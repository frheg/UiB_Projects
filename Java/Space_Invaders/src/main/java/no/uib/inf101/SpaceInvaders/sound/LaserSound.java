package no.uib.inf101.SpaceInvaders.sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Chat GPT
 */

public class LaserSound implements Runnable {

    private static final String INVADERSMUSIC = "laserSound.wav";
    private Clip clip;

    @Override
    public void run() {
        InputStream song = InvadersSong.class.getClassLoader().getResourceAsStream(INVADERSMUSIC);
        this.doPlayAudio(song);
    }

    private void doPlayAudio(final InputStream is) {
        try {
            this.doStopAudioSounds();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            this.audioError("" + e);
        }
    }

    public void doStopAudioSounds() {
        try {
            if (this.clip == null || !this.clip.isRunning()) {
                return;
            }
            this.clip.stop();
            this.clip.close();
        } catch (Exception e) {
            this.audioError("" + e);
        }
        this.clip = null;
    }

    public void doPauseAudioSounds() {
        try {
            if (this.clip == null || !this.clip.isRunning()) {
                return;
            }
            this.clip.stop();
        } catch (Exception e) {
            this.audioError("" + e);
        }
    }

    public void doUnpauseAudioSounds() {
        try {
            if (this.clip == null) {
                return;
            }
            this.clip.start();
        } catch (Exception e) {
            this.audioError("" + e);
        }
    }

    private void audioError(final String msg) {
        System.err.println("Audio error: " + msg);
        this.clip = null;
    }
}
