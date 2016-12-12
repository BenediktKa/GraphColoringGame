package com.graphcoloring.main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayer {

    private final int BUFFER_SIZE = 128000;
    private File file;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;

    public void playSound(String initFileName){

        String fileName = initFileName;

        try {
            file = new File(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(file);
        } catch (Exception e){
            e.printStackTrace();
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		
        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sourceDataLine.start();

        int byteInput = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (byteInput != -1) {
            try {
                byteInput = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (byteInput >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceDataLine.write(abData, 0, byteInput);
            }
        }

        sourceDataLine.drain();
        sourceDataLine.close();
    }
}