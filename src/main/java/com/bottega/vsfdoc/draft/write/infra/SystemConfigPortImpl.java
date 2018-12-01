package com.bottega.vsfdoc.draft.write.infra;

import com.bottega.vsfdoc.draft.write.domain.ports.SystemConfigPort;
import com.bottega.vsfdoc.draft.write.domain.ports.SystemType;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigPortImpl implements SystemConfigPort {

	@Override
	public SystemType getSystemType() {
		return SystemType.QEP; // TODO read from env
	}

	@Override
	public boolean isDemo() {
		return false; // TODO read from env
	}
}
