package com.bottega.vsfdoc.shared.validation;

import lombok.Value;

import java.util.UUID;

@Value
public class ValidationException extends RuntimeException {

	private final UUID id;
	private final ValidationViolations violations;

}
