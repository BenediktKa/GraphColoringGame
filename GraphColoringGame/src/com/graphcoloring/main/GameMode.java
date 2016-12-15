package com.graphcoloring.main;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.TimerHUD;
import com.graphcoloring.menu.Menu;

// TODO: Auto-generated Javadoc
/**
 * The Class GameMode.
 */
public class GameMode {

	/**
	 * Instantiates a new game mode.
	 *
	 * @param nodes the nodes
	 * @param edges the edges
	 * @param game the game
	 * @param handler the handler
	 * @param notification the notification
	 * @param colorPickerHUD the color picker HUD
	 * @param menu the menu
	 * @param randomOrder the random order
	 */
	public GameMode(int nodes, int edges, Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, Menu menu, boolean randomOrder) {
		new Graph(game, handler, notification, colorPickerHUD, nodes, edges, randomOrder);
	}
	
	/**
	 * Instantiates a new game mode.
	 *
	 * @param nodes the nodes
	 * @param edges the edges
	 * @param game the game
	 * @param handler the handler
	 * @param notification the notification
	 * @param colorPickerHUD the color picker HUD
	 * @param menu the menu
	 * @param timerHUD the timer HUD
	 * @param time the time
	 */
	public GameMode(int nodes, int edges, Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, Menu menu, TimerHUD timerHUD, int time) {
		timerHUD.startTimer(time);
		
		new Graph(game, handler, notification, colorPickerHUD, nodes, edges, false);
	}
}
