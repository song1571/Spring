package com.sung.springframe.learningtest.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sung.springframe.dao.DaoFactory;

//필요한 import 구문 추가
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})  // TestJunit을 DaoFactory로 수정
public class JUnitTest {
@Autowired ApplicationContext context; // Spring IoC Container가 자동 주입됨.

// SET은 동일한 엘리먼트 중복을 허용하지 않는다.
// 엘리먼트: 특정 JUnitTest 클래스 오브젝트(인스턴스) 참조(C언어에서 포인터:값으로 메모리 주소) 값
static Set<JUnitTest> testObjects = new HashSet<>();
static ApplicationContext contextObject = null;

@Test public void test1() {
   assertFalse(testObjects.contains(this));
   testObjects.add(this);
   
   assertFalse(contextObject == null || contextObject == this.context);
   contextObject = this.context;
}

@Test public void test2() {
   assertFalse(testObjects.contains(this));
   testObjects.add(this);
   
   assertTrue(contextObject == null || contextObject == this.context);
   contextObject = this.context;
}

@Test public void test3() {
   assertFalse(testObjects.contains(this));
   testObjects.add(this);
   
   assertFalse(contextObject == null || contextObject == this.context);
   contextObject = this.context;
}

@Test public void test4() {
   assertFalse(testObjects.contains(this));
   testObjects.add(this);
   
   assertFalse(contextObject == null || contextObject == this.context);
   contextObject = this.context;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
