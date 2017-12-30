package com.toyrobot.service;

public interface ToyRobotService {
	public boolean left();
	public boolean right();
	public boolean move();
	public void place(String requestBody) throws Exception;
	public void reset();
	public ToyRobot report();
	public boolean validatePosition();
}
