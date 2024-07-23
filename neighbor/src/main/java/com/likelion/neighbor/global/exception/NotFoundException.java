package com.likelion.neighbor.global.exception;

import com.likelion.neighbor.global.exception.model.CustomException;
import com.likelion.neighbor.global.exception.model.Error;

public class NotFoundException extends CustomException {

	public NotFoundException(Error error, String message){
		super(error,message);
	}
}
