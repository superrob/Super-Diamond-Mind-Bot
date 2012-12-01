package com.robinkaja.sdm;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class Game {	
	public static int distanceFromRootX = 27;
	public static int distanceFromRootY = 66;	
	
	public static int gameFieldDistanceFromRootX = 214;
	public static int gameFieldDistanceFromRootY = 57;
	public static int gameField_Width = 272;
	public static int gameField_Height = 272;
	
	public static int cellPadding = 34;
	public static int cellTestPoint = 17;
	
	public int rootX;
	public int rootY;
	public int fieldX;
	public int fieldY;
	
	public Robot robot;
	public FieldParser fieldParser = new FieldParser();
	public MoveFinder moveFinder = new MoveFinder();
	
	Game() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean findGamePosition() {
		BufferedImage screencapture = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));		
		int maxWidth = screencapture.getWidth()-1;
		int maxHeight = screencapture.getHeight()-1;
		
		for (int x=0;x<maxWidth;x++) {
			for (int y=0;y<maxHeight;y++) {
				int rgb = screencapture.getRGB(x, y);
				Color color = new Color(rgb);
				if (color.getRed() == 243 && color.getGreen() == 219 && color.getBlue() == 111) {
					rootX = x-distanceFromRootX;
					rootY = y-distanceFromRootY;
					fieldX = rootX+gameFieldDistanceFromRootX;
					fieldY = rootY+gameFieldDistanceFromRootY;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public BufferedImage getField() {
		return getField(false);
	}
	
	public BufferedImage getField(Boolean reRender) {
		BufferedImage field = null;
		field = robot.createScreenCapture(new Rectangle(fieldX, fieldY, gameField_Width, gameField_Height));
		
		if (reRender) {
			BufferedImage newImage = new BufferedImage(gameField_Width, gameField_Height, BufferedImage.TYPE_INT_RGB);
			for (int y=0;y<8;y++) {
				for (int x=0;x<8;x++) {
					int rgb = field.getRGB((x * cellPadding) + cellTestPoint, (y * cellPadding) + cellTestPoint);
					
					for (int j=(y*cellPadding);j<((y+1)*cellPadding);j++) {
						for (int i=(x*cellPadding);i<((x+1)*cellPadding);i++) {
							newImage.setRGB(i, j, rgb);
						}
					}
					
				}
			}
			return newImage;
		}
		
		return field;
	}
	
	public int[][] parseField() {
		return fieldParser.parseField(getField(false));
	}
	
	public int[][] parseField(BufferedImage image) {
		int[][] first = fieldParser.parseField(image);
		int trueCount = 15;
		while(true) {
			int[][] second = fieldParser.parseField(image);
			if (fieldParser.compareTwoField(first, second)) trueCount++;
			else trueCount=0;
			first = second.clone();
			if (trueCount > 25) break;
		}
		return first;
	}
	
	public Boolean makeGameMove() {
		int[][] fieldData = parseField();
		try {
			Move move = moveFinder.findBestMove(fieldData);
			int[][] moveCoordinates = move.getMoveCoordinates();
			moveCoordinates[0][0] += fieldY;
			moveCoordinates[1][0] += fieldY;
			moveCoordinates[0][1] += fieldX;
			moveCoordinates[1][1] += fieldX;
			
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			
			robot.mouseMove(moveCoordinates[0][1], moveCoordinates[0][0]);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseMove(moveCoordinates[1][1], moveCoordinates[1][0]);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			robot.mouseMove((int)mousePoint.getX(), (int)mousePoint.getY());
		} catch (NoMoveFoundException e) {
			return false;
		}
		
		return true;
	}
}
