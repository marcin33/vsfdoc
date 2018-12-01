package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.NonNull;
import lombok.Value;


@Value
public class DoCreateDraft {

	@NonNull
	private final QDocId qDocId;

	@NonNull
	private final String qDocType;

	@NonNull
	private final OwnerId ownerId;

}
