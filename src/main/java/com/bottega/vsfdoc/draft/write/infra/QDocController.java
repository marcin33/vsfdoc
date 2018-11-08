package com.bottega.vsfdoc.draft.write.infra;

import com.bottega.vsfdoc.draft.write.domain.DraftCommandHandler;
import com.bottega.vsfdoc.draft.write.domain.consumes.DoCreateDraft;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class QDocController {

	private final DraftCommandHandler handler;

	@PostMapping("/qdoc")
	public ResponseEntity<Void> create(String qDocType) throws URISyntaxException {
		QDocId qDocId = new QDocId(UUID.randomUUID());

		DoCreateDraft command = new DoCreateDraft(qDocType, qDocId);
		handler.handle(command);

		return ResponseEntity.created(new URI("/qdoc/" + qDocId)).build();

	}
}
