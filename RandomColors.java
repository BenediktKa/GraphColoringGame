import java.awt.Color;
public class RandomColors {
	Color[] colors;
	float saturation;
	 
	public RandomColors(int amount, float satRange) {
		 colors = new Color[amount];
		 saturation = 0.5f + (float) Math.random()*satRange;
		 for (int i = 0; i < amount; i++) {
			colors[i] = Color.getHSBColor((float) Math.random()*100, saturation, (float) Math.random());
		}
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