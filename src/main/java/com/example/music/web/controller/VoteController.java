package com.example.music.web.controller;

import com.example.music.server.dto.VoteDto;
import com.example.music.server.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("date")
    public String addDateToVote(@ModelAttribute VoteDto vote, Model model){
        model.addAttribute("vote", vote);
        return "vote/date";
    }

    @PostMapping("add")
    public String addVote(@ModelAttribute VoteDto vote){
        voteService.addVote(vote);
        return "index";
    }
}
