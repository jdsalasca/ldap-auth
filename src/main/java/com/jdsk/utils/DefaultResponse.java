package com.jdsk.utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//version 0.0.3 1/2/2023
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse<T> {

    private List<T> data = Collections.emptyList();
    private HttpStatus status;
    private List<Message> message;
    private List<Error> error;
    private MESSAGETYPES messageType = MESSAGETYPES.INFO;
    private DATATYPE dataType = DATATYPE.LIST;

    public DefaultResponse(List<T> data, HttpStatus status, List<Message> message, List<Error> error,
   		 MESSAGETYPES messageType) {
   	 super();
   	 this.data = data;
   	 this.status = status;
   	 this.message = message;
   	 this.error = error;
   	 this.messageType = messageType;
    }

    public enum MESSAGETYPES {
   	 INFO("info"), SUCCESS("success"), WARN("warn"), ERROR("error");

   	 private String value;

   	 MESSAGETYPES(String value) {
   		 this.value = value;

   	 }

   	 public String value() {
   		 return this.value;
   	 }

    }

    public enum DATATYPE {
   	 LIST("List"), OBJECT("Object");

   	 private String value;

   	 DATATYPE(String value) {
   		 this.value = value;

   	 }

   	 public String value() {
   		 return this.value;
   	 }

    }

    public enum DEFAULTMESSAGES {
   	 SUCCESS_MESSAGE("Operación terminada correctamente"),
   	 NOT_INFO_FOUND_MESSAGE("No se encontro información relacionada"),
   	 DATA_SAVED_MESSAGE("Información Guardada con éxito!"), NOT_DATA_SAVED_MESSAGE("Información no almacenada"),
   	 INFO_UPDATED_MESSAGE("Información actualizada con éxito!");

   	 private String value;

   	 DEFAULTMESSAGES(String value) {
   		 this.value = value;

   	 }

   	 public String value() {
   		 return this.value;
   	 }

    }

    public static<T> ResponseEntity<DefaultResponse<T>> onThrow500ErrorResponse(List<String> errorMessage) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.ERROR);
   	 messageResult.setMessage("Error interno del sistema");
   	 messageResult.setErrorStringList(errorMessage);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static <T>ResponseEntity<DefaultResponse<T>> onThrow500ErrorListResponse(List<Error> errorMessage) {
   	 DefaultResponse <T>messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.ERROR);
   	 messageResult.setMessage("Error interno del sistema");
   	 messageResult.setError(errorMessage);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static<T> ResponseEntity<DefaultResponse<T>> onThrow500ErrorResponse(String errorMessage) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.ERROR);
   	 messageResult.setMessage("Error interno del sistema");
   	 messageResult.setError(errorMessage);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public static<T> ResponseEntity<DefaultResponse<T>> onThrow400ResponseTypeInfo(String message) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.INFO);
   	 messageResult.setMessage(message);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static <T>ResponseEntity<DefaultResponse<T>> onThrow400ResponseTypeError(String message) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.ERROR);
   	 messageResult.setMessage(message);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static<T> ResponseEntity<DefaultResponse<T>> onThrow400ResponseTypeError(List<Message> message) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.ERROR);
   	 messageResult.setMessage(message);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static <T>ResponseEntity<DefaultResponse<T>> onThrow404Response(String message) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.NOT_FOUND);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.INFO);
   	 messageResult.setMessage(message);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public static <T> ResponseEntity<DefaultResponse<T>> onThrow404ResponseWithData(String message, List<T> data) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.NOT_FOUND);
   	 messageResult.setData(data);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.INFO);
   	 messageResult.setMessage(message);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public static <T>ResponseEntity<DefaultResponse<T>> onThrow400ResponseBindingResult (BindingResult bindingResult){
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
  	  List<Message> listErrors = new ArrayList<>();
  	  for (FieldError error : bindingResult.getFieldErrors()) {
  		  listErrors.add(new Message(error.getDefaultMessage(), MESSAGETYPES.INFO));
  	  }
   	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.INFO);
   	 messageResult.setMessage(listErrors);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static <T> ResponseEntity<DefaultResponse<T>> onThrow200Response(List<T> data) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setData(data);
   	 messageResult.setStatus(HttpStatus.OK);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.SUCCESS);
   	 messageResult.setMessage(DefaultResponse.DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static <T> ResponseEntity<DefaultResponse<T>> onThrow200ResponseListMessageAndErrors(List<T> data,
   		 List<Message> messages, List<Error> errors) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setData(data);
   	 messageResult.setStatus(HttpStatus.OK);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.SUCCESS);
   	 messageResult.setMessage(messages);
   	 messageResult.setError(errors);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public static <T>ResponseEntity<DefaultResponse<T>> onThrow200ResponseListMessage(List<T> data, List<Message> messages) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setData(data);
   	 messageResult.setStatus(HttpStatus.OK);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.SUCCESS);
   	 messageResult.setMessage(messages);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public static<T> ResponseEntity<DefaultResponse<T>> onThrow201Response(List<T> data) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setData(data);
   	 messageResult.setStatus(HttpStatus.CREATED);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.SUCCESS);
   	 messageResult.setMessage(DefaultResponse.DEFAULTMESSAGES.INFO_UPDATED_MESSAGE.value());
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

    }

    public static <T> ResponseEntity<DefaultResponse<T>> onThrow201CustomMessage(String string) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setStatus(HttpStatus.CREATED);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.SUCCESS);
   	 messageResult.setMessage(string);
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public static <T> ResponseEntity<DefaultResponse<T>> onThrow200ResponseObjectData(List<T> data) {
   	 DefaultResponse<T> messageResult = new DefaultResponse<>();
   	 messageResult.setData(data);
   	 messageResult.setStatus(HttpStatus.OK);
   	 messageResult.setDataType(DATATYPE.OBJECT);
   	 messageResult.setMessageType(DefaultResponse.MESSAGETYPES.SUCCESS);
   	 messageResult.setMessage(DefaultResponse.DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
   	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
    }

    public DefaultResponse(List<T> data, HttpStatus status, List<Message> message) {
   	 super();
   	 this.data = data;
   	 this.status = status;
   	 this.message = message;
    }

    public String getMessageType() {
   	 return messageType.value();
    }

    public void setMessageType(MESSAGETYPES messageType) {
   	 this.messageType = messageType;
    }

    public void setDataType(DATATYPE dataType) {
   	 this.dataType = dataType;
    }

    public DATATYPE getDataType() {
   	 return dataType;
    }


    public List<T> fecthDataTest() {
   	 return this.data;
    }

    public List<Error> getError() {
   	 return this.error;
    }

//   	 if (this.error !=null && this.error.size() == 1) {
//   		 return this.error.get(0);
//   	 }else {
//   		 return error;
//
//   	 }
//    }
    public List<Message> getMessage() {
   	 return this.message;
//   	 if (this.message !=null &&  this.message.size() == 1) {
//   		 return this.message.get(0);
//   	 }else {
//   		 return message;
//
//   	 }
    }

    public void setData(List<T> data) {
   	 this.data = data;
    }

    public void setErrorStringList(List<String> errorsList) {
   	 ArrayList<Error> errors = new ArrayList<>();
   	 for (String error : errorsList) {
   		 errors.add(new Error(error, this.messageType));
   	 }
   	 this.error = errors;
    }

    public void setError(Error error) {
   	 this.error = List.of(error);
    }

    public void setError(String error) {
   	 this.error = List.of(new Error(error, (this.messageType != null) ? this.messageType : MESSAGETYPES.ERROR));
    }

    @JsonSetter
    public void setError(List<Error> error) {
   	 this.error = error;
    }

    @JsonSetter
    public void setMessage(List<Message> message) {
   	 this.message = message;
    }

    public void setMessage(Message message) {
   	 this.message = List.of(message);
    }

    public void setMessage(String message) {

   	 this.message = List.of(new Message(message, (this.messageType != null) ? this.messageType : MESSAGETYPES.INFO));
    }

    public int getStatus() {
   	 return status.value();
    }

    public void setStatus(HttpStatus status) {
   	 this.status = status;
    }

    public HttpStatus catchHttpStatus() {
   	 return this.status;
    }

    @Override
    public String toString() {
   	 Gson gson = new Gson();
   	 return gson.toJson(this);
    }
}




