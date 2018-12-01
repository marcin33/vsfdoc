package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.consumes.*;

public class DraftCommandHandler {

	private QDocNumberGenerator numberGenerator;
	private QDocDraftRepo repo;
	private QDocValidatorProvider validatorProvider;

	public void handle(DoCreateDraft command) {
		repo.apply(command.getQDocId(),
				qDoc -> qDoc.create(numberGenerator.generate(),
						command.getOwnerId(),
						QDocType.valueOf(command.getQDocType())));
	}

	public void handle(DoUpdateContent command) {
		repo.apply(command.getQDocId(),
				qDoc -> qDoc.updateContent(validatorProvider.get(qDoc),
						command.getContent()));
	}

	public void handle(DoAssignToVerifier command) {
		repo.apply(command.getQDocId(),
				qDoc -> qDoc.assignToVerifier(validatorProvider.get(qDoc),
						command.getVerifierId()));
	}

	public void handle(DoSetDepartments command) {
		repo.apply(command.getQDocId(),
				qDoc -> qDoc.setDepartments(validatorProvider.get(qDoc),
						command.getDepartmentIds()));
	}

	public void handle(DoSendToVerification command) {
		repo.apply(command.getQDocId(),
				qDoc -> qDoc.sendToVerification(validatorProvider.get(qDoc)));
	}

	public void handle(DoVerifyDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.verify(validatorProvider.get(qDoc)));
	}

	public void handle(DoDeclineDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.decline(validatorProvider.get(qDoc), command.getDeclineNote()));
	}

	public void handle(DoPublishDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.publish(validatorProvider.get(qDoc)));
	}

}
