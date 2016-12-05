package com.graphcoloring.input;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.main.Game;
import com.graphcoloring.main.GameObject;
import com.graphcoloring.main.GraphNode;
import com.graphcoloring.main.Handler;
import com.graphcoloring.main.ID;

public class MouseMotionListener extends MouseMotionAdapter{

	private Game game;
	private Handler handler;

	public MouseMotionListener(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mouseDragged(MouseEvent event) {

	}

	public void mouseMoved(MouseEvent event) {
		if(game.gameState != Game.STATE.Game) {
			return;
		}
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() != ID.GraphNode) {
				continue;
			}
			GraphNode gn = (GraphNode) tempObject;

			if (gn.clicked(event.getX(), event.getY())) {
				gn.onHover();
			}
		}
	}
}
