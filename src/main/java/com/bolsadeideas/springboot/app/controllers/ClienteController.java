package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.dao.ClienteDaoImpl;
import com.bolsadeideas.springboot.app.models.entity.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.service.IclienteService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IclienteService clienteService;
	
	@RequestMapping(value = "/listar",method = RequestMethod.GET )
	public String listar(Model model) {
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("titulo", "Listando la lista de clientes");
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Listado Clientes");
		return "form";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
		}else {
			return "redirect:listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Listado Clientes");
		
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado Clientes");
			return "form";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliinar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			clienteService.eliminar(id);
		}
		return "redirect:/listar";
	}

}
