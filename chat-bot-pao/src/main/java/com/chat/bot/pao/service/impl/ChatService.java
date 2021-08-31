package com.chat.bot.pao.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.bot.pao.model.dto.ChatDTO;

@Service
public class ChatService {
	
	private Logger log = LoggerFactory.getLogger(ChatService.class);
	
	@Autowired 
	private KieContainer kieContainer;

	public String getResponse(ChatDTO chatDto) {
		log.info("[Obteniendo respuesta al chat - Inicio]");
		KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("chat", chatDto);
        kieSession.insert(chatDto);
        kieSession.fireAllRules();
        kieSession.dispose();
        return chatDto.getRespuesta();
	}
	
	
}
