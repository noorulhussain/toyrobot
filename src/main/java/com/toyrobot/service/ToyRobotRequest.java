package com.toyrobot.service;

import com.toyrobot.service.constant.DIRECTION;

public class ToyRobotRequest {
	private Integer x;
	private Integer y;
	private DIRECTION direction;
	
	public Integer getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public DIRECTION getDirection() {
		return direction;
	}
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
}
