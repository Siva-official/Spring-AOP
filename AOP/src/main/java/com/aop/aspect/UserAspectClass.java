package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aop.customannotation.LogExecution;
import com.aop.entity.User;

@Component
@Aspect
public class UserAspectClass {

	private static final Logger logger = LoggerFactory.getLogger(UserAspectClass.class);

	
	/**
	 * @param joinPoint
	 * @param logExecution 
	 * 
	 * LogExecution is Custom annotation instead of expression we can use  annotation where we what to apply advice .
	 * To apply advice we need to annotate methods with this custom annotation 
	 */
	@Before("@annotation(logExecution)")
	public void beforeAllMethods(JoinPoint joinPoint,LogExecution logExecution) {
		System.out.println("=======================================================");
       logger.info(joinPoint.getSignature().getName() +" Started...................!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
	}
	
	/**
	 * @After("execution(* com.aop.controller.UserController.*(..))")
	 * @param joinPoint
	 * There are several ways to apply advice to methods here Execution is one of the way . By using below expression we can apply advice all methods present in UserControler
	 * based on requirement we can modify expression accordingly
	 * 
	 * 
	 */
	@After("execution(* com.aop.controller.UserController.*(..))")
	public void afterAllMethods(JoinPoint joinPoint) {
       logger.info(joinPoint.getSignature().getName() +" Completed...................!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		System.out.println("=======================================================");
	}
	
	/**
	 * @AfterReturning(value = "execution(* com.aop.controller.UserController.*(..))" ,returning = "value")
	 * @param joinPoint
	 * @param value
	 * @AfterReturning means after returning destination method it will execute .
	 * We can catch return value also by adding `returning`
	 * 
	 */
	
	@AfterReturning(value = "execution(* com.aop.controller.UserController.*(..))" ,returning = "value")
	public void afterReturningData(JoinPoint joinPoint ,User value) {
	       logger.info(joinPoint.getSignature().getName() +" returned UserName: {} ..................!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
		
	}
	
	/**
	 * @AfterThrowing(value ="execution(* com.aop.controller.UserController.*(..))", throwing = "exception" )
	 * @param joinPoint
	 * @param value
	 * @AfterThrowing means after throwing exception execution will come to here.
	 * We can catch exception value also by adding `throwing`
	 * 
	 */
	
	@AfterThrowing(value ="execution(* com.aop.controller.UserController.*(..))", throwing = "exception" )
	public void afterThrowingMethod(JoinPoint joinPoint,Exception exception) {
	       logger.info(joinPoint.getSignature().getName() +" Exception : {} ..................!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ",exception.getMessage());
	}
	
	/**
	 * 	@Around(value = "testPointCut()")
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * It will not execute the destination method .The execution will be starts from here for that particular methods.
	 * if we want to execute destination method also explicitly we need to give permission by using (joinPoint.proceed()) this statement.For this we need pass parameter ProceedingJoinPoint interface
	 * 
	 */
	@Around(value = "testPointCut()")
	public Object  aroundDataMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info(joinPoint.getSignature().getName()+"  Arround Working ");
		return joinPoint.proceed();
//		return 10;
	}
	/**
	 * @Pointcut
	 * If multiple methods having save point cut expression we can maintain it separately like below .And instead of expression we can use point cut annotated method name as expression
	 */
	@Pointcut("execution(* com.aop.controller.UserController.aroundData(..))")
	public void testPointCut() {
		
	}
	

}
