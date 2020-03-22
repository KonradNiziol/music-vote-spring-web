package com.example.music.server.dto;

import com.example.music.server.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackDto {

    private Long id;
    private String title;
    private String artist;
    private Genre genre;
}
