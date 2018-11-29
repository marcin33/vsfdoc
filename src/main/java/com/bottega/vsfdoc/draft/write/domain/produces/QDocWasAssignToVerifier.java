package com.bottega.vsfdoc.draft.write.domain.produces;

import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import lombok.Value;

import java.util.UUID;

@Value
public class QDocWasAssignToVerifier implements DomainEvent {

	private final QDocId qDocId;
	private final VerifierId verifierId;

	@Override
	public UUID getId() {
		return qDocId.value();
	}

}
