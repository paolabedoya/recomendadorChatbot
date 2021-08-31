package com.chat.bot.pao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.chat.bot.pao.agents.util.AgentFactory;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.model.PalabrasClave;
import com.chat.bot.pao.model.dto.ChatDTO;
import com.chat.bot.pao.model.dto.LibroDTO;
import com.chat.bot.pao.service.LibroService;
import com.chat.bot.pao.service.impl.ChatService;

@Controller
public class ConsultaLibroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8948123656105059208L;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AgentFactory agentFactory;

	@Autowired
	private LibroService libroService;

	@Autowired
	private ChatService chatService;

	@Autowired
	@Qualifier("PalabrasClaves")
	private List<PalabrasClave> palabrasClaves;

	List<Libro> LIST_LIBROS = new ArrayList();

	List<Libro> LIST_LIBROS_RECOMENDADOS = new ArrayList();

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("listRecomendados", libroService.obtenerListadoRecomendados());
		return "index";
	}

	@GetMapping("/consulta/libro/{textSearch}/{parampantalla}")
	public String consultarLibro(@PathVariable("textSearch") String textSearch,
			@PathVariable("parampantalla") String parampantalla, Model model, HttpServletRequest request) {
		log.info("Search: " + textSearch);
		if (!agentFactory.existsAgent()) {
			agentFactory.initAgent();
		}
		LibroDTO libroDTO;
		switch (parampantalla) {
		case "init":
			libroDTO = libroService.obtenerLibrosByNombre(textSearch);
			if (!ObjectUtils.isEmpty(libroDTO.getListLibrosRecomendados())) {
				LIST_LIBROS_RECOMENDADOS = libroDTO.getListLibrosRecomendados();
			}
			LIST_LIBROS = libroDTO.getListLibros();
			break;
		case "send":

			List<String> listPalabrasClaves = new ArrayList<>();
			int contarPalabra = 0;
			for (PalabrasClave palabrasClave : palabrasClaves) {
				if (contarPalabra < 5 && palabrasClave.getNombreClave().contains(textSearch)) {
					listPalabrasClaves.add(palabrasClave.getNombreClave());
					contarPalabra++;
				}
			}
			if (!ObjectUtils.isEmpty(listPalabrasClaves) && listPalabrasClaves.size() < 5) {
				String palabraClave = "";
				for (String palabra : listPalabrasClaves) {
					palabraClave = palabraClave + " " + palabra;
				}
				libroDTO = libroService.obtenerLibrosByNombre(palabraClave.trim());
				if (!ObjectUtils.isEmpty(libroDTO.getListLibrosRecomendados())) {
					LIST_LIBROS_RECOMENDADOS = libroDTO.getListLibrosRecomendados();
				}
				LIST_LIBROS = libroDTO.getListLibros();
			}

			break;
		default:
			break;
		}
		model.addAttribute("lstLibros", LIST_LIBROS_RECOMENDADOS);
		model.addAttribute("chatDto", new ChatDTO());
		return "consultar :: listResult";
	}

	@GetMapping("/chatbot/index/")
	public String cargarChat(HttpServletRequest request, Model model) {
		StringBuilder mensajeChat = new StringBuilder();
		if (!ObjectUtils.isEmpty(LIST_LIBROS)) {
			mensajeChat.append("Hola, espero estes bien, he encontrado el siguiente listado de libros: \n");
			LIST_LIBROS.forEach(libro -> {
				mensajeChat.append(libro.getNombreLibro());
				if (LIST_LIBROS.size() > 1) {
					mensajeChat.append(",");
				} else {
					mensajeChat.append("\t\t");
				}
			});
		} else {
			mensajeChat.append("Lo lamento no he  encontrado alguna conincidencia exacta con tu busqueda. ");
		}

		if (!ObjectUtils.isEmpty(LIST_LIBROS_RECOMENDADOS)) {
			mensajeChat.append("\nTe suguiero revises el listado de recomendaciones.");
		}
		log.info(mensajeChat.toString());
		model.addAttribute("respuesta", mensajeChat.toString());
		model.addAttribute("lstLibros", LIST_LIBROS_RECOMENDADOS);
		model.addAttribute("chatDto", new ChatDTO());
		return "consultar";
	}

	@RequestMapping(value = "/chat")
	public String chat(@ModelAttribute("chatDto") ChatDTO chatDto, Model model) {

		model.addAttribute("respuesta", chatService.getResponse(chatDto));
		return "consultar :: fragment";
	}

	@RequestMapping(value = "/consultar/historial")
	public String consultarHistorial(Model model) {
		model.addAttribute("listHistorial", libroService.obtenerListadoHistorico());
		return "historicobusqueda";
	}

}
