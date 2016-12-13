package com.graphcoloring.main;

import java.util.Timer;
import java.util.TimerTask;

public class TimerGame {
	
	private Timer timer;
	private double finishTime;
	
	class CloseTimer extends TimerTask {
		public void run() {
			finishTime += 0.1f;
		}
	}
	
	public void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new CloseTimer(), 0, 100);
	}

	public void stopTimer() {
		timer.cancel();
	}

	public double getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
}
