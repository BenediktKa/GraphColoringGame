package com.graphcoloring.main;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SoundPlayer {

	public void playMusic(String soundName) {
		switch (soundName) {
		case "MenuMusic":
			break;
		}
	}

	public void playSoundFX(String soundName) {
		switch (soundName) {
		case "MenuClick":
			playSound("src\\sound\\click.wav");
			break;
		}
	}

	public void playSound(String filePath) {
		
		if(Game.VOLUME == 0) {
			return;
		}
		
		try {
			File file = new File(filePath);
			playSoundFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playSoundFile(File file) {

		try {
			final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP)
						clip.close();
				}
			});

			clip.open(AudioSystem.getAudioInputStream(file));
			
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Game.VOLUME * gainControl.getMaximum() / 100);
			
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
}