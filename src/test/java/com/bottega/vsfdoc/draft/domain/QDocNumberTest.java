package com.bottega.vsfdoc.draft.domain;

import com.bottega.vsfdoc.draft.domain.ports.SystemType;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class QDocNumberTest {

	@Test
	public void should_generate_number() {

		QDocNumber qDocNumber = new QDocNumber(SystemType.ISO, true, true, LocalDate.of(2018, 11, 11), 123L);

		assertThat(qDocNumber.getValue()).isEqualTo("DEMO/ISO/123/11/2018/AUDIT");
	}
}