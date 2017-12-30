package com.toyrobot.service;

import org.springframework.stereotype.Component;

import com.toyrobot.service.constant.DIRECTION;

@Component
public class ToyRobot {
	private Position position;
	private DIRECTION direction;
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public DIRECTION getDirection() {
		return direction;
	}
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
}
