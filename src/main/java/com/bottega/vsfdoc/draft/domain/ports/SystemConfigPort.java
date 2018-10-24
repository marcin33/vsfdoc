package com.bottega.vsfdoc.draft.domain.ports;

public interface SystemConfigPort {
	SystemType getSystemType();

	boolean isDemo();
}
