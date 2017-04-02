package org.feup.trains.dto;

import java.util.Date;

import org.feup.trains.model.Account;

public class AccountDTO {

	private String username;
	
	private String cardNumber;
	
	private Date cardDate;
	
	public AccountDTO(){}
	
	public AccountDTO(Account account){
		this.username = account.getUsername();
		this.cardNumber = account.getCardNumber();
		this.cardDate = account.getCardDate();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getCardDate() {
		return cardDate;
	}

	public void setCardDate(Date cardDate) {
		this.cardDate = cardDate;
	}	
	
}
