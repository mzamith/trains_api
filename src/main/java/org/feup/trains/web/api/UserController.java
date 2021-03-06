package org.feup.trains.web.api;

import javax.servlet.http.HttpServletRequest;

import org.feup.trains.dto.AccountDTO;
import org.feup.trains.model.Account;
import org.feup.trains.service.UserService;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

    @RequestMapping("/inspector/test") /* Maps to all HTTP actions by default (GET,POST,..)*/
    public @ResponseBody
    String getUsers() {
        return "hello";
    }
    
    
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account) {
        logger.info("> createAccount");
        
        try{
        	
        	String pw = account.getPassword();
        	Account savedAccount = userService.create(account);
        	logger.info("< createAccount");
		
       	
            return new ResponseEntity<Account>(savedAccount, HttpStatus.CREATED);
            
        }catch(DataIntegrityViolationException SQLe){
        	
        	//Check for duplicate Key in Request (duplicate name)
        	
        	//From mySQL Docs:
        	//Error: 1022 SQLSTATE: 23000 (ER_DUP_KEY)
        	//Message: Can't write; duplicate key in table '%s'
        	        	
        	if (SQLe.getCause() instanceof JDBCException){
        	
	            if (((JDBCException) SQLe.getCause()).getSQLException().getSQLState().equals("23000") ) {
	        		return new ResponseEntity<Account>(HttpStatus.CONFLICT);
	        	}else return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        	}else{
        		return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        	}
        	
        }catch(Exception e){
        	logger.info("> createAccount");
        	logger.error(e.getMessage());
        	return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(
            value = "/api/profile",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getProfile(HttpServletRequest request) {
        logger.info("> getProfile");

        AccountDTO account = userService.getProfile(request);

        logger.info("< getProfile");
        return new ResponseEntity<AccountDTO>(account,
                HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/api/profile",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> updateExam(
            @RequestBody AccountDTO account, HttpServletRequest request) {
        logger.info("> updateAccount username:{}", account.getUsername());
        
		try {
			
			AccountDTO updatedAccount = userService.updateProfile(account, request);
	        if (updatedAccount == null) {
	            return new ResponseEntity<AccountDTO>(
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        logger.info("< updateAccount username:{}", account.getUsername());
	        return new ResponseEntity<AccountDTO>(updatedAccount, HttpStatus.OK);

		}  catch (Exception e) {
			logger.info("> updateAccount");
			logger.error(e.getMessage());
			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
		}
    }


}
