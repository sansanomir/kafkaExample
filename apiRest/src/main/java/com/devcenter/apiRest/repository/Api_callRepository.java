package com.devcenter.apiRest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.apiRest.entity.Api_call;

@Repository("Api_callRepository")
public interface Api_callRepository extends MongoRepository<Api_call, String>{
	public abstract Api_call insert(Api_call apiCall);
}
