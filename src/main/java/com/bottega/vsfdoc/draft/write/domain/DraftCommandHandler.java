package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.consumes.DoCreateDraft;
import com.bottega.vsfdoc.draft.write.domain.consumes.DoSendToVerification;
import com.bottega.vsfdoc.draft.write.domain.consumes.DoUpdateContent;

public class DraftCommandHandler {


	private QDocNumberGenerator numberGenerator;
	private QDocDraftRepo repo;

	public void handle(DoCreateDraft command) {
		repo.apply(command.getQDocId(), qDoc -> qDoc.create(numberGenerator.generate(),
				command.getOwnerId(),
				QDocType.valueOf(command.getQDocType())));
	}

	public void handle(DoUpdateContent command) {
		repo.apply(command.getQDocId(), qDoc -> qDoc.updateContent(command.getContent()));
	}

	public void handle(DoSendToVerification command) {
		repo.apply(command.getQDocId(), QDocDraft::sendToVerification);
	}

}
