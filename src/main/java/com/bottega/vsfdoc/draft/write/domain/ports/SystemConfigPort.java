package com.bottega.vsfdoc.draft.write.domain.ports;

public interface SystemConfigPort {
	SystemType getSystemType();

	boolean isDemo();
}
