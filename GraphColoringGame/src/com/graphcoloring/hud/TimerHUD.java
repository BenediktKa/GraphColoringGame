package com.graphcoloring.hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.graphcoloring.main.Game;
import com.graphcoloring.menu.ScoreMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class TimerHUD.
 */
public class TimerHUD {

	/** The game. */
	private Game game;
	
	/** The score menu. */
	private ScoreMenu scoreMenu;
	
	/** The time. */
	private double time;
	
	/** The finish time. */
	private double finishTime;
	
	/** The timer. */
	private Timer timer;
	
	/**
	 * Instantiates a new timer HUD.
	 *
	 * @param game the game
	 * @param scoreMenu the score menu
	 */
	public TimerHUD(Game game, ScoreMenu scoreMenu) {
		this.game = game;
		this.scoreMenu = scoreMenu;
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
		Graphics2D g2d = (Graphics2D) g;

		Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 20f);
		g2d.setFont(fnt);
		g2d.setColor(Game.textColor);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		g2d.drawString("Timer " + df.format(finishTime) + "", 0, 20);
	}
	
	/**
	 * Start timer.
	 *
	 * @param pausedTime the paused time
	 */
	public void startTimer(double pausedTime) {
		this.time = pausedTime;
		this.finishTime = pausedTime;
		timer = new Timer();
		timer.scheduleAtFixedRate(new CloseTimer(), 0, 100);
	}
	
	/**
	 * The Class CloseTimer.
	 */
	class CloseTimer extends TimerTask {
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run() {
			finishTime -= 0.1f;
			
			if(finishTime <= 0) {
				scoreMenu.setWin(false);
				game.gameState = Game.STATE.Score;
				timer.cancel();
			}
		}
	}

	
	/**
	 * Stop timer.
	 */
	public void stopTimer() {
		if(timer != null) {
			timer.cancel();
		}
	}
	
	/**
	 * Gets the finish time.
	 *
	 * @return the finish time
	 */
	public double getFinishTime() {
		return finishTime;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(double time) {
		this.time = time;
	}
}
