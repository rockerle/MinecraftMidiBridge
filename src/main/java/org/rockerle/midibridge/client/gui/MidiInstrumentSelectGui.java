package org.rockerle.midibridge.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.block.NoteBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.rockerle.midibridge.client.util.NoteHelper;

import java.util.ArrayList;


public class MidiInstrumentSelectGui extends LightweightGuiDescription {
    //The minecraft music scale starts @ F#
    private static final int MAXOCTAVES = 6;
    public MidiInstrumentSelectGui(){
        WPlainPanel root = new WPlainPanel();
        this.setRootPanel(root);
        root.setSize(450,250);
        //root.setSize(MinecraftClient.getInstance().getWindow().getWidth()-100,MinecraftClient.getInstance().getWindow().getHeight()-100);

        WToggleButton scaleSize = new WToggleButton(new LiteralText("single/double"));
        scaleSize.setToggle(NoteHelper.getScale());
        scaleSize.setOnToggle((b)->{
            NoteHelper.switchScale();
        });
        scaleSize.setSize(10,15);
        root.add(scaleSize, 10,10);

        WTextField numOctaves = new WTextField();
        root.add(numOctaves,100,10);


        ArrayList<WSprite> octaves = new ArrayList<>();

        Identifier keyboardSprite = new Identifier("midibridge",scaleSize.getToggle()?"oneoctave.png":"twooctave.png");
        WSprite keyboardPart = new WSprite(new Identifier("midibridge","oneoctave.png"));
        //keyboardPart.setSize(200,80);
        //root.setSize(keyboardPart.getWidth()+20, keyboardPart.getHeight()+20);
        root.add(keyboardPart, 10, 40, 110,80);

        WItemSlot instruments = new WItemSlot(new SidedInventory() {
            @Override
            public int[] getAvailableSlots(Direction side) {
                return new int[0];
            }

            @Override
            public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
                return false;
            }

            @Override
            public boolean canExtract(int slot, ItemStack stack, Direction dir) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public ItemStack getStack(int slot) {
                return null;
            }

            @Override
            public ItemStack removeStack(int slot, int amount) {
                return null;
            }

            @Override
            public ItemStack removeStack(int slot) {
                return null;
            }

            @Override
            public void setStack(int slot, ItemStack stack) {

            }

            @Override
            public void markDirty() {

            }

            @Override
            public boolean canPlayerUse(PlayerEntity player) {
                return false;
            }

            @Override
            public void clear() {

            }
        }, 0, 16, 1, true);
        //instruments.setSize(400,10);
        root.add(instruments,25, root.getHeight()- instruments.getHeight()-10);
        //root.setSize(keyboardPart.getAbsoluteX()+10, keyboardPart.getAbsoluteY()+10);
        root.validate(this);
    }
}