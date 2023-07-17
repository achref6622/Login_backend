package com.test.contro;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.entities.users;
import com.test.securite.JWTResponse;
import com.test.securite.TokenUtils;
import com.test.service.UserService;


@RestController
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = { "/login" })
		public JWTResponse singIn(@RequestParam String userName , @RequestParam String password) throws Exception {
		log.info("authentication >> ");
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, password ));

		} catch (DisabledException dis) {
			return new JWTResponse(null, null, null);
		} catch (BadCredentialsException e) {
			return new JWTResponse(null, null, null);
			}

		log.info("authentication >> " + authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			log.info("authentication >> " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = userService.loadUserByUsername(userName);
			Optional<users> user = userService.findByUserName(userName);
//System.out.println(tokenUtils.generateToken(userDetails));
//
			

		String token = tokenUtils.generateToken(userDetails);
;
			return new JWTResponse(token, userName ,  user.get().getRoles());
		}

	return new JWTResponse(null, null, null);
	}
	@PostMapping("/users")
	private void adduser (@RequestBody users u) {
userService.addUser(u) ;
	}
	@GetMapping("/users")
	public List<users> getAllUsers() {
		return userService.getAllUsers();
	}

}