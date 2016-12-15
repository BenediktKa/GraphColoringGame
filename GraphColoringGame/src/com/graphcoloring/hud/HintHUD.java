package com.graphcoloring.hud;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.hud.Notification.TYPE;
import com.graphcoloring.main.Game;
import com.graphcoloring.menu.CustomButton;

// TODO: Auto-generated Javadoc
/**
 * The Class HintHUD.
 */
public class HintHUD extends MouseAdapter {

	/** The game. */
	private Game game;
	
	/** The notification. */
	private Notification notification;
	
	/** The hint count. */
	private int hintCount;

	/** The hint box. */
	private CustomButton hintBox;

	/**
	 * Instantiates a new hint HUD.
	 *
	 * @param game the game
	 * @param notification the notification
	 * @param hintCount the hint count
	 */
	public HintHUD(Game game, Notification notification, int hintCount) {
		this.game = game;
		this.notification = notification;
		this.hintCount = hintCount;
	}

	/**
	 * Tick.
	 */
	public void tick() {
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		hintBox = new CustomButton(Game.WIDTH - 70, Game.HEIGHT - 95, 100, 50, false, "Hint (" + hintCount + ")", 25);
		hintBox.drawButton(g);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
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
	
	/**
	 * Sets the hint count.
	 *
	 * @param hintCount the new hint count
	 */
	public void setHintCount(int hintCount) {
		this.hintCount = hintCount;
	}
	
	/**
	 * Display hint.
	 */
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
