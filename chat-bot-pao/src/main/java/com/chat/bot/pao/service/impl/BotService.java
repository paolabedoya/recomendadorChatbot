package com.chat.bot.pao.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BotService {
    private static final long TIEMPO_OBTENCION_DATOS = 2000;
    private static final Logger log = LoggerFactory.getLogger(BotService.class);
 
    public void obtenerMinerales() {
        try {
            Thread.sleep(TIEMPO_OBTENCION_DATOS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
