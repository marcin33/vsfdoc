package com.bottega.vsfdoc.draft.write.infra;

import com.bottega.vsfdoc.draft.write.domain.ports.CurrentUserPort;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserPortImpl implements CurrentUserPort {

	@Override
	public boolean isAuditor() {
		return false; // TODO read user from spring security context
	}
}
