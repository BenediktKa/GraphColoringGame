package com.graphcoloring.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.main.Camera;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.Game.STATE;
import com.graphcoloring.main.GameObject;
import com.graphcoloring.main.GraphNode;
import com.graphcoloring.main.Handler;
import com.graphcoloring.main.ID;

public class MouseInput extends MouseAdapter {

	Game game;
	Handler handler;
	Camera camera;

	public MouseInput(Game game, Handler handler, Camera camera) {
		this.game = game;
		this.handler = handler;
		this.camera = camera;
	}

	public void mouseClicked(MouseEvent event) {
		// Don't do anything if not in-game
		if (game.gameState != STATE.Game) {
			return;
		}

		if (event.getButton() == MouseEvent.BUTTON1) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;
			
				if (gn.clicked(event.getX() - (int)camera.getX(), event.getY() - (int)camera.getY())) {
					gn.changeColor();
				}
			}
		} else if (event.getButton() == MouseEvent.BUTTON3) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;

				if (gn.clicked(event.getX() - (int)camera.getX(), event.getY() - (int)camera.getY())) {
					gn.changeColor();
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		System.out.println("Miau2");
	}
}
