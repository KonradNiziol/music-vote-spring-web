package com.example.music.server.repository;

import com.example.music.server.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Vote, Long> {
}
