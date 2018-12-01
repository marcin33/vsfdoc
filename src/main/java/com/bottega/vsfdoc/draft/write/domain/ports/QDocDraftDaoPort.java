package com.bottega.vsfdoc.draft.write.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QDocDraftDaoPort extends JpaRepository<QDocDraftRecord, UUID> {

	Optional<QDocDraftRecord> findById(UUID id);

}
