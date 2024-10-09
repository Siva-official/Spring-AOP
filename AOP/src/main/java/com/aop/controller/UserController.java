package com.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.customannotation.LogExecution;
import com.aop.entity.User;
import com.aop.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService service;

	@LogExecution
	@GetMapping("/getuserdata")
	public String getUserData() {
		System.out.println("Business Logic here ");
		return "getUserData Success";
	}
	
	@GetMapping("/getall")
	public String getAllUsers() {
		System.out.println("Business Logic here ");
		return "getAllUsers Success";
	}
	
	@GetMapping("/getuserdetails")
	public User getUserDetails() {
		User user = null;
		try {
			user = service.getUserDetails(0);
		} catch (Exception e) {
			System.out.println(e.getMessage()+ " Handled");
			user = User.builder().userId(1002102).emial("james001@gmail.com").phoneNumber(8529637412l).userName("James").sex("Male").status("Single").build();
		}
		return user;
	}
	
	@GetMapping("/aroundData")
	public int aroundData() {
          int x = 10 ;
          int y = 20;
          int z = (x*y)-(x+y);
		return (z*2);
	}

}
