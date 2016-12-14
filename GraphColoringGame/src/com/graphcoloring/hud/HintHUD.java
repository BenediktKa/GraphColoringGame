package com.graphcoloring.hud;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.hud.Notification.TYPE;
import com.graphcoloring.main.Game;
import com.graphcoloring.menu.CustomButton;

public class HintHUD extends MouseAdapter {

	private Game game;
	private Notification notification;
	private int hintCount;

	private CustomButton hintBox;

	public HintHUD(Game game, Notification notification, int hintCount) {
		this.game = game;
		this.notification = notification;
		this.hintCount = hintCount;
	}

	public void tick() {
	}

	public void render(Graphics g) {
		hintBox = new CustomButton(Game.WIDTH - 70, Game.HEIGHT - 95, 100, 50, false, "Hint (" + hintCount + ")", 25);
		hintBox.drawButton(g);
	}

	public void mouseClicked(MouseEvent event) {
		if (game.gameState != Game.STATE.Game || hintCount <= 0 || hintBox == null) {
			return;
		}

		int mx = event.getX();
		int my = event.getY();

		if (hintBox.mouseOver(mx, my)) {
			displayHint();
		}
	}
	
	public void setHintCount(int hintCount) {
		this.hintCount = hintCount;
	}
	
	public void displayHint() {
		if(hintCount == 5) {
			notification.createNotification(TYPE.Hint, "Hint: You don't need more than " + (Game.chromaticNumber + 2) + " colors", 5);
		} else if (hintCount == 4) {
			notification.createNotification(TYPE.Hint, "Hint: You need more than " + (Game.chromaticNumber - 2) + " colors", 5);
		} else if (hintCount == 3) {
			notification.createNotification(TYPE.Hint, "Hint: You need more than " + (Game.chromaticNumber - 1) + " colors", 5);
		} else if (hintCount == 2) {
			notification.createNotification(TYPE.Hint, "Hint: You don't need more than " + (Game.chromaticNumber + 1) + " colors", 5);
		} else if (hintCount == 1) {
			notification.createNotification(TYPE.Hint, "Hint: The chromatic number is " + Game.chromaticNumber, 5);
		}
		
		hintCount--;
	}
}
