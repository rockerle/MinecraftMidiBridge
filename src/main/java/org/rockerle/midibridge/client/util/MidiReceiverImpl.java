package org.rockerle.midibridge.client.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiReceiverImpl implements Receiver {

    private ClientPlayerEntity player;

    public MidiReceiverImpl(ClientPlayerEntity player){
        this.player = player;
    }

    public MidiReceiverImpl(){}

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(message.getMessage().length<3)
            return;

        System.out.println("Got a midi message with length: "+message.getMessage().length);
        byte[] msg = message.getMessage();

        if(!NoteHelper.played(msg[2]))
            return;
        if(MinecraftClient.getInstance()!=null) {
            BlockPos notePos = RangeScanner.findNoteBlock(
                    MinecraftClient.getInstance().player.getBlockPos(),
                    NoteHelper.byteToNote(msg[1]),
                    MinecraftClient.getInstance().world);
            if(notePos != null){
                MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText("Noteblock with right note found here: "+notePos.toString()), null);
                MinecraftClient.getInstance().interactionManager.attackBlock(notePos, Direction.DOWN);
            }
        }
        if(MinecraftClient.getInstance().player != null && NoteHelper.played(msg[2]))
            MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText("played the note: "+NoteHelper.byteToNote(msg[1])), null);
    }

    @Override
    public void close() {
        System.out.println("Closed Midi Receiver");
    }
}