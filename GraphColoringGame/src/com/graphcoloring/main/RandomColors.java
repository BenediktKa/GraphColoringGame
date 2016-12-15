package com.graphcoloring.main;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomColors.
 */
public class RandomColors {
	
	/** The colors. */
	private Color[] colors;
	
	/** The saturation. */
	private float saturation;

	/**
	 * Instantiates a new random colors.
	 *
	 * @param amount the amount
	 * @param satRange the sat range
	 */
	public RandomColors(int amount, float satRange) {
		colors = new Color[amount];
		float seperation = 1 / amount;
		float[] hue = new float[amount];
		
		colors[0] = Game.silverColor;

		for (int i = 1; i < amount; i++) {
			saturation = 0.5f + (float) Math.random() * satRange;
			boolean isSimilar = true;

			hue[i] = (float) (Math.random() * 100);

			while (isSimilar) {
				isSimilar = checkSeperation(hue, i, seperation, amount);
				if (isSimilar)
					hue[i] = (float) (Math.random() * 100);
			}

			colors[i] = Color.getHSBColor(hue[i], saturation, (float) (Math.random() * 0.5f) + 0.5f);
		}
	}

	/**
	 * Check seperation.
	 *
	 * @param hue the hue
	 * @param i the i
	 * @param seperation the seperation
	 * @param amount the amount
	 * @return true, if successful
	 */
	public boolean checkSeperation(float[] hue, int i, float seperation, int amount) {
		float seperationRange = 0.3f * seperation;

		for (int k = 0; k < i; k++) {
			if (((float) Math.abs(hue[k] - hue[i])) < ((360 * seperation) + seperationRange)
					|| ((float) Math.abs(hue[k] - hue[i])) < ((360 * seperation) - seperationRange))
				return true;
		}
		return false;

	}

	/**
	 * Gets the palette.
	 *
	 * @return the palette
	 */
	public Color[] getPalette() {
		return colors;
	}

	/**
	 * Change color.
	 *
	 * @param index the index
	 */
	public void changeColor(int index) {
		saturation = 0.5f + (float) (Math.random() * 0.1 - Math.random() * 0.1);
		colors[index] = Color.getHSBColor((float) Math.random() * 100, saturation, (float) Math.random());
	}

	/**
	 * Sets the color.
	 *
	 * @param hue the hue
	 * @param saturation the saturation
	 * @param brightness the brightness
	 * @param index the index
	 */
	public void setColor(float hue, float saturation, float brightness, int index) {
		colors[index] = Color.getHSBColor(hue, saturation, brightness);
	}
}