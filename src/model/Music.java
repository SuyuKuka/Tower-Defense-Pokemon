package model;

import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Music {
    public Music() {
    }

    public void playMusic(String songName) {
        try {
            File audioFile = new File("ressources/music/" + songName + ".wav");
            if (audioFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                if (songName.substring(0, songName.length() - 1).contains("main")) {
                    clip.addLineListener(new LineListener() {
                        @Override
                        public void update(LineEvent event) {
                            if (event.getType() == LineEvent.Type.STOP) {
                                clip.close();
                                try {
                                    audioInputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Random rand = new Random();
                                int i = rand.nextInt(5);
                                playMusic(songName.substring(0, songName.length() - 1) + i);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
