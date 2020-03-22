package com.example.music.server.model.tools;

import com.example.music.server.dto.*;
import com.example.music.server.model.Track;
import com.example.music.server.model.User;
import com.example.music.server.model.Vote;

public interface Mappers {

    static User fromUserCreateDtoToUser(UserCreateDto userCreateDto){
        return User.builder()
                .name(userCreateDto.getName())
                .surname(userCreateDto.getSurname())
                .age(userCreateDto.getAge())
                .build();
    }

    static UserDto fromUserToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .build();
    }

    static Track fromTrackDtoToTrack(TrackCreateDto trackDto){
        return Track.builder()
                .title(trackDto.getTitle())
                .artist(trackDto.getArtist())
                .genre(trackDto.getGenre())
                .build();
    }

    static TrackDto fromTrackToTrackDto(Track track){
        return TrackDto.builder()
                .artist(track.getArtist())
                .genre(track.getGenre())
                .title(track.getTitle())
                .id(track.getId())
                .build();
    }

    static TrackTopDto fromTrackToTrackTopDto(Track track){
        return TrackTopDto.builder()
                .artist(track.getArtist())
                .genre(track.getGenre())
                .title(track.getTitle())
                .numberOfVoices(track.getVotes() != null ? track.getVotes().size() : 0L)
                .build();
    }



}
