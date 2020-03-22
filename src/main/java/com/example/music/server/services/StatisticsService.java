package com.example.music.server.services;


import com.example.music.server.dto.TrackTopDto;
import com.example.music.server.dto.UserDto;
import com.example.music.server.model.Track;
import com.example.music.server.model.User;
import com.example.music.server.model.Vote;
import com.example.music.server.model.enums.Genre;
import com.example.music.server.model.tools.Mappers;
import com.example.music.server.repository.TrackRepo;
import com.example.music.server.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

    private final TrackRepo trackRepo;
    private final UserRepo userRepo;

    public List<TrackTopDto> getMostPopularTrack(int numberOfTrack){
        return trackRepo.findAll().stream()
                .sorted(Comparator.comparing(track -> track.getVotes().size(), Comparator.reverseOrder()))
                .limit(numberOfTrack)
                .map(Mappers::fromTrackToTrackTopDto)
                .collect(Collectors.toList());
    }

    public List<TrackTopDto> getMostPopularTrackForSelectedArtist(int numberOfTrack, String artist){
        return trackRepo.findByArtist(artist).stream()
                .sorted(Comparator.comparing(track -> track.getVotes().size(), Comparator.reverseOrder()))
                .limit(numberOfTrack)
                .map(Mappers::fromTrackToTrackTopDto)
                .collect(Collectors.toList());
    }

    public List<TrackTopDto> getMostPopularTrackByGenre(){
        return trackRepo.findAll().stream()
                .collect(Collectors.groupingBy(track -> track.getGenre()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        genreListEntry -> genreListEntry.getKey(),
                        genreListEntry -> genreListEntry
                                .getValue().stream()
                                .reduce((trackA, trackB)-> reduceTrackByVote(trackA, trackB))))
                .values().stream()
                .filter(track -> track.isPresent())
                .map(track -> Mappers.fromTrackToTrackTopDto(track.get()))
                .collect(Collectors.toList());

    }

    public Map<Integer, TrackTopDto> getHighlightsOfTrackToAge(){
        return userRepo.findAll().stream().collect(Collectors.groupingBy(user -> user.getAge(),
                Collectors.flatMapping(user -> user.getVotes().stream().map(Vote::getTrack), Collectors.groupingBy(Function.identity(), Collectors.counting()))))
                .entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), mapEntry -> mapEntry.getValue().entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(entry -> TrackTopDto.builder()
                        .artist(entry.getKey().getArtist())
                        .title(entry.getKey().getTitle())
                        .genre(entry.getKey().getGenre())
                        .numberOfVoices(entry.getValue())
                        .build())
                .orElseGet(()->TrackTopDto.builder()
                        .artist("")
                        .title("")
                        .numberOfVoices(0L).build())));
    }

    private Track reduceTrackByVote(Track trackA, Track trackB){
        return trackA.getVotes().size() >= trackB.getVotes().size() ? trackA : trackB;
    }

}
