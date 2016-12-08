package com.graphcoloring.main;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.graphcoloring.hud.Notification;
import com.graphcoloring.menu.Menu;

public class GameMode {

	private int nodes;
	private int edges;
	
	private Game game;
	private Handler handler;
	private Notification notification;
	private Menu menu;

	public GameMode(Game game, Handler handler, Notification notification, Menu menu) {
		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.menu = menu;

		new Graph(handler, notification, 10, 10);
	}
	
	public GameMode(int nodes, int edges, Game game, Handler handler, Notification notification, Menu menu) {
		this.nodes = nodes;
		this.edges = edges;
		
		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.menu = menu;

		new Graph(handler, notification, nodes, edges);
	}
}
