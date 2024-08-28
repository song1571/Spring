package com.sj5finaltest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sj5final.dao.DaoFactory;
import com.sj5final.dao.UserDao;
import com.sj5final.domain.Level;
import com.sj5final.domain.User;
import com.sj5final.service.UserService;




@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})
public class UserServiceTest {
	@Autowired UserService userService;	
	@Autowired UserDao userDao;
	@Autowired MailSender mailSender; 
	@Autowired PlatformTransactionManager transactionManager;
	
	static List<User> users;	// test fixture
	
	@BeforeEach
	public void setUp() {
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", "user1@ksug.org", Level.BASIC, UserService.MIN_LOGCOUNT_FOR_SILVER-1, 0),
				new User("joytouch", "강명성", "p2", "user2@ksug.org", Level.BASIC, UserService.MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("test","테스트","p6","tjrwn1234562@gmail.org", Level.BASIC, 1, UserService.MIN_LOGCOUNT_FOR_SILVER),
				new User("erwins", "신승한", "p3", "user3@ksug.org", Level.SILVER, 60, UserService.MIN_RECCOMEND_FOR_GOLD-1),
				new User("madnite1", "이상호", "p4", "user4@ksug.org", Level.SILVER, 60,UserService. MIN_RECCOMEND_FOR_GOLD),
				new User("green", "오민규", "p5", "user5@ksug.org", Level.GOLD, 100, Integer.MAX_VALUE));

	}

	@Test @DirtiesContext
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		MockMailSender mockMailSender = new MockMailSender();  
		userService.setMailSender(mockMailSender);  
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), true);
		checkLevelUpgraded(users.get(5), false);
		
		List<String> request = mockMailSender.getRequests();  
		assertEquals(request.size(), 2);  
		assertEquals(request.get(0), users.get(1).getEmail());  
		assertEquals(request.get(1), users.get(3).getEmail());  
	}
	
	static class MockMailSender implements MailSender {
		private List<String> requests = new ArrayList<String>();	
		
		public List<String> getRequests() {
			return requests;
		}

		public void send(SimpleMailMessage mailMessage) throws MailException {
			requests.add(mailMessage.getTo()[0]);  
		}

		public void send(SimpleMailMessage[] mailMessage) throws MailException {
		}
	}


	private void checkLevelUpgraded(User user, boolean upgraded) {
		Optional<User> userUpdate = userDao.get(user.getId());
		if (!userUpdate.isEmpty()) {
			User userUpdates = userUpdate.get();
			if (upgraded) {
				assertEquals(userUpdates.getLevel(), user.getLevel().nextLevel());
			}
			else {
				assertEquals(userUpdates.getLevel(), (user.getLevel()));
			}	
		}
	}

	@Test 
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);	  // GOLD 레벨  
		User userWithoutLevel = users.get(0);  
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);	  
		userService.add(userWithoutLevel);
		
		Optional<User> userWithLevelRead = userDao.get(userWithLevel.getId());
		Optional<User> userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertEquals(userWithLevelRead.get().getLevel(), userWithLevel.getLevel()); 
		assertEquals(userWithLevelRead.get().getLevel(), Level.BASIC);
	}
 
	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserService testUserService = new TestUserService(users.get(3).getId());  
		testUserService.setUserDao(this.userDao);
		testUserService.setTransactionManager(this.transactionManager);
		testUserService.setMailSender(this.mailSender);
		 
		userDao.deleteAll();			  
		for(User user : users) userDao.add(user);
		
		try {
			testUserService.upgradeLevels();   
			fail("TestUserServiceException expected"); 
		}
		catch(TestUserServiceException e) { 
		}
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	static class TestUserServiceException extends RuntimeException {
	}
	
	
	static class TestUserService extends UserService {
		private String id;
		
		private TestUserService(String id) {  
			this.id = id;
		}

		protected void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) throw new TestUserServiceException();  
			super.upgradeLevel(user);  
		}
		
		@Override
		public void upgradeLevels()
		{
			TransactionStatus status = this.getTransactionManager().getTransaction(new DefaultTransactionDefinition());
			
			try {
				for (User user: users)
				{
					if (canUpgradeLevel(user));
					{
						upgradeLevel(user);
					}
				}
				this.getTransactionManager().commit(status);
			
		}
			catch (RuntimeException e)
			{
				this.getTransactionManager().rollback(status);
				throw e;
			}
	}

	}
}