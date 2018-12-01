package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import com.bottega.vsfdoc.shared.validation.ValidationException;
import com.bottega.vsfdoc.shared.validation.ValidationViolations;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

class QDocValidator {

	private List<QDocChecker> checkers = new ArrayList<>();

	QDocValidator notBlankContent(String newContent) {
		checkers.add(new NotBlankContentChecker(newContent));
		return this;
	}

	QDocValidator state(QDocState desireState) {
		checkers.add(new StateChecker(desireState));
		return this;
	}

	QDocValidator ownerIsNotVerifier(VerifierId verifierId) {
		checkers.add(new OwnerIsNotVerifierCheck(verifierId));
		return this;
	}

	QDocValidator departmentIdsNotEmpty(List<DepartmentId> departmentIds) {
		checkers.add(new DepartmentIdsNotEmptyChecker(departmentIds));
		return this;
	}

	QDocValidator departmentsNotEmpty() {
		checkers.add(new DepartmentsNotEmptyChecker());
		return this;
	}

	QDocValidator verifierNotEmpty() {
		checkers.add(new VerifierNotEmpty());
		return this;
	}

	QDocValidator notBlankDeclineNote(String declineNote) {
		checkers.add(new NotBlankDeclineNote(declineNote));
		return this;
	}

	void validate(QDocDraft qDocDraft) {
		requireNonNull(qDocDraft, "qDocDraft is required");

		ValidationViolations violations = new ValidationViolations();

		checkers.forEach(c -> c.check(violations, qDocDraft));

		if (violations.isNotEmpty()) {
			throw new ValidationException(qDocDraft.getQDocId().value(), violations);
		}
	}

	private interface QDocChecker {

		void check(ValidationViolations violations, QDocDraft qDocDraft);
	}

	@RequiredArgsConstructor
	private static class OwnerIsNotVerifierCheck implements QDocChecker {

		private final VerifierId verifierId;

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (qDocDraft.getOwnerId().isSame(verifierId)) {
				violations.append("owner cannot be same with verifier");
			}
		}
	}

	@RequiredArgsConstructor
	private static class NotBlankContentChecker implements QDocChecker {

		private final String newContent;

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (isBlank(newContent)) {
				violations.append("not empty content is required");
			}
		}
	}

	@RequiredArgsConstructor
	private static class StateChecker implements QDocChecker {

		@NonNull
		private final QDocState desireState;

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (desireState != qDocDraft.getState()) {
				violations.append(String.format("this operation requires %s state, actual is %s", desireState, qDocDraft.getState()));
			}
		}
	}

	@RequiredArgsConstructor
	private static class DepartmentIdsNotEmptyChecker implements QDocChecker {

		@NonNull
		private final List<DepartmentId> departmentIds;

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (departmentIds == null || departmentIds.isEmpty()) {
				violations.append("not empty departments are required");
			}
		}
	}

	@RequiredArgsConstructor
	private static class DepartmentsNotEmptyChecker implements QDocChecker {

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (qDocDraft.getDepartments().isEmpty()) {
				violations.append("not empty departments are required");
			}
		}
	}

	private static class VerifierNotEmpty implements QDocChecker {

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (qDocDraft.getVerifierId() == null) {
				violations.append("not empty verifier is required");
			}
		}
	}

	@RequiredArgsConstructor
	private static class NotBlankDeclineNote implements QDocChecker {

		@NonNull
		private final String declineNote;

		@Override
		public void check(ValidationViolations violations, QDocDraft qDocDraft) {
			if (isBlank(declineNote)) {
				violations.append("not empty decline note is required");
			}
		}
	}
}
