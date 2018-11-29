package com.bottega.vsfdoc.draft.write.domain.produces;

import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Value;

import java.util.UUID;

@Value
public class QDocContentWasUpdated implements DomainEvent {

	private final QDocId qDocId;
	private final String content;

	@Override
	public UUID getId() {
		return qDocId.value();
	}
}
