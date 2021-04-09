package com.pruebaTecnica.model;

import java.util.ArrayList;
import java.util.Date;

public class Account {
	
	private int id;
	private Client client;
	private ArrayList<Product> products;
	private double value_iva;
	private double totalValue;
	private double domicilioValue;
	private Date date;
	private boolean estado;

	
	// CONSTRUCTOR WITH PARAMETERS
	public Account(Client client, ArrayList<Product> products) {
		this.id = (int)(Math.random()*10000 + 1);
		this.client = client;
		this.products = products;
		this.value_iva = 0.19;
		this.totalValue = 0;
		// Se supone el valor del domicilio
		this.domicilioValue = 7000;
		this.date = new Date();
		this.estado = true;
	}


	// CONSTRUCTOR WITHOUT PARAMETERS
	public Account() {
		this.id = (int)(Math.random()*10000 + 1);
		// Se supone el valor del domicilio
		this.domicilioValue = 7000;
		this.value_iva = 0.19;
		this.totalValue = 0;
		this.date = new Date();
		this.estado = true;
	}

	// METHODS GET AND SET OF ATTRIBUTES
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public ArrayList<Product> getProducts() {
		return products;
	}


	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}


	public double getValue_iva() {
		return value_iva;
	}


	public void setValue_iva(double value_iva) {
		this.value_iva = value_iva;
	}


	public double getTotalValue() {
		return totalValue;
	}


	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}


	public double getDomicilioValue() {
		return domicilioValue;
	}


	public void setDomicilioValue(double domicilioValue) {
		this.domicilioValue = domicilioValue;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}
