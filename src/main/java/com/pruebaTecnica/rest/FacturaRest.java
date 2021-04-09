package com.pruebaTecnica.rest;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTecnica.dao.FacturaDAO;
import com.pruebaTecnica.model.Account;

@RestController
@RequestMapping(path="/home", method= RequestMethod.POST)
public class FacturaRest {
	
	private FacturaDAO facturaDAO = new FacturaDAO();
	
	@GetMapping("/listar")
	public List<Account> getFacturas(){
		return facturaDAO.getAccounts();
	}
	
	@PostMapping(path="/generarFactura", consumes = "application/json")
	public Account generarFactura(@RequestBody Account account){
		
		System.out.println(account.getClient().getId() + "  " + account.getId());
		
		account = facturaDAO.addAccount(account);
		
		return account;
		
	}
	
	@PostMapping(path="/editarFactura/{id}", consumes = "application/json")
	public String editarFactura(@RequestBody Account account, @PathVariable("id") Integer id){
		
		boolean verificarEdit = false;
		account.setId(id);
		if(facturaDAO.findAccount(id) != null) {
			verificarEdit = facturaDAO.editAccount(account);	
		}
		
		if(verificarEdit) {
			return "Se edito correctamnete la factura #" +id;
		}
		return "No se pudo editar la factura #" +id;
	}
	
	@SuppressWarnings("unused")
	@PostMapping(path="/eliminarFactura/{id}", consumes = "application/json")
	public String eliminarFactura(@PathVariable("id") Integer id){
		

		String verificarDelete = facturaDAO.deleteAccount(id);	
		
		
		if(verificarDelete.equals("exito"))
			return "Se elimino tu pedido sin ningun cobro adicional";
		else if(verificarDelete != null) 
			return "Se elimino tu pedido pero se genero un cobro adicional por la cancelación \n Debes de pagar: " + verificarDelete;
		else
			return "No se pudo elimin la factura #" +id;
	}
	
	
	
}
