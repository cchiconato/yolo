package br.com.yolo.utils;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import org.jfugue.theory.Note;


/**
* @author  Christian Chiconato
* @since   2018-03-20 
*/

public class Mod {

	 public static Pattern transposeFromPattern(Pattern pattern, int nt) {
	        Pattern transpose = new Pattern();
	        for (Token token: pattern.getTokens()) {
	            if (token.getType() != Token.TokenType.NOTE) {
	                transpose.add(token.getPattern());
	            }
	            else {
	                String val = token.toString();
	                if (val.substring(0,1).equalsIgnoreCase("r")) { // Rest 
	                    transpose.add(token.toString());
	                }
	                else if (val.contains("+")) { // Chord (C+E+G format)
	                    String newValue = "";
	                    String[] notes = val.split("[+]");
	                    int i;
	                    for (i = 0; i<(notes.length-1); i++) {
	                        newValue += (getNote(notes[i], 0, nt) + "+");
	                    }
	                    i = notes.length-1;
	                    newValue += getNote(notes[i], 1, nt);
	                    transpose.add(newValue);
	                    
	                }
	                else { // Single note
	                    transpose.add(getNote(token.toString(), 1, nt));
	                }
	            }
	        }
	        return transpose;
	    }
	    
	    private static String getNote(String noteString, int flag, int nt) {
	        Note note = new Note(noteString);
	        byte value = note.changeValue(nt).getValue();
	        String tone = Note.getToneString(value);
	        if (flag==0) return tone;
	        else {
	            String dur = Note.getDurationString(note.getDuration());
	            String vel = note.getVelocityString();
	            return tone + dur + vel;
	        }
	    }
}
