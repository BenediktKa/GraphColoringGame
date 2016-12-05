package com.graphcoloring.main;

public class GameMode {

	private Game game;
	private Handler handler;

	public GameMode(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;

		new Graph(handler, 10, 5);
	}
}
