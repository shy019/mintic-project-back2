package com.mintic.tiendagenerica.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionControllerAdvice.class);
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError handleException(Exception ex) {

		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));

		logger.info(
				"Internal error::".concat(ex.getMessage().isEmpty() ? ex.getStackTrace().toString() : ex.getMessage()));
		return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getName(), ex.getMessage());
	}

	/*@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class ,
		org.springframework.beans.factory.UnsatisfiedDependencyException.class})
	@ResponseBody
	public ApiError dateBadRequest(Exception ex) {
		logger.info(
				"Internal error::".concat(ex.getMessage().isEmpty() ? ex.getStackTrace().toString() : ex.getMessage()));
		return new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getClass().getName(),
				"The date does not have a valid format. ");
	}*/

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ TiendaGenericaException.class, BadRequestException.class,
			org.springframework.web.bind.support.WebExchangeBindException.class,
			org.springframework.web.server.ServerWebInputException.class })
	@ResponseBody
	public ApiError badRequest(Exception ex) {
		logger.info(
				"Internal error::".concat(ex.getMessage().isEmpty() ? ex.getStackTrace().toString() : ex.getMessage()));
		return new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getClass().getName(), ex.getMessage());
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ValidationError> handleException(MethodArgumentNotValidException ex) {
		logger.info(
				"Internal error::".concat(ex.getMessage().isEmpty() ? ex.getStackTrace().toString() : ex.getMessage()));
		return ex.getBindingResult().getAllErrors().stream().map(this::mapError).collect(Collectors.toList());
	}

	private ValidationError mapError(ObjectError objectError) {
		if (objectError instanceof FieldError) {
			return new ValidationError(((FieldError) objectError).getField(), objectError.getDefaultMessage());
		}
		return new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public ApiError notFoundRequest(Exception ex) {
		logger.info(
				"Internal error::".concat(ex.getMessage().isEmpty() ? ex.getStackTrace().toString() : ex.getMessage()));
		return new ApiError(HttpStatus.NOT_FOUND.value(), ex.getClass().getName(), ex.getMessage());
	}

}