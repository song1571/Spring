package com.sung.springframe.learningtest.daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sung.springframe.dao.UserDao;
import com.sung.springframe.domain.Level;
import com.sung.springframe.domain.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDaoFactory.class})
public class UserDaoTest {
	
	@Autowired UserDao dao;
	@Autowired DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
    	
        this.user1 = new User("gyumee", "박성철", "springno1",Level.BASIC,1,0);
        this.user2 = new User("leegw700", "이길원", "springno2",Level.SILVER,55,10);
        this.user3 = new User("bumjin", "박범진", "springno3",Level.GOLD,100,40);
    }

    @Test
	public void addAndGet() throws SQLException, ClassNotFoundException {				
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		Optional<User> Optuserget1 = dao.get(user1.getId());
		if (!Optuserget1.isEmpty()) {
			User userget = Optuserget1.get();
			assertEquals(user1.getName(), userget.getName());
			assertEquals(user1.getPassword(), userget.getPassword());
		}		
		
		Optional<User> Optuserget2 = dao.get(user2.getId());	
		if (!Optuserget2.isEmpty()) {
			User userget = Optuserget2.get();
			assertEquals(user2.getName(), userget.getName());
			assertEquals(user2.getPassword(), userget.getPassword());		
		}		
	}

    @Test
    public void count() throws SQLException, ClassNotFoundException {
    	dao.deleteAll();
    	assertEquals(dao.getCount(),0);
    	
    	dao.add(user1);
    	assertEquals(dao.getCount(),1);
    	
    	dao.add(user2);
    	assertEquals(dao.getCount(),1);
    	
    	dao.add(user3);
    	assertEquals(dao.getCount(),1);
    }
    
    @Test
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        assertThrows(EmptyResultDataAccessException.class,
                () -> { dao.get("unknown_id");});
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
