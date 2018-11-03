package com.bottega.vsfdoc.draft.write.domain.produces;

import com.bottega.vsfdoc.shared.DomainEvent;
import lombok.Value;

@Value
public class QDocWasCreated implements DomainEvent {
	private String qDocNumber;

	public QDocWasCreated(String qDocNumber) {
		this.qDocNumber = qDocNumber;
	}
}
