package com.toyrobot.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyrobot.service.constant.DIRECTION;

@Component
public class ToyRobotServiceImpl implements ToyRobotService, InitializingBean {

	private static int LEFTX = 0;
	private static int LEFTY = 0;
	private static int RIGHTX = 4;
	private static int RIGHTY = 4;

	@Autowired
	ToyRobot toyRobot;

	private DIRECTION getLeftDirection(String position) {
		DIRECTION direction = null;
		switch (position) {
		case "EAST":
			direction = DIRECTION.NORTH;
			break;
		case "WEST":
			direction = DIRECTION.SOUTH;
			break;
		case "NORTH":
			direction = DIRECTION.WEST;
			break;
		case "SOUTH":
			direction = DIRECTION.EAST;
			break;
		}
		return direction;
	}

	private DIRECTION getRightDirection(String position) {
		DIRECTION direction = null;
		switch (position) {
		case "EAST":
			direction = DIRECTION.SOUTH;
			break;
		case "WEST":
			direction = DIRECTION.NORTH;
			break;
		case "NORTH":
			direction = DIRECTION.EAST;
			break;
		case "SOUTH":
			direction = DIRECTION.WEST;
			break;
		}
		return direction;
	}

	private boolean validatePosition(Position position) {
		if (position != null && position.getX() >= LEFTX && position.getY() >= LEFTY && position.getX() <= RIGHTX
				&& position.getY() <= RIGHTY) {
			return true;
		}
		return false;
	}

	@Override
	public boolean left() {
		// TODO Auto-generated method stub
		if (toyRobot.getDirection() != null && validatePosition()) {
			toyRobot.setDirection(getLeftDirection(toyRobot.getDirection().name()));
			return true;
		}
		return false;
	}

	@Override
	public boolean right() {
		// TODO Auto-generated method stub
		if (toyRobot.getDirection() != null && validatePosition()) {
			toyRobot.setDirection(getRightDirection(toyRobot.getDirection().name()));
			return true;
		}
		return false;
	}

	@Override
	public boolean move() {
		// TODO Auto-generated method stub
		if (toyRobot.getPosition() != null) {
			Position position = new Position(toyRobot.getPosition().getX(), toyRobot.getPosition().getY());
			if (validatePosition()) {
				switch (toyRobot.getDirection().name()) {
				case "EAST":
					position.setX(position.getX() + 1);
					break;
				case "WEST":
					position.setX(position.getX() - 1);
					break;
				case "NORTH":
					position.setY(position.getY() + 1);
					break;
				case "SOUTH":
					position.setY(position.getY() - 1);
					break;
				}
				toyRobot.setPosition(position);
				return true;
			} else {
				return false;
			}
			
		}
		return true;
	}

	@Override
	public void place(String requestBody) throws ToyRobotException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		ToyRobotRequest toyRobotRequest = null;
		try {
			toyRobotRequest = mapper.readValue(requestBody, ToyRobotRequest.class);
		} catch (Exception e) {
			throw new ToyRobotException("Invalid arguments");
		}

		if (toyRobotRequest.getX() == null || toyRobotRequest.getY() == null
				|| toyRobotRequest.getDirection() == null) {
			throw new ToyRobotException("Invalid arguments");
		} else {
			Position position = new Position(toyRobotRequest.getX(), toyRobotRequest.getY());
			DIRECTION direction = toyRobotRequest.getDirection();
			if (validatePosition(position)) {
				toyRobot.setPosition(position);
				toyRobot.setDirection(direction);
			} else {
				throw new ToyRobotException("Invalid placement of Toy Robot");
			}
		}
	}

	@Override
	public ToyRobot report() {
		// TODO Auto-generated method stub
		return toyRobot;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		toyRobot.setPosition(null);
		toyRobot.setDirection(null);
	}

	@Override
	public boolean validatePosition() {
		// TODO Auto-generated method stub
		return validatePosition(toyRobot.getPosition());	
	}

}
