package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.consumes.DoCreateDraft;
import com.bottega.vsfdoc.draft.write.domain.consumes.DoSendToVerification;
import com.bottega.vsfdoc.draft.write.domain.consumes.DoUpdateContent;

public class DraftCommandHandler {


	private QDocNumberGenerator numberGenerator;
	private QDocDraftRepo repo;

	public void handle(DoCreateDraft command) {

		QDocNumber number = numberGenerator.generate();
		repo.save(new QDocDraft(number));

	}

	public void handle(DoUpdateContent command) {
		QDocDraft draft = repo.load(command.getQDocId());
		draft.updateContent(command.getContent());
		repo.save(draft);
	}

	public void handle(DoSendToVerification command) {
		QDocDraft draft = repo.load(command.getQDocId());
		draft.sendToVerification();
		repo.save(draft);
	}

}
