package com.devcenter.apiRest.model;

public class Response {
	private String timestamp;
	private String data;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String responseCode;
	private String status;
	public Response(String timestamp, String data, String responseCode, String status) {
		super();
		this.timestamp = timestamp;
		this.data = data;
		this.responseCode = responseCode;
		this.status = status;
	}
	
}
