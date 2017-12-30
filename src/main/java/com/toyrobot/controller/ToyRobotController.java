package com.toyrobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toyrobot.service.ToyRobot;
import com.toyrobot.service.ToyRobotException;
import com.toyrobot.service.ToyRobotResponse;
import com.toyrobot.service.ToyRobotService;

@RestController
public class ToyRobotController {

	@Autowired
	ToyRobotService toyRobotService;

	@RequestMapping(value = "/move")
	public ResponseEntity<?> move() {
		if (toyRobotService.move()) {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT MOVED SUCCESSFULLY."), HttpStatus.OK);
		} else {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT FALLEN. PLACE IT AGAIN!!!"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/report")
	public ResponseEntity<?> report() {
		ToyRobot robot = toyRobotService.report();
		if (robot.getPosition() == null || robot.getDirection() == null) {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("ROBOT MISSING"), HttpStatus.OK);
		}
		if (!toyRobotService.validatePosition()) {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT FALLEN. PLACE IT AGAIN!!!"), HttpStatus.OK);
		}
		return new ResponseEntity<ToyRobot>(toyRobotService.report(), HttpStatus.OK);
	}

	@RequestMapping(value = "/left")
	public ResponseEntity<?> left() {
		if (toyRobotService.left()) {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT TOOK LEFT DIRECTION SUCCESSFULLY."), HttpStatus.OK);
		} else {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT FALLEN. PLACE IT AGAIN!!!"), HttpStatus.OK);
		}
		
	}

	@RequestMapping(value = "/right")
	public ResponseEntity<?> right() {
		if (toyRobotService.right()) {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT TOOK RIGHT DIRECTION SUCCESSFULLY."), HttpStatus.OK);	
		} else {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage("TOY ROBOT FALLEN. PLACE IT AGAIN!!!"), HttpStatus.OK);
		}
		
	}

	@RequestMapping(value = "/place", consumes = "application/json", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> place(@RequestBody String json) throws Exception {
		try {
			if (json == null) {
				return new ResponseEntity<ToyRobotResponse>(getErrorMessage("Invalid Request body"), HttpStatus.OK);
			}
			toyRobotService.place(json);
		} catch (ToyRobotException ex) {
			return new ResponseEntity<ToyRobotResponse>(getErrorMessage(ex.toString()), HttpStatus.OK);
		}
		return new ResponseEntity<ToyRobot>(toyRobotService.report(), HttpStatus.OK);
	}

	private ToyRobotResponse getErrorMessage(String message) {
		ToyRobotResponse response = new ToyRobotResponse();
		response.setMessage(message);
		return response;
	}

}
