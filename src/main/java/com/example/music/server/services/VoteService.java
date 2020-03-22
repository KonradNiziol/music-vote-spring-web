package com.example.music.server.services;

import com.example.music.server.dto.VoteDto;
import com.example.music.server.model.Track;
import com.example.music.server.model.User;
import com.example.music.server.model.Vote;
import com.example.music.server.repository.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepo voteRepo;
    private final UserService userService;
    private final TrackService trackService;

    public boolean addVote(VoteDto voteDto){
        User user = userService.getUserById(voteDto.getUserId());
        Track track = trackService.getTrackById(voteDto.getTrackId());
        Vote vote = voteRepo.save(Vote.builder()
                .track(track)
                .user(user).voteDate(voteDto.getVoteDate())
                .build());
        return vote != null;
    }
}
