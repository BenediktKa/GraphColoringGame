package com.graphcoloring.main;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.TimerHUD;
import com.graphcoloring.menu.Menu;

public class GameMode {

	public GameMode(int nodes, int edges, Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, Menu menu, boolean randomOrder) {
		new Graph(handler, notification, colorPickerHUD, nodes, edges, randomOrder);
	}
	
	public GameMode(int nodes, int edges, Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, Menu menu, TimerHUD timerHUD, int time) {
		timerHUD.startTimer(time);
		
		new Graph(handler, notification, colorPickerHUD, nodes, edges, false);
	}
}
