package dev.gestionmissions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Le taux journalier moyen doit être renseigner si facture égale vrai")
public class TjmException extends ControleException {

}
