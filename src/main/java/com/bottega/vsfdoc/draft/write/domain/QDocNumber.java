package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.SystemType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class QDocNumber {

	private static final String SEPARATOR = "/";
	private final List<String> value;

	QDocNumber(SystemType type, boolean isDemo, boolean isAuditor, LocalDate date, Long next) {
		if (next < 0) {
			throw new RuntimeException("next less then 0");
		}

		String number = type.name() + SEPARATOR + next + SEPARATOR + date.getMonthValue() + SEPARATOR + date.getYear();

		if (isDemo) {
			number = "DEMO" + SEPARATOR + number;
		}
		if (isAuditor) {
			number = number + SEPARATOR + "AUDIT";
		}

		this.value = Arrays.asList(number.split("/"));
	}

	String getValue() {
		return String.join("/", value);
	}
}
