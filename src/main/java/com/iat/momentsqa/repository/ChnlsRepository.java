package com.iat.momentsqa.repository;

import com.iat.momentsqa.model.Channels;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChnlsRepository extends JpaRepository<Channels, Long> {
    @Query
    Slice<Channels> findByChannelNameContainingIgnoreCase(String channelName, Pageable pageable);
}
