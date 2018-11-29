package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocContentWasUpdated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasAssignToVerifier;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

class QDocDraft {

	private final QDocId qDocId;
	private final OwnerId ownerId;
	private final QDocNumber number;
	private final QDocState state;
	private final String content;
	private final VerifierId verifierId;
	private final Departments departments;

	QDocDraft(QDocDraftRecord record) {
		this.qDocId = QDocId.of(record.getId());
		this.ownerId = record.getOwnerId()
				.map(OwnerId::of)
				.orElse(null);
		this.number = record.getNumber()
				.map(QDocNumber::new)
				.orElse(null);
		this.content = record.getContent()
				.orElse(null);
		this.state = record.getState()
				.map(QDocState::valueOf)
				.orElse(null);
		this.verifierId = record.getVerifierId()
				.map(VerifierId::of)
				.orElse(null);
		this.departments = new Departments(record.getDepartments());
	}

	DomainEvent sendToVerification() {
		if (number != null && state.isNew() && verifierId != null && isNotBlank(content)
				&& departments.isNotEmpty()) {
			return new QDocWasSendToVerification(qDocId);
		}
		throw new IllegalStateException("Cannot send to verification");
	}

	DomainEvent updateContent(String content) {
		return new QDocContentWasUpdated(qDocId, content);
	}

	DomainEvent create(QDocNumber number, OwnerId ownerId, QDocType type) {
		if (number != null && ownerId != null && type != null) {
			return new QDocWasCreated(qDocId, number.getValue(), ownerId, type.name(), QDocState.NEW.name());
		}
		throw new IllegalStateException("Cannot create document");
	}

	DomainEvent assignToVerifier(VerifierId verifierId) {
		if (state.isNew() && !ownerId.isSame(verifierId)) {
			return new QDocWasAssignToVerifier(qDocId, verifierId);
		}
		throw new IllegalStateException("Cannot assign to verifier");
	}
}
