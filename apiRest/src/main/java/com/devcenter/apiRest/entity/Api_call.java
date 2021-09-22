package com.devcenter.apiRest.entity;

import java.sql.Timestamp;
import org.springframework.data.annotation.Id;

public class Api_call {

	@Id
	public String id;
	
	private String ipAddr;
	
	private Timestamp time;
	
	private String service;
	
	public Api_call(String ipAddr, Timestamp time, String service) {
		super();
		this.ipAddr = ipAddr;
		this.time = time;
		this.service = service;
	}

	@Override
	public String toString() {
		return "Api_call [ipAddr=" + ipAddr + ", time=" + time + ", service=" + service + "]";
	}
	
}
