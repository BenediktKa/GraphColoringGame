package com.graphcoloring.main;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.Notification;
import com.graphcoloring.menu.Menu;

public class GameMode {

	private int nodes;
	private int edges;

	private Game game;
	private Handler handler;
	private Notification notification;
	private ColorPickerHUD colorPickerHUD;
	private Menu menu;

	public GameMode(Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, Menu menu) {
		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.colorPickerHUD = colorPickerHUD;
		this.menu = menu;

		new Graph(handler, notification, colorPickerHUD, 10, 10);
	}

	public GameMode(int nodes, int edges, Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, Menu menu) {
		this.nodes = nodes;
		this.edges = edges;

		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.colorPickerHUD = colorPickerHUD;
		this.menu = menu;

		new Graph(handler, notification, colorPickerHUD, nodes, edges);
	}
}
