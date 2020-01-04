package serenetweaks.handlers;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.minecraftforge.fml.relauncher.Side;
import sereneseasons.api.season.ISeasonState;
import sereneseasons.api.season.SeasonHelper;
import sereneseasons.api.season.WorldHooks;

public class SnowRecalculationHandler {
	
	private static ArrayList<Chunk> recalculationQueue = new ArrayList<Chunk>();
	
	@SubscribeEvent
	public void onTick(TickEvent.WorldTickEvent event) {
		Type type = event.type;
		Side side = event.side;
		Phase phase = event.phase;
		World world = event.world;
		if (!(type == Type.WORLD && side == Side.SERVER)) {
			return;
		}
		if (recalculationQueue.size() == 0) {
			return;
		}
		int i = 0;
		while (recalculationQueue.size() > i && !recalculationQueue.get(i).isLoaded()) {
			i++;
		}
		if (recalculationQueue.size() <= i) {
			return;
		}
		Chunk chunk = recalculationQueue.get(i);
		if (!chunk.isLoaded()) {
			return;
		}
		recalculationQueue.remove(i);
		ISeasonState seasonState = SeasonHelper.getSeasonState(world);
		BlockPos pos = new BlockPos(chunk.x*16, 0, chunk.z*16);
		//BlockPos snowPos = chunk.getPrecipitationHeight(pos);
		//world.setBlockState(snowPos, Blocks.ICE.getDefaultState(), 2);
		
		for (int k2 = 0; k2 < 16; ++k2)
        {
            for (int j3 = 0; j3 < 16; ++j3)
            {
                BlockPos blockpos1 = chunk.getPrecipitationHeight(pos.add(k2, 0, j3));
                BlockPos blockpos2 = blockpos1.down();

                if (world.canBlockFreezeWater(blockpos2))
//                if (WorldHooks.canBlockFreezeInSeason(world, blockpos2, false, seasonState))
                {
                    world.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
                }

                if (world.canSnowAt(blockpos1, true))
//                if (WorldHooks.canSnowAtInSeason(world, blockpos1, false, seasonState))
                {
                    world.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
                }
            }
        }
		
	}
	
	@SubscribeEvent
	public void onChunkLoaded(ChunkEvent.Load event) {
		World world = event.getWorld();
		Chunk chunk = event.getChunk();
		recalculationQueue.add(chunk);
		System.out.println(chunk.isLoaded());
	}
	
	
	
}
