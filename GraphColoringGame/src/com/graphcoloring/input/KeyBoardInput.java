package com.graphcoloring.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.graphcoloring.main.Camera;

public class KeyBoardInput extends KeyAdapter {

	private Camera camera;

	public KeyBoardInput(Camera camera) {
		this.camera = camera;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			camera.setY(camera.getY() + 10);
			break;
		case KeyEvent.VK_DOWN:
			camera.setY(camera.getY() - 10);
			break;
		case KeyEvent.VK_LEFT:
			camera.setX(camera.getX() + 10);
			break;
		case KeyEvent.VK_RIGHT:
			camera.setX(camera.getX() - 10);
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;
		}
	}
}
