package com.pruebaTecnica.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pruebaTecnica.model.Account;
import com.pruebaTecnica.model.Client;
import com.pruebaTecnica.model.Product;

public class FacturaDAO {
	
	/**
	 * Declaración de lista de entidades
	 * Se simula los datos de la tienda
	 **/
	private List<Product> products;
	private List<Account> accounts;
	private List<Client> clientes;
	
	/**
	 * Constructor de la clase FacturaDAO
	 * Se inicializan las listas y se agregan valor para la simulación
	 **/
	public FacturaDAO() {
		//INICIALIZACIÓN DE VARIABLES
		products = new ArrayList<Product>();
		accounts = new ArrayList<Account>();
		clientes = new ArrayList<Client>();
		
		//SE AGREGAN DATOS DE PRODUCTOS
		products.add(new Product(1,"Mouse Gamming", "Mouse para jugar", 50000, 0));
		products.add(new Product(2,"Teclado Gamming", "Teclado para inalambrico", 20000, 0));
		products.add(new Product(3,"Monitor 24 pulgadas", "Monitor full HD", 550000, 0));
		products.add(new Product(4,"Monitor 22 pulgadas", "Monitor full HD", 450000, 0));
		products.add(new Product(5,"Audifonos inalambricos", "Audifonos de diadema", 30000, 0));
		
		//SE AGREGAN DATOS DE CLIENTES
		clientes.add(new Client("12345", "Kra 82-21"));
		
		//SE AGREGAN DATOS DE UNA FACTURA
		accounts.add(new Account(clientes.get(0), (ArrayList<Product>) products));
		accounts.get(0).setId(123);
	}
	
	/**
	 * Método que devuelve todo los productos 
	 **/
	public List<Product> getProducts(){
		return this.products;
	}
	
	/**
	 * Método que devuelve todas las facturas 
	 **/
	public List<Account> getAccounts(){
		return this.accounts;
	}
	
	/** 
	 * Método que devuelve todos los clientes
	 **/
	public List<Client> getCLients(){
		return this.clientes;
	}
	
	/**
	 *  Recibe como parametro un cliente que buscará en el arreglo si se encuentra registrado por la cedula
	 *  y si no se encuentra se agrega a la lista
	 *  
	 * @param cliente Es de tipo Cliente
	 */
	public void agregarCliente(Client cliente) {
		
		boolean verificar = false;
		for (Client aux : this.clientes) {
			if(cliente.getId().equalsIgnoreCase(aux.getId())) {
				verificar = true;
				break;
			}
		}
		if(verificar==false) {
			this.clientes.add(cliente);
		}
	}
	
	// Metodo para encontrar una factura por id
	public Account findAccount(int id){
		
		for (Account account : this.accounts) {
		
			if(account.getId() == id) {
				return account;
			}
		}
		return null;
	}
	
	
	/**
	 * Genera la factura de los productos que realiza o pide un cliente.
	 * Agrega esta factura a la lista para tener un inventario (Simulación)
	 * @param account Trae la información de los productos y del cliente
	 * 
	 * @return Retorna la factura con el valor total del pedido
	 */
	public Account addAccount(Account account) {
		
		double valorTotal = 0;
		//Suma el valor de cada producto y sus unidades
		for (Product product : account.getProducts()) {
			valorTotal = valorTotal + (product.getValor()*product.getUnits());
		}
		
		valorTotal = validarValorTotal(valorTotal, account.getValue_iva(), account.getDomicilioValue());
		
		account.setTotalValue(valorTotal);
		
		this.accounts.add(account);
		
		return account;
	}
	
	
	/**
	 * Método auxiliar para validar el valor total de la factura según ciertas condiciones dadas
	 * 
	 * @param valorTotal Es la suma total del valor de los productos
	 * @param valorIva Es el valor del iva que se le aplica a los productos
	 * @param valorDomicilio Es el valor del domicilio para llevar los productos al cliente
	 * 
	 * @return Retorna el valor total de la factura
	 */
	public double validarValorTotal(double valorTotal, double valorIva, double valorDomicilio) {
		
		if(valorTotal >= 70000 && valorTotal < 100000) {
			return valorTotal + (valorTotal*valorIva) + valorDomicilio  ;
		}
		else if(valorTotal >= 100000){
			return (valorTotal*valorIva) + valorTotal;
		}
		else
			//Se supone. Si el valor es menor a $70.000 solo se le suma el valor del domicilio
			return valorTotal + valorDomicilio;
	}
	
	
	/**
	 *  Método para editar una factura que el cliente desee
	 * 
	 * @param account Se pasa una factura con los productos que el cambio y y la información del cliente
	 * 
	 * @return Retorna Verdadero (true) si se pudo editar de lo contrario retorna Falso (false)
	 */
	public boolean editAccount(Account account) {
		
		double valorTotal = 0;
		for (Account ac : accounts) {
			if(ac.getId() == account.getId()) {
				
				if(calculoFecha(ac.getDate())<= 5) {
					for (Product product : account.getProducts()) {
						valorTotal = valorTotal + (product.getValor()*product.getUnits());
					}	
					if(valorTotal >= ac.getTotalValue()) {
						ac.setProducts(account.getProducts());
						ac.setTotalValue(validarValorTotal(valorTotal, account.getValue_iva(), account.getDomicilioValue()));
						ac.setEstado(true);
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}
	
	
	/**
	 *  Se realiza el calculo de diferencia de horas para el pedido
	 * @param date Fecha 
	 * @return Retorna la diferencia en horas del pedido a la fecha actual
	 */
	public double calculoFecha(Date date) {
		
		Long diferencia = ((new Date().getTime()) - (date.getTime()));
		
		//Se realiza una conversion a horas
		diferencia = diferencia/(1000*60*60);
		
		return (double) diferencia;
		
	}
	
	/**
	 *  Método para eliminar una factura como tal no se elimina la factura dado que debe de quedar un registro de esta
	 *  por ende solo se cambia el estado de la misma para simular la eliminación de la factura 
	 * @param id Es el id de la factura que se desea eliminar
	 * @return Retorna exito si no tiene ningun costo y en caso de generar un costo adicional retorna el costo de lo contrario retorna null
	 */
	public String deleteAccount(int id) {
		
		for (Account ac : accounts) {
			if(ac.getId() == id) {
				if(calculoFecha(ac.getDate())<= 12) {
					ac.setEstado(false);
					return "exito";
				}
				else{
					ac.setEstado(false);
					return "" +  ac.getTotalValue()*0.10;
				}
			}
		} 
		return null;
	}
	
	
	
	
	
	
	
}
