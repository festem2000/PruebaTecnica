package com.pruebaTecnica.model;

public class Product {
	
	private int id;
	private String title;
	private String description;
	private int valor;
	private int units;
	
	
	// CONSTRUCTOR WITH PARAMETERS
	public Product(int id, String title, String description, int valor, int units) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.valor = valor;
		this.units = units;
	}

	// CONSTRUCTOR WITHOUT PARAMETERS
	public Product() {
	}
	
	// METHODS GET AND SET OF ATTRIBUTES
	
	
	
	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	
	
	

}
