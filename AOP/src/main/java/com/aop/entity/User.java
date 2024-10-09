package com.aop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class User {
	
	private long userId;
	private String userName;
	private String emial;
	private long phoneNumber;
	private String sex;
	private String status;
	
	
}
