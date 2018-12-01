package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Data;
import lombok.NonNull;

@Data
public class DoDeclineDraft {

	@NonNull
	private final QDocId id;

	@NonNull
	private final String declineNote;

}
