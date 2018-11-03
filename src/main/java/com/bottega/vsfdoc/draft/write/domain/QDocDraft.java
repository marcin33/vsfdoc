package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.VerifierId;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

class QDocDraft {

	private final QDocNumber number;
	private final QDocState state;
	private final String content;
	private final VerifierId verifierId;
	private final Departments departments;

	QDocDraft(QDocDraftRecord record) {
		this.content = record.getContent();
		this.number = new QDocNumber(record.getNumber());
		this.state = QDocState.valueOf(record.getState());
		this.verifierId = new VerifierId();
		this.departments = new Departments();
	}

	DomainEvent sendToVerification() {
		if (number != null && state.isNew() && verifierId != null && isNotBlank(content) && departments.isNotEmpty()) {
			return new QDocWasSendToVerification();
		} else {
			throw new RuntimeException("cant send to verification");
		}
	}

	DomainEvent updateContent(String content) {
		return new QDocContentWasUpdated(content);

	}

	DomainEvent create(QDocNumber number) {
		return new QDocWasCreated(number.getValue());
	}
}
