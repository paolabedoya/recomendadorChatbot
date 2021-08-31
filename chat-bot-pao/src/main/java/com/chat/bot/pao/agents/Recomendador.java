package com.chat.bot.pao.agents;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.util.ObjectUtils;

import com.chat.bot.pao.agents.util.AgentFactory;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.model.dto.LibroDTO;

import akka.actor.UntypedActor;

@SuppressWarnings("deprecation")
@Named("recomendador")
@Scope("prototype")
public class Recomendador extends UntypedActor {

	private List<Libro> listLibros;

    public List<Libro> getListLibros() {
		return listLibros;
	}

	public void setListLibros(List<Libro> listLibros) {
		this.listLibros = listLibros;
	}

	public enum Mensaje {

    }

    private static final Logger log = LoggerFactory.getLogger(Recomendador.class);

    @Override
    public void onReceive(Object o) {
        log.info("[Recomendador] ha recibido el mensaje: \"{}\".", o);
        LibroDTO libro = (LibroDTO) o;
        
        if(ObjectUtils.isEmpty(libro.getListLibros())) {
        	AgentFactory.buscador.tell(libro, getSelf());
        } else if(!ObjectUtils.isEmpty(libro.getListLibros())) {
//        	getContext().stop(getSelf());
        	this.setListLibros(libro.getListLibros());
        } else {
            unhandled(o);
        }
    }

}
