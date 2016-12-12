package com.graphcoloring.input;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.graphcoloring.main.Camera;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.Game.STATE;
import com.graphcoloring.main.GameObject;
import com.graphcoloring.main.GraphNode;
import com.graphcoloring.main.Handler;
import com.graphcoloring.main.ID;
import com.graphcoloring.menu.ColorPickerHUD;

public class MouseInput extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Camera camera;
	private ColorPickerHUD colorPickerHUD;
	
	private Point mousePoint;

	public MouseInput(Game game, Handler handler, Camera camera, ColorPickerHUD colorPickerHUD) {
		this.game = game;
		this.handler = handler;
		this.camera = camera;
		this.colorPickerHUD = colorPickerHUD;
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

				if (gn.clicked(event.getX() - (int) camera.getX(), event.getY() - (int) camera.getY())) {
					gn.changeColor(colorPickerHUD.getActiveColor());
					break;
				}
			}
		} else if (event.getButton() == MouseEvent.BUTTON3) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;

				if (gn.clicked(event.getX() - (int) camera.getX(), event.getY() - (int) camera.getY())) {
					gn.changeColor(colorPickerHUD.getActiveColor());
					break;
				}
			}
		}
	}

	public void mouseDragged(MouseEvent event) {

		// Don't do anything if not in-game
		if (game.gameState != STATE.Game) {
			return;
		}
		
		int mx = event.getX();
		int my = event.getY();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() != ID.GraphNode) {
				continue;
			}
			GraphNode gn = (GraphNode) tempObject;

			if (gn.clicked(mx - (int) camera.getX(), my - (int) camera.getY())) {
				gn.setX(mx - (int) camera.getX() - gn.getRadius() / 2);
				gn.setY(my - (int) camera.getY() - gn.getRadius() / 2);
				break;
			}
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent event) {
		if(game.gameState != Game.STATE.Game) {
			return;
		}
		
		if(event.getWheelRotation() < 0) {
			colorPickerHUD.setActiveColor(colorPickerHUD.getActiveColor() + 1);
		} else {
			colorPickerHUD.setActiveColor(colorPickerHUD.getActiveColor() - 1);
		}
	}
}
