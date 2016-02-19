package com.service;

public class AccountServiceImp implements IAccountService {

	public void save(String name, String password) {
	//	throw new RuntimeException("故意抛出一个异常");
		System.out.println(name +password);
	}

	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

}
