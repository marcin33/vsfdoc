package com.bottega.vsfdoc.shared.validation;

import java.util.LinkedList;
import java.util.List;

public class ValidationViolations {

	private final List<String> violations = new LinkedList<>();

	public boolean isNotEmpty() {
		return !violations.isEmpty();
	}

	public void append(String violation) {
		violations.add(violation);
	}

	@Override
	public String toString() {
		return violations.stream().reduce((s, s2) -> s + ";" + s2).orElse("");
	}

}
