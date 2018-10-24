package com.bottega.vsfdoc.draft.domain;

import com.bottega.vsfdoc.draft.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.BaseAggregate;
import com.bottega.vsfdoc.shared.QDocId;
import com.bottega.vsfdoc.shared.VerifierId;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

class QDocDraft extends BaseAggregate {


	private QDocId id;
	private Long version;
	private QDocNumber number;
	private QDocState state;
	private String content;
	private VerifierId verifierId;
	private Departments departments;

	QDocDraft(QDocNumber number) {
		this.number = number;
		emit(new QDocWasCreated(number.getValue()));
	}

	void sendToVerification() {
		if (state.isNew() && verifierId != null && isNotBlank(content) && departments.isNotEmpty()) {
			state = QDocState.IN_VERIFICATION;
			emit(new QDocWasSendToVerification());
		} else {
			throw new RuntimeException("cant send to verification");
		}
	}

	void updateContent(String content) {
		this.content = content;
		emit(new QDocContentWasUpdated(content));

	}
}
