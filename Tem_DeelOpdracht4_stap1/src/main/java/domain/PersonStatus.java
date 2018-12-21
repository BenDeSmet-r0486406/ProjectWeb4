package domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonStatus {
	ONLINE("Online"),
	OFFLINE("Offline"),
	BUSY("Busy"),
	CUSTOM("Custom");

	private final String message;
	PersonStatus(String msg){
		this.message = msg;
	}

	@Override
	public String toString(){
		return message;
	}

}
