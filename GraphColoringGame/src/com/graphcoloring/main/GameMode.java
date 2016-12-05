package com.graphcoloring.main;

import com.graphcoloring.hud.Notification;

public class GameMode {

	private Game game;
	private Handler handler;
	private Notification notification;

	public GameMode(Game game, Handler handler, Notification notification) {
		this.game = game;
		this.handler = handler;

		new Graph(handler, notification, 10, 5);
	}
}
