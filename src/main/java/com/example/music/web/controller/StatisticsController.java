package com.example.music.web.controller;

import com.example.music.server.dto.StatisticsDto;
import com.example.music.server.dto.StatisticsTopArtistDto;
import com.example.music.server.dto.TrackTopDto;
import com.example.music.server.services.StatisticsService;
import com.example.music.server.services.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final TrackService trackService;

    @GetMapping("form")
    public String getStatistic(Model model){
        model.addAttribute("parameters", new StatisticsDto());
        model.addAttribute("parametersArtist", new StatisticsTopArtistDto());
        model.addAttribute("allArtists", trackService.getAllExistArtist());
        model.addAttribute("topGenre", statisticsService.getMostPopularTrackByGenre());
        model.addAttribute("ageToTrack",statisticsService.getHighlightsOfTrackToAge());
        return "statistics";
    }

    @PostMapping("form")
    public String createStatistics(Model model, @ModelAttribute StatisticsDto statisticsDto, @ModelAttribute StatisticsTopArtistDto statisticsTopArtistDto){
        model.addAttribute("parameters", new StatisticsDto());
        model.addAttribute("parametersArtist", new StatisticsTopArtistDto());
        model.addAttribute("allArtists", trackService.getAllExistArtist());
        if (statisticsDto.getNumberOfTopTrack()>0){
            model.addAttribute("mostPopularTrack", statisticsService.getMostPopularTrack(statisticsDto.getNumberOfTopTrack()));
        }
        model.addAttribute("topGenre", statisticsService.getMostPopularTrackByGenre());
        model.addAttribute("ageToTrack", statisticsService.getHighlightsOfTrackToAge());
        model.addAttribute("topArtistTrack", statisticsService.getMostPopularTrackForSelectedArtist(statisticsTopArtistDto.getNumberOfTrack(), statisticsTopArtistDto.getArtist()));
        return "statistics";
    }

}
