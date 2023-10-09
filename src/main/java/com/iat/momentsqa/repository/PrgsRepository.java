package com.iat.momentsqa.repository;

import com.iat.momentsqa.model.Programs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrgsRepository extends JpaRepository<Programs, Long> {
    @Query(value="DELETE FROM programs p WHERE p.program_id = ?1", nativeQuery = true)
    long deleteProgramById(Long programId);

    @Query
    Slice<Programs> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
