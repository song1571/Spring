package com.sung.pointcutapi.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import com.sung.pointcutapi.service.CustomAnnotation;

public class CustomPointCut implements Pointcut {

	@Override
	public ClassFilter getClassFilter() {
		// boolean matches(Class<?> clazz);
		// unqualified name
		// qualified name
		return clazz -> clazz.getName().startsWith("com.sung.pointcutapi.service");
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return new MethodMatcher() {

			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return method.isAnnotationPresent(CustomAnnotation.class) 
						|| "differentMethod".equals(method.getName());
			}

			@Override
			public boolean isRuntime() {
				System.out.println("called isRuntime 메서드");
				return false;
			}

			@Override
			public boolean matches(Method method, Class<?> targetClass, Object... args) {
				if (args.length > 0
						&& args[0] instanceof String) {
					System.out.println("called matches[" +
						method.getName() + "]:arg is String");
					
					return true;
				} else {
					System.out.println("called matches[" +
							method.getName() + "]:arg is not String");
				}
				return false;
			}			
		};
	}

}
