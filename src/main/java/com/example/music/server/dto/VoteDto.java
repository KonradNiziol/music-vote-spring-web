package com.example.music.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteDto {

    private Long userId = 0L;
    private Long trackId = 0L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate voteDate;

}
