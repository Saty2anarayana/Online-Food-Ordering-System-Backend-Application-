package com.satya.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satya.Repository.CartRepository;
import com.satya.Repository.UserRepository;
import com.satya.entity.Cart;
import com.satya.entity.USER_ROLE;
import com.satya.entity.UserEntity;
import com.satya.request.LoginRequest;
import com.satya.response.AuthResponse;
import com.satya.security.JwtProvider;
import com.satya.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private static final String UserDetails = null;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomerUserDetailsService customerUserService;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserEntity user) throws Exception{
		
		UserEntity isEmailExist=userRepository.findByEmail(user.getEmail());
		if(isEmailExist!=null) {
			throw new Exception("Email is already used with another account");
		}
		
		UserEntity createUser=new UserEntity();
		createUser.setEmail(user.getEmail());
		createUser.setFullName(user.getFullName());
		createUser.setRole(user.getRole());
		createUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		UserEntity savedUser = userRepository.save(createUser);
		
		Cart cart=new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register success");
		authResponse.setRole(savedUser.getRole());
		
		
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req ){
		
		String username=req.getEmail();
		String password=req.getPassword();
		
	
		Authentication authentication=authenticate(username,password);
		Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		
		String jwt=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login success");
		
		
		
		authResponse.setRole(USER_ROLE.valueOf(role));
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
		
		
	
		
	}

	private Authentication authenticate(String username, String password){
		// TODO Auto-generated method stub
		
		UserDetails userDetails= customerUserService.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username..");
		}
		
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password..");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
		
	}
	
	
	

}
