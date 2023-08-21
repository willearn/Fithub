package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.announcement.IAnnouncementService;

@RestController
public class AnnouncementcController {

	@Autowired IAnnouncementService iAnnouncementService;
	
}
