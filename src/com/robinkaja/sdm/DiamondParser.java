package com.robinkaja.sdm;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DiamondParser {
	DiamondParser() {
		
	}
	
	public boolean[] parseDiamonds(BufferedImage image) {
		boolean[] diamonds = new boolean[7];
		if (checkIfLit(image, 21, 21, 0.8f))
        	diamonds[0] = true;
        if (checkIfLit(image, 62, 31, 0.55f))
        	diamonds[1] = true;
        if (checkIfLit(image, 107, 14, 0.45f))
        	diamonds[2] = true;
        if (checkIfLit(image, 151, 21, 0.5f))
        	diamonds[3] = true;
        if (checkIfLit(image, 61, 61, 0.5f))
        	diamonds[4] = true;
        if (checkIfLit(image, 105, 66, 0.4f))
        	diamonds[5] = true;
        if (checkIfLit(image, 148, 64, 0.5f))
        	diamonds[6] = true;
        
        String DiamondStringDebug = "";
		for (int i = 0;i<7;i++) {
			if (diamonds[i]) DiamondStringDebug += "1";
			else DiamondStringDebug += "0";
		}
		System.out.println("Diamond state: " + DiamondStringDebug);
        
		return diamonds;
	}
	
	boolean checkIfLit(BufferedImage image, int x, int y, float tolarance) {
		int rgb = image.getRGB(x, y);
		Color color = new Color(rgb);
		float[] pixelData = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), new float[3]);
		return (pixelData[2] > tolarance);
	}
}
