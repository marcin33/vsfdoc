package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Data;

@Data
public class DoUpdateContent {
	private QDocId qDocId;
	private String content;
}
