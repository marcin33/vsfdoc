package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.shared.DomainEvent;
import lombok.Value;

@Value
public class QDocContentWasUpdated implements DomainEvent {
	private final String content;


}
