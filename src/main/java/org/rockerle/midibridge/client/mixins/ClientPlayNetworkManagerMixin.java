package org.rockerle.midibridge.client.mixins;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkManagerMixin {

    @Inject(at=@At("HEAD"), method="onDisconnect")
    public void onDisconnect(DisconnectS2CPacket packet, CallbackInfo ci){
        System.out.println("checking for open midi devices to close");
        for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            try {
                if (MidiSystem.getMidiDevice(info).isOpen())
                    System.out.println("found open midi device: "+info.getName());
                    MidiSystem.getMidiDevice(info).close();
                    System.out.println("closed midi device: "+info.getName());
            } catch(Exception e){

            }
        }
    }
}