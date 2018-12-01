package com.bottega.vsfdoc.draft.write.infra;

import com.bottega.vsfdoc.command.CommandHandlerRegistry;
import com.bottega.vsfdoc.draft.write.domain.DraftCommandHandler;
import com.bottega.vsfdoc.draft.write.domain.QDocDraftRepo;
import com.bottega.vsfdoc.draft.write.domain.QDocNumberGenerator;
import com.bottega.vsfdoc.draft.write.domain.QDocValidatorProvider;
import com.bottega.vsfdoc.draft.write.domain.consumes.*;
import com.bottega.vsfdoc.draft.write.domain.ports.CurrentUserPort;
import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftDaoPort;
import com.bottega.vsfdoc.draft.write.domain.ports.SequencePort;
import com.bottega.vsfdoc.draft.write.domain.ports.SystemConfigPort;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QDocWriteConfig {

	@Autowired
	public SystemConfigPort systemConfigPort;

	@Autowired
	public CurrentUserPort currentUserPort;

	@Bean
	public InitializingBean qDocCommandHandlerInitualizer(DraftCommandHandler draftCommandHandler, CommandHandlerRegistry registry) {
		return () -> {
			registry.register(draftCommandHandler::handle, DoCreateDraft.class);
			registry.register(draftCommandHandler::handle, DoUpdateContent.class);
			registry.register(draftCommandHandler::handle, DoAssignToVerifier.class);
			registry.register(draftCommandHandler::handle, DoSetDepartments.class);
			registry.register(draftCommandHandler::handle, DoSendToVerification.class);
			registry.register(draftCommandHandler::handle, DoVerifyDraft.class);
			registry.register(draftCommandHandler::handle, DoDeclineDraft.class);
			registry.register(draftCommandHandler::handle, DoPublishDraft.class);
		};
	}

	@Bean
	public DraftCommandHandler draftCommandHandler(QDocDraftRepo qDocDraftRepo, SequencePort sequencePort) {
		return new DraftCommandHandler(
				new QDocNumberGenerator(sequencePort, systemConfigPort, currentUserPort),
				qDocDraftRepo, new QDocValidatorProvider());
	}

	@Bean
	public QDocDraftRepo qDocDraftRepo(QDocDraftDaoPort qDocDraftDaoPort, ApplicationEventPublisher applicationEventPublisher) {
		return new QDocDraftRepo(qDocDraftDaoPort, applicationEventPublisher);
	}


}
