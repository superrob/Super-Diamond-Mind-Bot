package com.robinkaja.sdm;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class FieldParser {
	FieldParser() {
		
	}
	
	public int[][] parseField(BufferedImage image) {
		int[][] field = new int[8][8];
		for (int y=0;y<8;y++) {
			for (int x=0;x<8;x++) {
				int rgb = image.getRGB((x * Game.cellPadding) + Game.cellTestPoint, (y * Game.cellPadding) + Game.cellTestPoint);
				Color color = new Color(rgb);
				field[y][x] = parseColor(color);
			}
		}
		return field;
	}
	
	public int parseColor(Color color) {
		float[] pixelData = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), new float[3]);
		pixelData[0] = pixelData[0] * 360;		
		if (pixelData[0] >= 179.0 && pixelData[0] < 196.0 && pixelData[1] >= 0.0 && pixelData[1] <= 0.04)
			return -Gem.WHITE;
		if (pixelData[0] >= 179.5 && pixelData[0] < 181.5)
			return Gem.CYAN;
		if (pixelData[0] >= 285.5 && pixelData[0] < 287.5)
			return Gem.PURPLE;
		if (pixelData[0] >= 57.5 && pixelData[0] < 59.5)
			return Gem.YELLOW;
		if (pixelData[0] >= 0.0 && pixelData[0] < 2.5)
			return Gem.RED;
		if (pixelData[0] >= 129.5 && pixelData[0] < 131.5)
			return Gem.GREEN;
		if (pixelData[0] >= 0.0 && pixelData[0] < 2.5)
			return Gem.RED;
		if (pixelData[0] >= 231.0 && pixelData[0] < 233.0)
			return Gem.BLUE;
		
		return Gem.NONE;
	}
	
	public Boolean compareTwoField(int[][] fieldOne, int[][] fieldTwo) {
		for (int y=0;y<8;y++)
			for (int x=0;x<8;x++)
				if (fieldOne[y][x] != fieldTwo[y][x]) return false;
		
		return true;
	}
}
