package com.graphcoloring.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

	Handler handler;

	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;

				if (gn.clicked(event.getX(), event.getY())) {
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

				if (gn.clicked(event.getX(), event.getY())) {
					gn.changeColor();
				}
			}
		}
	}
}
