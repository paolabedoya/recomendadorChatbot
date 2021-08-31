package com.chat.bot.pao.agents.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.chat.bot.pao.SpringExtension;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Component
public class AgentFactory {
	
	public static ActorRef recomendador;
	public static ActorRef buscador;
	public static ActorRef bot;
	
	@Autowired
    private ApplicationContext context;

	public void initAgent() {
		ActorSystem actorSystem = context.getBean(ActorSystem.class);

		recomendador = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("recomendador"),
				"recomendador");
		buscador = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("buscador"),
				"buscador");
		bot = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("bot"),
				"bot");
	}

	public boolean existsAgent() {
		return !ObjectUtils.isEmpty(recomendador) && 
				!ObjectUtils.isEmpty(buscador) && 
				!ObjectUtils.isEmpty(bot);
	}
}
