package com.bottega.vsfdoc.draft.domain;

import com.bottega.vsfdoc.draft.domain.consumes.DoCreateDraft;
import com.bottega.vsfdoc.draft.domain.consumes.DoUpdateContent;

public class DraftCommandHandler {


	private QDocNumberGenerator numberGenerator;

	public void handle(DoCreateDraft command) {

		QDocNumber number = numberGenerator.generate();
		new QDocDraft(number);

	}

	public void handle(DoUpdateContent command) {

	}

}
