package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import lombok.NonNull;
import lombok.Value;

@Value
public class DoAssignToVerifier {

	@NonNull
	private final QDocId qDocId;

	@NonNull
	private final VerifierId verifierId;
}
