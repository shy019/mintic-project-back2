package com.mintic.genericstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionControllerAdvice.class);

	private void logError(Exception ex) {
		String errorMessage = ex.getMessage().isEmpty() ? NO_MESSAGE_PROVIDED : ex.getMessage();
		logger.error(ERROR_TEMPLATE, errorMessage);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError handleException(Exception ex) {
		logError(ex);
		return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getName(), ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
			MethodArgumentTypeMismatchException.class,
			HttpMessageNotReadableException.class,
			org.springframework.beans.factory.UnsatisfiedDependencyException.class,
			HandlerMethodValidationException.class
	})
	@ResponseBody
	public ApiError dateBadRequest(Exception ex) {
		logError(ex);
		return new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getClass().getName(), INVALID_DATA);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
			GenericStoreException.class,
			BadRequestException.class,
			org.springframework.web.bind.support.WebExchangeBindException.class,
			org.springframework.web.server.ServerWebInputException.class
	})
	@ResponseBody
	public ApiError badRequest(Exception ex) {
		logError(ex);
		return new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getClass().getName(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ValidationError> handleValidationException(MethodArgumentNotValidException ex) {
		logError(ex);
		return ex.getBindingResult().getAllErrors().stream()
				.map(this::mapError)
				.toList();
	}

	private ValidationError mapError(ObjectError objectError) {
		String field = (objectError instanceof FieldError fieldError) ? fieldError.getField() : objectError.getObjectName();
		return new ValidationError(field, objectError.getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ApiError notFoundRequest(Exception ex) {
		logError(ex);
		return new ApiError(HttpStatus.NOT_FOUND.value(), ex.getClass().getName(), ex.getMessage());
	}
}