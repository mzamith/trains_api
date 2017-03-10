package org.feup.trains.security.jwt;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.feup.trains.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenAuthenticationService {

     private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
     private String secret = "ThisIsASecret";
     private String tokenPrefix = "Bearer";
     private String headerString = "Authorization";
     
     private final UserDetailsService userService;
     
     public TokenAuthenticationService() {
    	 this.userService = new UserService();
     }
     
     
     public TokenAuthenticationService(UserDetailsService userService) {
         this.userService = userService;
     }
     
     public void addAuthentication(HttpServletResponse response, String username) {
         // We generate a token now.
         String JWT = Jwts.builder()
             .setSubject(username)
             .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
             .signWith(SignatureAlgorithm.HS512, secret)
             .compact();
         response.addHeader(headerString, tokenPrefix + " " + JWT);
     }

     public Authentication getAuthentication(HttpServletRequest request) {
         String token = request.getHeader(headerString);
         if (token != null) {
             // parse the token.
             String username = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
             
             
             if (username != null) // we managed to retrieve a user
             {
                 return new AuthenticatedUser(username);
             }
         }
         return null;
     }
     
     public UserDetails getAuthenticatedUser(HttpServletRequest request) {
         String token = request.getHeader(headerString);
         if (token != null) {
             // parse the token.
             String username = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
             
             
             if (username != null) // we managed to retrieve a user
             {
                 return userService.loadUserByUsername(username);
             }
         }
         return null;
     }
     
     public boolean isValidInspector(AccountCredentials credentials){
    	 
    	 User user = (User) userService.loadUserByUsername(credentials.getUsername());
    	 
    	 for (GrantedAuthority authority: user.getAuthorities()){
    		 if (authority.getAuthority().equals("ROLE_INSPECTOR")) return true;
    	 }
    	 return false;
    	 
     }
     
     public boolean isValidInspector(Authentication credentials){
    	 
    	 User user = (User) userService.loadUserByUsername(credentials.getName());
    	 
    	 for (GrantedAuthority authority: user.getAuthorities()){
    		 if (authority.getAuthority().equals("ROLE_INSPECTOR")) return true;
    	 }
    	 return false;
    	 
     }
 }