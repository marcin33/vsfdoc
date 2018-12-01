package com.bottega.vsfdoc.draft.write.infra;

import com.bottega.vsfdoc.draft.write.domain.consumes.DoCreateDraft;
import com.bottega.vsfdoc.shared.command.CommandBus;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class QDocController {

	private final CommandBus commandBus;

	@PostMapping("/qdoc/{qDocType}")
	public ResponseEntity<Void> create(@PathVariable("qDocType") String qDocType) throws URISyntaxException {
		QDocId qDocId = QDocId.of(UUID.randomUUID());
		OwnerId ownerId = OwnerId.of(UUID.randomUUID()); // TODO get from security

		DoCreateDraft command = new DoCreateDraft(qDocId, qDocType.toUpperCase(), ownerId);
		commandBus.dispatch(command);

		return ResponseEntity.created(new URI("/qdoc/" + qDocId)).build();

	}
}
