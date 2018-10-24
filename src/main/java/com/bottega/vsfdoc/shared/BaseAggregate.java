package com.bottega.vsfdoc.shared;

import java.util.ArrayList;
import java.util.List;

public class BaseAggregate {
	private final List<DomainEvent> events = new ArrayList<>();

	public List<DomainEvent> getEvents() {
		return events;
	}

	protected void emit(DomainEvent event) {
		events.add(event);
	}
}
