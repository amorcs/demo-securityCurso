package com.mballem.curso.security.web.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ModelAndView usuarioNaoEncontrado(UsernameNotFoundException notFoundException) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("status", 404);
		model.addObject("error", "Operação não pode ser Realizada");
		model.addObject("message", notFoundException.getMessage());
		return model;
	}
}
