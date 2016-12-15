package com.graphcoloring.main;

import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

// TODO: Auto-generated Javadoc
/**
 * The Class SoundPlayer.
 */
public class SoundPlayer {

	/**
	 * Play music.
	 *
	 * @param soundName the sound name
	 */
	public void playMusic(String soundName) {
		switch (soundName) {
		case "MenuMusic":
			break;
		}
	}

	/**
	 * Play sound FX.
	 *
	 * @param soundName the sound name
	 */
	public void playSoundFX(String soundName) {
		switch (soundName) {
		case "MenuClick":
			playSound("sound/click.wav");
			break;
		}
	}

	/**
	 * Play sound.
	 *
	 * @param filePath the file path
	 */
	public void playSound(String filePath) {
		
		if(Game.VOLUME == 0) {
			return;
		}
		
		try {
			InputStream file = ResourceLoader.load(filePath);
			playSoundFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Play sound file.
	 *
	 * @param file the file
	 */
	public void playSoundFile(InputStream file) {

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