package com.aop.service;

import org.springframework.stereotype.Service;

import com.aop.customannotation.LogExecution;
import com.aop.entity.User;


@Service
public class UserService {

	@LogExecution
	public User getUserDetails(int value) {
		if(value <= 0) {
			throw new NullPointerException("Null Values are not allowed");
		}
	return User.builder().userId(1002102).emial("james001@gmail.com").phoneNumber(8529637412l).userName("James").sex("Male").status("Single").build();
	}
}
