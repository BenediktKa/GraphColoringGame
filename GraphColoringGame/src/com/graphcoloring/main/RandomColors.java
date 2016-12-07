package com.graphcoloring.main;

import java.awt.Color;

public class RandomColors {
	Color[] colors;
	float saturation;

	//Test
	public RandomColors(int amount, float satRange) {
		colors = new Color[amount];
		saturation = 0.5f + (float) Math.random() * satRange;
		
		//Set First Color
		colors[0] = Color.getHSBColor(183.53f / 360, 10.24f / 100, 65.1f / 100);
		
		for (int i = 1; i < amount; i++) {
			colors[i] = Color.getHSBColor((float) Math.random() * 100, saturation, (float) Math.random());
		}
	}

	public Color[] getPalette() {
		return colors;
	}

	public void changeColor(int index) {
		saturation = 0.5f + (float) (Math.random() * 0.1 - Math.random() * 0.1);
		colors[index] = Color.getHSBColor((float) Math.random() * 100, saturation, (float) Math.random());
	}

	public void setColor(float hue, float saturation, float brightness, int index) {
		colors[index] = Color.getHSBColor(hue, saturation, brightness);
	}
}