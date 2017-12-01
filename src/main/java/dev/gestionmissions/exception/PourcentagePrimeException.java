package dev.gestionmissions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Le pourcentage de la prime ne dois pas éxcéder 10 %")
public class PourcentagePrimeException extends ControleException {

}
