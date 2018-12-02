package com.bottega.vsfdoc.draft.write.infra.rest;

import com.bottega.vsfdoc.draft.write.domain.consumes.DoCreateDraft;
import com.bottega.vsfdoc.draft.write.infra.rest.dto.CreateQDocDraftDto;
import com.bottega.vsfdoc.shared.command.CommandBus;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class QDocController {

	private final CommandBus commandBus;

	@PostMapping("/qdoc")
	public ResponseEntity<Void> create(@Valid @RequestBody CreateQDocDraftDto dto) throws URISyntaxException {
		QDocId qDocId = QDocId.of(UUID.randomUUID());

		DoCreateDraft command = new DoCreateDraft(qDocId, dto.getType().toUpperCase(), dto.getOwnerId());
		commandBus.dispatch(command);

		return ResponseEntity.created(new URI("/qdoc/" + qDocId)).build();

	}
}
