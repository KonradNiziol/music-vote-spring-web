package com.example.music.server.repository;

import com.example.music.server.model.ArtistOnly;
import com.example.music.server.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepo extends JpaRepository<Track, Long> {

    List<Track> findByArtist(String artist);

}
