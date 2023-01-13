/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor
 */
public class Som {
            
    public void somp3(){
        URL audio = getClass().getResource("/music/PacMusic.wav");
        AudioClip audioClip = Applet.newAudioClip(audio);
        audioClip.loop();
    }
    public void pacomendo(){
        URL audio = getClass().getResource("/music/comendo2.wav");
        AudioClip audioClip = Applet.newAudioClip(audio);
        audioClip.loop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
