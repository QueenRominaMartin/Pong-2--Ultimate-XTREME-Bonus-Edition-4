import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A class for static methods that deal with importing and playing audio files for the game.
 */
public class Sound {
    
    /**
     * This class is used to create a Clip object that contains a specific sound file, which in this case
     * all the sound files used are MP3s, though it should support any audio format file.
     * @param filePath a string representing the path of the audio file.
     * @return the Clip object
     */
    public static Clip createSoundClip(String filePath) {
        File soundFile = new File(filePath); // Creates a file object to represent the sound file.
        Clip soundClip = null;
        /*
         * Creates an Audio clip object for the specific sound
         */
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile.getAbsoluteFile());
            soundClip = AudioSystem.getClip();
            soundClip.open(audioIn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return soundClip;
    }

    /**
     * A static method used to play a sound from a Clip object
     * @param soundClip the Clip object to play the sound from
     */
    public static void playSoundClip(Clip soundClip) {
        if(soundClip.isRunning()){
            soundClip.stop();
        }
        soundClip.setFramePosition(0);
        soundClip.start();

    }

}
