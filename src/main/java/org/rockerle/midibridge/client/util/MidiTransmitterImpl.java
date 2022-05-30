package org.rockerle.midibridge.client.util;

import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class MidiTransmitterImpl implements Transmitter {

    Receiver rec;

    @Override
    public void setReceiver(Receiver receiver) {
        this.rec = receiver;
    }

    @Override
    public Receiver getReceiver() {
        return this.rec;
    }

    @Override
    public void close() {
        System.out.println("Goodbye");
    }
}