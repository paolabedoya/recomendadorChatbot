package com.chat.bot.pao.agents;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.chat.bot.pao.service.impl.BotService;

import akka.actor.UntypedActor;

@Named("bot")
@Scope("prototype")
public class Bot extends UntypedActor {
 
    public enum Mensaje {
        OBTENER_DATOS
    }
 
    private static final Logger log = LoggerFactory.getLogger(Bot.class);
    private final BotService botService;
 
    @Inject
    public Bot(BotService service) {
        this.botService = service;
    }
 
 
    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Bot] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.OBTENER_DATOS) {
            log.info("[Bot] está obteniendo datos...");
            botService.obtenerMinerales();
            log.info("[Bot] ha obtenido datos.");
           // getSender().tell(Buscador.Mensaje.MATERIALES, getSelf());
        } else {
            unhandled(o);
        }
    }
}
