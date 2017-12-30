package com.toyrobot.service;

import org.springframework.stereotype.Component;

@Component
public class ToyRobotResponse {
	private String message = "ROBOT MISSING";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
