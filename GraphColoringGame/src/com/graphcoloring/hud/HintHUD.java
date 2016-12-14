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
	
	public void initialize() {
		hintBox = new CustomButton(Game.WIDTH - 70, Game.HEIGHT - 95, 100, 50, false, "Hint (" + hintCount + ")", 25);
	}

	public void tick() {
	}

	public void render(Graphics g) {
		hintBox.drawButton(g);
	}

	public void mouseClicked(MouseEvent event) {
		if (game.gameState != Game.STATE.Game || hintCount <= 0) {
			return;
		}

		int mx = event.getX();
		int my = event.getY();

		if (hintBox.mouseOver(mx, my)) {
			notification.createNotification(TYPE.Hint, "Hint: Sample Hint", 5);
			hintCount--;
		}
	}
	
	public void setHintCount(int hintCount) {
		this.hintCount = hintCount;
	}
}
