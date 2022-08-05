package org.rockerle.midibridge.client.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Inject(at=@At("HEAD"), method="attackBlock", cancellable = true)
    public void onAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir){
        if(MinecraftClient.getInstance()!=null){
            if(MinecraftClient.getInstance().world.getBlockState(pos).isOf(Blocks.NOTE_BLOCK)) {
                World w = MinecraftClient.getInstance().world;
                Property note = Properties.NOTE;
                BlockState bs = w.getBlockState(pos);
                int ip = (int) Blocks.NOTE_BLOCK.getStateWithProperties(bs).get(note);
                cir.cancel();
            }
        }
    }
}