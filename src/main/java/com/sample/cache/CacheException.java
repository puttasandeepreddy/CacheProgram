package com.sample.cache;

public class CacheException extends RuntimeException{
	//Comment
	private static final long serialVersionUID = 1L;

	CacheException(String message, Exception e){
		super(message,e);
	}
	CacheException( Exception e){
		super(e);
	}
	
}
