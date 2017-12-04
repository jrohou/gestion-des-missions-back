package dev.gestionmissions.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {

	public LocalDate convertJSDateToJavaLocalDate(LocalDate oldDate){
		return oldDate.plusDays(1).minusMonths(1);
	}
}
