package org.rockerle.midibridge.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.rockerle.midibridge.client.util.MidiReceiverImpl;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MidiDeviceListGui extends LightweightGuiDescription {

    private Transmitter currentTransmitter = null;

    public MidiDeviceListGui(){
        WGridPanel root = new WGridPanel();
        this.rootPanel = root;
        //root.setSize(400,200);

        ArrayList<MidiDevice.Info> deviceList = new ArrayList<>();
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        if(infos != null)
            for (MidiDevice.Info info : infos) {
                try {
                    MidiDevice test = MidiSystem.getMidiDevice(info);
                    Transmitter testTrans = test.getTransmitter();
                    if (testTrans!=null) {
                        deviceList.add(info);
                        System.out.println(info.getName()+" has a Transmitters");
                        testTrans.close();
                    }
                } catch(Exception e){

                }
            }
        root.setSize(21,10);
        WListPanel<MidiDevice.Info, WButton> midiDevices = new WListPanel<>(deviceList,
                WButton::new,
                (mi,b)->{
                    b.setLabel(new LiteralText(mi.getName()));
                    b.setOnClick(()->{
                        if(currentTransmitter != null)
                            currentTransmitter.close();

                        try {
                            MidiDevice md = MidiSystem.getMidiDevice(mi);
                            Transmitter trans = md.getTransmitter();
                            trans.setReceiver(new MidiReceiverImpl());
                            System.out.println("opening device: "+mi.getName());
                            md.open();
                            currentTransmitter = trans;
                        } catch (MidiUnavailableException e) {
                            e.printStackTrace();
                        }
                        MinecraftClient.getInstance().setScreen(null);
                    });
                });
        //midiDevices.setSize(20,50);
        midiDevices.setListItemHeight(15);
        root.add(midiDevices,1,1,20,9);

        root.validate(this);
    }
}