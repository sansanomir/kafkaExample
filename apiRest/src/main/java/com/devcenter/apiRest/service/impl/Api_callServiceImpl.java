package com.devcenter.apiRest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcenter.apiRest.entity.Api_call;
import com.devcenter.apiRest.repository.Api_callRepository;
import com.devcenter.apiRest.service.Api_callService;

@Service("Api_callServiceImpl")
public class Api_callServiceImpl implements Api_callService{

	@Autowired
	@Qualifier("Api_callRepository")
	private Api_callRepository apiCallRepository;

	@Override
	public Api_call insert(Api_call apiCall) {
		return apiCallRepository.insert(apiCall);		
	}
}
