package org.dsu.controller.rest;

import javax.validation.Valid;

import org.dsu.json.VoteJSON;
import org.dsu.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
	
	@Autowired
	private VoteService voteService;

	@RequestMapping(value = "/api/v1/vote", method = RequestMethod.POST)
    public VoteJSON vote(@Valid @RequestBody VoteJSON dto) {
        voteService.vote(dto);
        return dto;
    }
}
