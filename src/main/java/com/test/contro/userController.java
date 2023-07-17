package com.test.contro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entities.users;
import com.test.repo.userrepos;


@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class userController {
@Autowired
	private userrepos userrepos ; 
@PostMapping	
private void adduser (@RequestBody users u) {

	userrepos.save(u) ;
}
@GetMapping 
private List<users> getusers () {
	return userrepos.findAll() ;
}
	
	
}
