package org.rockerle.midibridge.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.checkerframework.checker.units.qual.A;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import java.util.ArrayList;
import java.util.Arrays;

public class MidiMainMenuGui extends LightweightGuiDescription {

    public MidiMainMenuGui(){
        WPlainPanel root = new WPlainPanel();
        this.setRootPanel(root);
        root.setSize(200,100);
        String[] elements = {"Select Midi Device","Select Instruments","close all open midi devices"};
        WListPanel<String, WButton> mainMenu = new WListPanel<>(
                Arrays.asList(elements),
                WButton::new,
                (s,b)->{
                    b.setLabel(new LiteralText(s));
                    b.setOnClick(()->{
                        switch (s){
                            case "Select Midi Device": {
                                MinecraftClient.getInstance().setScreen(new MidiDeviceScreen(new MidiDeviceListGui()));
                                break;
                            }
                            case "Select Instruments": {
                                MinecraftClient.getInstance().setScreen(new MidiDeviceScreen(new MidiInstrumentSelectGui()));
                                break;
                            }
                            case "close all open midi devices": {
                                MinecraftClient.getInstance().setScreen(null);
                                try {
                                    closeAllDevices();
                                } catch(Exception e){

                                }
                            }
                            default:
                        }
                    });
                }
        );

        root.add(mainMenu,10,10,root.getWidth()-10, root.getHeight()-10);
    }

    private void closeAllDevices() throws Exception{
        ArrayList<MidiDevice> openDevices = new ArrayList<>();
        for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            if(MidiSystem.getMidiDevice(info).isOpen())
                openDevices.add(MidiSystem.getMidiDevice(info));
        }
        System.out.println("found"+openDevices.size()+" open devices");
        for (MidiDevice d : openDevices) {
            d.close();
        }
    }
}