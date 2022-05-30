package org.rockerle.midibridge.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.rockerle.midibridge.client.gui.MidiDeviceScreen;
import org.rockerle.midibridge.client.gui.MidiMainMenuGui;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class MidibridgeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        //-----------------------BEGIN--TESTAREA---------------------------------------------
        KeyBinding testKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Midi Menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J,"Minecraft Midi Bridge"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(MinecraftClient.getInstance().player!=null && testKey.wasPressed())
                MinecraftClient.getInstance().setScreen(new MidiDeviceScreen(new MidiMainMenuGui()));
        });
        //-----------------------END---TESTARTEA----------------------------------------------

    }
}