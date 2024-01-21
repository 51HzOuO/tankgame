package com.teng;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SimpleAudioPlayer implements Runnable {
    private String audioFilePath;
    private Clip audioClip;

    public SimpleAudioPlayer(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    @Override
    public void run() {
        play();
    }

    public void play() {
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            setVolume(0.05);

            do {
                audioClip.start();
                audioClip.drain(); // 等待播放完成

                // 重置音频剪辑以便重新播放
                audioClip.stop();
                audioClip.setFramePosition(0);
            } while (true); // 通过这个循环实现重复播放

            // audioClip.close(); // 如果你想要在某个时刻停止循环，可以通过控制循环条件或在适当的位置调用此方法

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(double volume) {
        if (volume < 0 || volume > 1)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        if (audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10) * 20);
            gainControl.setValue(dB);
        }
    }

}
