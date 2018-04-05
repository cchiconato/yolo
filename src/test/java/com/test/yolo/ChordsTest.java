package com.test.yolo;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.Test;

import br.com.yolo.utils.Mod;

/**
* @author  Chrisitan Chiconato
* @since   2018-03-21 
*/

public class ChordsTest {
	
	@Test
	public void songTestSmokeOnTheWater(){
        Player player = new Player();
        
        String g1 = "G3q A#3q C4q. G3q A#3q C#4i C4h G3q A#3q C4q. A#3q "
                + "G3ih.";
        Pattern guitar1 = new Pattern(g1);
        
        Pattern guitar2 = Mod.transposeFromPattern(guitar1, -12);
        
        guitar1.repeat(8);
        guitar1.setInstrument("OVERDRIVEN_GUITAR").setVoice(0);
        
        guitar2.repeat(6);
        guitar2.prepend("R/8.0"); // start at 8 beat
        guitar2.setInstrument("OVERDRIVEN_GUITAR").setVoice(1);
        
        Pattern bass1 = new Pattern("G3i").repeat(10);
        
        Pattern bass2 = new Pattern("G3i").repeat(8); 
        Pattern bass3 =
                new Pattern("A#3i A#3i C4i C4i C4i A#3i A#3i G3i G3i");
        Pattern bass4 = new Pattern("G3i").repeat(5);
        
        Pattern bass = new Pattern(bass1, bass2, bass3, bass4);
        bass.repeat(4);
        bass.prepend("R/16.0"); // start at 16 beat
        bass.setInstrument("ELECTRIC_BASS_FINGER").setVoice(2);
        
        Pattern drum = new Pattern("Rq [ACOUSTIC_SNARE]q").repeat(16);
        drum.prepend("R/24.0"); // start at beat 24
        drum.setVoice(9); // percussion
        
        Pattern pattern = 
                new Pattern(guitar1, guitar2, bass, drum);
        pattern.setTempo(110);
 
        player.play(pattern);
	}
}
