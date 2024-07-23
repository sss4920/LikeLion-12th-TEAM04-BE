package com.likelion.neighbor.global.exception;

import com.likelion.neighbor.global.exception.model.CustomException;
import com.likelion.neighbor.global.exception.model.Error;

public class BadRequestException extends CustomException {

	public BadRequestException(Error error, String message){
		super(error,message);
	}
}
