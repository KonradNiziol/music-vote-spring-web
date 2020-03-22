package com.example.music.server.services;

import com.example.music.server.dto.TrackCreateDto;
import com.example.music.server.dto.TrackDto;
import com.example.music.server.exceptions.AppException;
import com.example.music.server.model.Track;
import com.example.music.server.model.tools.Mappers;
import com.example.music.server.repository.TrackRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepo trackRepo;

    public boolean addTrack(TrackCreateDto trackCreateDto){
        if (trackCreateDto == null){
            return false;
        }
        Track track = trackRepo.save(Mappers.fromTrackDtoToTrack(trackCreateDto));
        return track!=null;
    }

    public List<TrackDto> getTracks(){
        return trackRepo.findAll().stream()
                .map(Mappers::fromTrackToTrackDto)
                .collect(Collectors.toList());
    }

    public Set<String> getAllExistArtist(){
        return trackRepo.findAll().stream()
                .map(Track::getArtist)
                .collect(Collectors.toSet());
    }

    public Track getTrackById(Long id){
        Optional<Track> optionalTrack = trackRepo.findById(id);
        return optionalTrack.orElseThrow(()-> new AppException(String.format("Track with id: %s don't exist!", id)));
    }

    public void removeTrack(Long id){
        trackRepo.deleteById(id);
    }

}
