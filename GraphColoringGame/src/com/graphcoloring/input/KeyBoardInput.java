package com.graphcoloring.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.TimerHUD;
import com.graphcoloring.main.Camera;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.TimerGame;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyBoardInput.
 */
public class KeyBoardInput extends KeyAdapter {

	/** The game. */
	private Game game;
	
	/** The camera. */
	private Camera camera;
	
	/** The timer HUD. */
	private TimerHUD timerHUD;
	
	/** The timer game. */
	private TimerGame timerGame;
	
	/** The color picker HUD. */
	private ColorPickerHUD colorPickerHUD;

	/** The paused time. */
	private double pausedTime;

	/**
	 * Instantiates a new key board input.
	 *
	 * @param game the game
	 * @param camera the camera
	 * @param timerHUD the timer HUD
	 * @param timerGame the timer game
	 * @param colorPickerHUD the color picker HUD
	 */
	public KeyBoardInput(Game game, Camera camera, TimerHUD timerHUD, TimerGame timerGame, ColorPickerHUD colorPickerHUD) {
		this.game = game;
		this.camera = camera;
		this.timerHUD = timerHUD;
		this.timerGame = timerGame;
		this.colorPickerHUD = colorPickerHUD;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
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
					timerGame.stopTimer();
				}
			} else if (game.gameState == Game.STATE.Pause) {
				game.gameState = Game.STATE.Game;

				if (game.gamemodeState == Game.GAMEMODE.BestUpperBound) {

					timerHUD.startTimer(pausedTime);
					timerGame.startTimer();
				}
			}
			break;
		case KeyEvent.VK_P:
			if (game.gameState == Game.STATE.Game) {
				colorPickerHUD.addCircle();
			}
			break;
		}
	}
}
