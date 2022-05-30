package org.rockerle.midibridge.client.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RangeScanner {

    public static BlockPos findNoteBlock(BlockPos playerPos, int note, World world){
        BlockPos result = null;
        BlockState test;
        for(int y=-3;y<4;y++) {
            for (int x = -3; x < 4; x++) {
                for (int z = -3; z < 4; z++) {
                    test = world.getBlockState(playerPos.add(x,y,z));
                    if(test.isOf(Blocks.NOTE_BLOCK)){
                        if(Blocks.NOTE_BLOCK.getStateWithProperties(test).get(Properties.NOTE)==note)
                            result = playerPos.add(x,y,z);
                    }
                }
            }
        }
        return result;
    }
}