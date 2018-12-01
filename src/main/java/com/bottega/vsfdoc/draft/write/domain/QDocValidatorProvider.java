package com.bottega.vsfdoc.draft.write.domain;

class QDocValidatorProvider {

	QDocValidator get(QDocDraft qDocDraft) {
		return new QDocValidator(qDocDraft);
	}

}
