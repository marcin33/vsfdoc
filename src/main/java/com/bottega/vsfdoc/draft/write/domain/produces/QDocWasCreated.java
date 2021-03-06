package com.bottega.vsfdoc.draft.write.domain.produces;

import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Value;

import java.util.UUID;

@Value
public class QDocWasCreated implements DomainEvent {

	private final QDocId qDocId;
	private final String qDocNumber;
	private final OwnerId ownerId;
	private final String type;
	private final String state;

	@Override
	public UUID getId() {
		return qDocId.value();
	}
}
