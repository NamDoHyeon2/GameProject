package background;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music extends Thread {

    private Clip clip;
    private FloatControl volumeControl;
    private boolean isLoop;
    private boolean isPlaying;

    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            File file = new File("C:\\ex1\\AutoPocket_ex1\\src\\Sound\\" + name);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);

            
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error initializing music: " + e.getMessage());
        }
    }

    public void close() {
        isLoop = false;
        clip.close();
        this.interrupt();
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);
        }
    }

    public void run() {
        try {
            do {
                play();
                clip.drain();
                clip.stop();
                clip.setFramePosition(0);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void play() {
        if (clip != null && !isPlaying) {
            isPlaying = true;
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}


