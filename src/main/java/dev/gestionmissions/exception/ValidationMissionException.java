package dev.gestionmissions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ValidationMissionException extends Exception {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	public ValidationMissionException(String errorMsg) {
		super(errorMsg);
	}

	@ExceptionHandler(ValidationMissionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleException(ValidationMissionException e) {
		return e.getMessage();
	}

}
