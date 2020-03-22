package com.example.music.web.controller;

import com.example.music.server.dto.ServerMessageDto;
import com.example.music.server.dto.TrackCreateDto;
import com.example.music.server.dto.VoteDto;
import com.example.music.server.model.enums.Genre;
import com.example.music.server.services.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/track")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("track", new TrackCreateDto());
        model.addAttribute("genre", Genre.values());
        model.addAttribute("tracks", trackService.getTracks());
        return "track/create";
    }

    @PostMapping("create")
    public String createTrack(@Valid @ModelAttribute TrackCreateDto trackCreateDto, BindingResult bindingResult, Model model){
        model.addAttribute("track", new TrackCreateDto());
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("errors", errors);

        } else if (trackService.addTrack(trackCreateDto)){
            model.addAttribute("success", new ServerMessageDto("The user has been correctly added"));
        }
        return "redirect:/track/create";
    }

    @PostMapping("remove")
    public String remove(@RequestParam Long id){
        trackService.removeTrack(id);
        return "redirect:/track/create";
    }

    @PostMapping("vote")
    public String getTracks(@RequestParam Long id, Model model){
        model.addAttribute("vote", VoteDto.builder().userId(id).build());
        model.addAttribute("tracks", trackService.getTracks());
        return "vote/tracks";
    }
}
