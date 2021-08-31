package com.chat.bot.pao.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChatDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063925307925886118L;

	private String solicitud;
	
	private String respuesta;
	
	
}
