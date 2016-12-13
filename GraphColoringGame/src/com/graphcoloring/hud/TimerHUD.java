package com.graphcoloring.hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.graphcoloring.main.Game;
import com.graphcoloring.menu.ScoreMenu;

public class TimerHUD {

	private Game game;
	private ScoreMenu scoreMenu;
	
	private double time;
	private double finishTime;
	
	private Timer timer;
	
	public TimerHUD(Game game, ScoreMenu scoreMenu) {
		this.game = game;
		this.scoreMenu = scoreMenu;
	}
	
	public void tick() {
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 20f);
		g2d.setFont(fnt);
		g2d.setColor(Game.textColor);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		g2d.drawString("Timer " + df.format(finishTime) + "", 0, 20);
	}
	
	public void startTimer(double pausedTime) {
		this.time = pausedTime;
		this.finishTime = pausedTime;
		timer = new Timer();
		timer.scheduleAtFixedRate(new CloseTimer(), 0, 100);
	}
	
	class CloseTimer extends TimerTask {
		public void run() {
			finishTime -= 0.1f;
			
			if(finishTime <= 0) {
				scoreMenu.setWin(false);
				game.gameState = Game.STATE.Score;
				timer.cancel();
			}
		}
	}

	
	public void stopTimer() {
		timer.cancel();
	}
	
	public double getFinishTime() {
		return finishTime;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
}
