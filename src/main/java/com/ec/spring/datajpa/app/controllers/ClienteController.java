package com.ec.spring.datajpa.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ec.spring.datajpa.app.models.entities.Cliente;
import com.ec.spring.datajpa.app.models.service.IClienteService;
import com.ec.spring.datajpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService iClienteDAO;
	
	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listAllClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		Pageable pageable = PageRequest.of (page, 3);
		
		Page<Cliente> clientes = iClienteDAO.findAll(pageable);
		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
		
		model.addAttribute("title", "Listado de Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		
		return "listar";
	}
	
	@GetMapping(value = "/form")
	public String createCliente(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("title", "Formulario de Cliente");
		model.put("cliente", cliente); 
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String saveCliente(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus sessionStatus) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Formulario de Cliente");
			return "form";
		}
		
		String messageFlash = (cliente.getIdCliente() != null) ? "Cliente Editado con Exito" : "Cliente Creado con Exito";
		
		iClienteDAO.save(cliente);
		sessionStatus.setComplete();
		flash.addFlashAttribute("sucess", messageFlash);
		return "redirect:listar";			
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editCliente(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		
		if (id > 0) {
			cliente = iClienteDAO.findClientebyID(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "Cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del Cliente es nulo");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("title", "Editar Cliente");
		return "form";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteCliente(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		if (id > 0) {
			iClienteDAO.delete(id); 
			flash.addFlashAttribute("sucess", "Cliente Eliminado con Exito");
		}
		return "redirect:/listar";
	}
}
