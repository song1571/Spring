package com.sj5final.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.sj5final.dao.UserDao;
import com.sj5final.domain.Level;
import com.sj5final.domain.User;



public class UserService {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;

	private UserDao userDao;
	private MailSender mailSender;
	private PlatformTransactionManager transactionManager;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

//	public void upgradeLevels() {
//		TransactionStatus status = 
//			this.transactionManager.getTransaction(new DefaultTransactionDefinition());
//		try {
//			List<User> users = userDao.getAll();
//			for (User user : users) {
//				if (canUpgradeLevel(user)) {
//					upgradeLevel(user);
//				}
//			}
//			this.transactionManager.commit(status);
//		} catch (RuntimeException e) {
//			this.transactionManager.rollback(status);
//			throw e;
//		}
//	}
	
	public boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel(); 
		switch(currentLevel) {                                   
		case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER); 
		case SILVER: return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
		case GOLD: return false;
		default: throw new IllegalArgumentException("Unknown Level: " + currentLevel); 
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEMail(user);
	}
	

	public void upgradeLevels() throws Exception {
		TransactionSynchronizationManager.initSynchronization();  
		Connection c = DataSourceUtils.getConnection(userDao.getdatasource()); 
		c.setAutoCommit(false);
		
		try {									   
			List<User> users = userDao.getAll();
			for (User user : users) {
				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}

			}
			c.commit();  
		} catch (Exception e) {    
			c.rollback();
			throw e;
		} finally {
			DataSourceUtils.releaseConnection(c, userDao.getdatasource());	
			TransactionSynchronizationManager.unbindResource(userDao.getdatasource());  
			TransactionSynchronizationManager.clearSynchronization();  
		}
	}
	
	private void sendUpgradeEMail(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("useradmin@ksug.org");
		mailMessage.setSubject("야레야레");
		mailMessage.setText("못말리는아가씨 " + user.getLevel().name());
		
		this.mailSender.send(mailMessage);
	}
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void add(User user) {
		if (user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}
}