import java.awt.Color;
public class RandomColors {
	private Color[] colors;
	private float saturation;
	 
	public RandomColors(int amount, float satRange) {
		 colors = new Color[amount];
		 float seperation = 1/amount;
		 float[] hue = new float[amount];
		 
		 for (int i = 0; i < amount; i++) {
			saturation = 0.5f + (float) Math.random()*satRange;
			boolean isSimilar = true;
			
			hue[i] = (float) (Math.random()*100);
			
			while(isSimilar) {
				isSimilar = checkSeperation(hue, i, seperation, amount);
				if (isSimilar)
					hue[i] = (float) (Math.random()*100);
			}
					
			colors[i] = Color.getHSBColor(hue[i], saturation, (float) (Math.random()*0.5f)+0.5f);
		}
	}
	
	public boolean checkSeperation(float[] hue, int i, float seperation, int amount) {
		float seperationRange = 0.3f*seperation;
		
		for (int k = 0; k < i; k++) {
			if (((float)Math.abs(hue[k]-hue[i])) < ((360*seperation)+seperationRange) || ((float)Math.abs(hue[k]-hue[i])) < ((360*seperation)-seperationRange))
				return true;
		}
		return false;
		
	}
	
	public Color[] getPalette() {
		return colors;
	}
	
	public void changeColor(int index) {
		saturation = 0.5f + (float) (Math.random()*0.1 - Math.random()*0.1);
		colors[index] = Color.getHSBColor((float) Math.random()*100, saturation, (float) Math.random());
	}
	
	public void setColor(float hue, float saturation, float brightness, int index) {
		colors[index] = Color.getHSBColor(hue, saturation, brightness);
	}
}