package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Data;
import lombok.NonNull;

@Data
public class DoUpdateContent {

	@NonNull
	private QDocId qDocId;

	@NonNull
	private String content;
}
