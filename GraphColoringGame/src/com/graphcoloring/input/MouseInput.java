package com.graphcoloring.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;
import java.util.Set;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.TimerHUD;
import com.graphcoloring.main.Camera;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.Game.GAMEMODE;
import com.graphcoloring.main.GameObject;
import com.graphcoloring.main.GraphNode;
import com.graphcoloring.main.Handler;
import com.graphcoloring.main.ID;
import com.graphcoloring.menu.ScoreMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class MouseInput.
 */
public class MouseInput extends MouseAdapter {

	/** The game. */
	private Game game;
	
	/** The handler. */
	private Handler handler;
	
	/** The camera. */
	private Camera camera;
	
	/** The color picker HUD. */
	private ColorPickerHUD colorPickerHUD;
	
	/** The score menu. */
	private ScoreMenu scoreMenu;
	
	/** The timer HUD. */
	private TimerHUD timerHUD;

	/**
	 * Instantiates a new mouse input.
	 *
	 * @param game the game
	 * @param handler the handler
	 * @param camera the camera
	 * @param colorPickerHUD the color picker HUD
	 * @param scoreMenu the score menu
	 * @param timerHUD the timer HUD
	 */
	public MouseInput(Game game, Handler handler, Camera camera, ColorPickerHUD colorPickerHUD, ScoreMenu scoreMenu, TimerHUD timerHUD) {
		this.game = game;
		this.handler = handler;
		this.camera = camera;
		this.colorPickerHUD = colorPickerHUD;
		this.scoreMenu = scoreMenu;
		this.timerHUD = timerHUD;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent event) {
		// Don't do anything if not in-game
		if (game.gameState != Game.STATE.Game) {
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
					checkAllColored();
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
					gn.setColor(0);
					break;
				}
			}
		}
	}

	/**
	 * Check all colored.
	 */
	public void checkAllColored() {
		int nodes = 0;
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() != ID.GraphNode) {
				continue;
			}

			GraphNode gn = (GraphNode) tempObject;
			nodes++;

			if (gn.getColor() == 0) {
				return;
			}
		}

		// All nodes are colored here
		int colorArray[] = new int[nodes];
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() != ID.GraphNode) {
				continue;
			}

			GraphNode gn = (GraphNode) tempObject;
			
			colorArray[gn.getNodeID()] = gn.getColor();
		}
		
		if(distinctNumberOfItems(colorArray) == Game.chromaticNumber) {
			if (game.gamemodeState == GAMEMODE.BestUpperBound) {
				scoreMenu.setTimeLeft(true);
				scoreMenu.setTime(timerHUD.getFinishTime());
				
				scoreMenu.setScore((int) (1.0f / timerHUD.getFinishTime() * 100000));
			} else {
				scoreMenu.setTime(Game.timerGame.getFinishTime());
				
				scoreMenu.setScore((int) (1.0f / Game.timerGame.getFinishTime() * 100000));
			}
			game.gameState = Game.STATE.Score;
			Game.timerGame.stopTimer();
			timerHUD.stopTimer();
		} else if (game.gamemodeState == GAMEMODE.RandomOrder) {
			scoreMenu.setWin(false);
			game.gameState = Game.STATE.Score;
			Game.timerGame.stopTimer();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent event) {

		// Don't do anything if not in-game
		if (game.gameState != Game.STATE.Game) {
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

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	public void mouseWheelMoved(MouseWheelEvent event) {
		if (game.gameState != Game.STATE.Game) {
			return;
		}

		if (event.getWheelRotation() < 0) {
			colorPickerHUD.setActiveColor(colorPickerHUD.getActiveColor() + 1);
		} else {
			colorPickerHUD.setActiveColor(colorPickerHUD.getActiveColor() - 1);
		}
	}

	/**
	 * Distinct number of items.
	 *
	 * @param array the array
	 * @return the int
	 */
	public int distinctNumberOfItems(int[] array) {
		if (array.length <= 1)
			return array.length;

		Set<Integer> set = new HashSet<Integer>();
		for (int i : array)
			if (i != 0)
				set.add(i);

		return set.size();
	}
}
