package se.unlogic.standardutils.color;

import java.awt.Color;


public class ColorUtils {

	public static Color getLightenColor(Color color, float amount) {

		int red = (int) ((color.getRed() * (1 - amount) / 255 + amount) * 255);
		int green = (int) ((color.getGreen() * (1 - amount) / 255 + amount) * 255);
		int blue = (int) ((color.getBlue() * (1 - amount) / 255 + amount) * 255);
		return new Color(red, green, blue);
	}
	
}
