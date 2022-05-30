package org.rockerle.midibridge.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.rockerle.midibridge.client.gui.MidiDeviceListGui;
import org.rockerle.midibridge.client.gui.MidiDeviceScreen;
import org.rockerle.midibridge.client.gui.MidiMainMenuGui;
import org.rockerle.midibridge.client.util.MidiReceiverImpl;

import javax.sound.midi.*;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class MidibridgeClient implements ClientModInitializer {

    MidiDevice.Info[] info;

    @Override
    public void onInitializeClient() {

        //-----------------------BEGIN--TESTAREA---------------------------------------------
        KeyBinding testKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("lol", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J,"lol"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(MinecraftClient.getInstance().player!=null && testKey.wasPressed())
                MinecraftClient.getInstance().setScreen(new MidiDeviceScreen(new MidiMainMenuGui()));
        });


        //-----------------------END---TESTARTEA----------------------------------------------

        MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
        //MidiDevice.Info device = null;
        if(info.length==0)
            System.out.println("No midi devices found :(");
        for (MidiDevice.Info i : info) {
            System.out.println("Device: "+i.getName()+":");
            System.out.println("description: "+i.getDescription());
            System.out.println("vendor: "+i.getVendor());
            System.out.println("version: "+i.getVersion());
            System.out.println("------------------------------------------------------");

        }
        /*try {
            device = info[5];
        } catch(IndexOutOfBoundsException iobe){
            System.out.println("No external Midi Device found");
            return;
        }
        MidiDevice mididevice = null;

        if(device != null) {
            try {
                mididevice = MidiSystem.getMidiDevice(device);
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No usb midi device found :(");
        }

        try {
            MidiReceiverImpl rec = new MidiReceiverImpl();
            Transmitter transmitter = mididevice.getTransmitter();
            transmitter.setReceiver(rec);
            mididevice.open();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }*/
    }
}