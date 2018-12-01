package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Data;
import lombok.NonNull;

@Data
public class DoPublishDraft {

	@NonNull
	private final QDocId id;

}
