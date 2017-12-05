package dev.gestionmissions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="La nature avec ce nom existe déjà")
public class DoublonNatureException extends ControleException {

}

