package com.graphcoloring.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.HintHUD;
import com.graphcoloring.menu.Menu;

public class Window extends Canvas {

	private static final long serialVersionUID = 8571002207074266558L;

	public Window(int width, int height, String title, Game game, Menu menu, ColorPickerHUD colorPickerHUD, HintHUD hintHUD) {
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();

		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				Game.WIDTH = frame.getWidth();
				Game.HEIGHT = frame.getHeight();
				menu.initialize();
				colorPickerHUD.intialize();
				hintHUD.initialize();
			}
		});
	}
}
