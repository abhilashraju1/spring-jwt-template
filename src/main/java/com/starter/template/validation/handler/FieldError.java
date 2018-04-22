package com.starter.template.validation.handler;

public class FieldError {
	
	private String fieldName;
	private String message;
	
	public FieldError(String fieldName, String error){
		this.fieldName = fieldName;
		this.message = error;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
