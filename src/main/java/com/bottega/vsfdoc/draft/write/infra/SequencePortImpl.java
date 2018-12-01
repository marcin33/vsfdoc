package com.bottega.vsfdoc.draft.write.infra;

import com.bottega.vsfdoc.draft.write.domain.ports.SequencePort;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class SequencePortImpl implements SequencePort {

	private final AtomicLong counter = new AtomicLong();

	@Override
	public Long next() {
		return counter.incrementAndGet();
	}
}
