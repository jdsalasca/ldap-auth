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
public class Error {
        private String message;
        private MESSAGETYPES messageType;
		public String getMessageType() {
			return messageType.value();
		}
 	

}