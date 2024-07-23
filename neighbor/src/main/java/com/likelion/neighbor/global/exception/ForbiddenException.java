package com.likelion.neighbor.global.exception;

import com.likelion.neighbor.global.exception.model.CustomException;
import com.likelion.neighbor.global.exception.model.Error;

public class ForbiddenException extends CustomException {
	public ForbiddenException(Error error, String message){
		super(error,message);
	}


}
