package com.jdsk.utils;

import com.jdsk.utils.DefaultResponse.MESSAGETYPES;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message{

    private String messageDesc;
    private MESSAGETYPES messageType;
	public String getMessageType() {
		return messageType.value();
	}
}