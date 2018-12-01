package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.consumes.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DraftCommandHandler {

	private final QDocNumberGenerator numberGenerator;
	private final QDocDraftRepo repo;
	private final QDocValidatorProvider validatorProvider;

	public void handle(DoCreateDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.create(numberGenerator.generate(),
						command.getOwnerId(),
						QDocType.valueOf(command.getQDocType())));
	}

	public void handle(DoUpdateContent command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.updateContent(validatorProvider.get(),
						command.getContent()));
	}

	public void handle(DoAssignToVerifier command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.assignToVerifier(validatorProvider.get(),
						command.getVerifierId()));
	}

	public void handle(DoSetDepartments command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.setDepartments(validatorProvider.get(),
						command.getDepartmentIds()));
	}

	public void handle(DoSendToVerification command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.sendToVerification(validatorProvider.get()));
	}

	public void handle(DoVerifyDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.verify(validatorProvider.get()));
	}

	public void handle(DoDeclineDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.decline(validatorProvider.get(), command.getDeclineNote()));
	}

	public void handle(DoPublishDraft command) {
		repo.apply(command.getId(),
				qDoc -> qDoc.publish(validatorProvider.get()));
	}

}
