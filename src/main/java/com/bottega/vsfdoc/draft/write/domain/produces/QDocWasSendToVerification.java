package com.bottega.vsfdoc.draft.write.domain.produces;

import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Value;

import java.util.UUID;

@Value
public class QDocWasSendToVerification implements DomainEvent {

    private final QDocId qDocId;
    private final String state;

    @Override
    public UUID getId() {
        return qDocId.value();
    }
}
