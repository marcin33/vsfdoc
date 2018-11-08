package com.bottega.vsfdoc.draft.write.domain;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocContentWasUpdated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;

class QDocDraft {

	private final QDocNumber number;
	private final QDocState state;
	private final String content;
	private final VerifierId verifierId;
	private final Departments departments;

	QDocDraft(QDocDraftRecord record) {
		this.number = record.getNumber()
			.map(QDocNumber::new)
			.orElse(null);
		this.content = record.getContent()
			.orElse(null);
		this.state = record.getState()
			.map(QDocState::valueOf)
			.orElse(null);
		this.verifierId = record.getVerifierId()
			.map(VerifierId::new)
			.orElse(null);
		this.departments = new Departments(record.getDepartments());
	}

	DomainEvent sendToVerification() {
		if (number != null && state.isNew() && verifierId != null && isNotBlank(content)
				&& departments.isNotEmpty()) {
			return new QDocWasSendToVerification();
		}
		else {
			throw new IllegalStateException("Cannot send to verification");
		}
	}

	DomainEvent updateContent(String content) {
		return new QDocContentWasUpdated(content);

	}

	DomainEvent create(QDocNumber number) {
		return new QDocWasCreated(number.getValue());
	}
}
