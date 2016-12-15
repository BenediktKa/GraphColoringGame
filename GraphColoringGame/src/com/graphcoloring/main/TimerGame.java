package com.graphcoloring.main;

import java.util.Timer;
import java.util.TimerTask;

// TODO: Auto-generated Javadoc
/**
 * The Class TimerGame.
 */
public class TimerGame {
	
	/** The timer. */
	private Timer timer;
	
	/** The finish time. */
	private double finishTime;
	
	/**
	 * The Class CloseTimer.
	 */
	class CloseTimer extends TimerTask {
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run() {
			finishTime += 0.1f;
		}
	}
	
	/**
	 * Start timer.
	 */
	public void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new CloseTimer(), 0, 100);
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
	 * Sets the finish time.
	 *
	 * @param finishTime the new finish time
	 */
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
}
