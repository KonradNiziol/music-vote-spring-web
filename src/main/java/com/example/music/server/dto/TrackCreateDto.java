package com.example.music.server.dto;

import com.example.music.server.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackCreateDto {

    @NotNull
    private String title;
    @NotNull
    private String artist;
    private Genre genre;
}
