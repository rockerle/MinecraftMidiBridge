package org.rockerle.midibridge.client.util;

public class NoteHelper {
    private static boolean singleScale = true;
    public static String byteToString(byte b){
        int n = (int) b;
        n -= 21;
        return DoubleScale.values()[n%12].name().replace("IS","#");
    }

    public static boolean getScale(){return singleScale;}
    public static void switchScale(){singleScale = !singleScale;}

    public static int byteToNote(byte b){
        int n = b;
        if(singleScale)
            return SingleScale.values()[(b-18)%12].ordinal();
        else
            return DoubleScale.values()[(b-18)%24].ordinal();
    }
    public static boolean played(byte b){
        return b>0;
    }
}