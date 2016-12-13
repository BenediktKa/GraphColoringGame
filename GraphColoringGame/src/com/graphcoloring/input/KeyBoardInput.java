package com.graphcoloring.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.graphcoloring.hud.TimerHUD;
import com.graphcoloring.main.Camera;
import com.graphcoloring.main.Game;

public class KeyBoardInput extends KeyAdapter {

	private Game game;
	private Camera camera;
	private TimerHUD timerHUD;

	private double pausedTime;

	public KeyBoardInput(Game game, Camera camera, TimerHUD timerHUD) {
		this.game = game;
		this.camera = camera;
		this.timerHUD = timerHUD;
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
			if (game.gameState == Game.STATE.Game) {
				game.gameState = Game.STATE.Pause;

				if (game.gamemodeState == Game.GAMEMODE.BestUpperBound) {
					pausedTime = timerHUD.getFinishTime();
					timerHUD.stopTimer();
				}
			} else if (game.gameState == Game.STATE.Pause) {
				game.gameState = Game.STATE.Game;

				if (game.gamemodeState == Game.GAMEMODE.BestUpperBound) {

					timerHUD.startTimer(pausedTime);
				}
			}
			break;
		}
	}
}
