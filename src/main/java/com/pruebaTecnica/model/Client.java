package com.pruebaTecnica.model;

public class Client {
	
	private String id;
	private String address;
	
	
	// CONSTRUCTOR WITH PARAMETERS
	public Client(String id, String address) {
		this.id = id;
		this.address = address;
	}
	
	
	// CONSTRUCTOE WITHOUT PARAMETERS
	public Client() {
	}

	// METHODS GET AND SET OF ATTRIBUTE

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
		
	

}
