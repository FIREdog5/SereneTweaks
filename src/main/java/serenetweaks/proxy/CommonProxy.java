package serenetweaks.proxy;

import net.minecraftforge.common.MinecraftForge;
import serenetweaks.handlers.BranchHandler;
import serenetweaks.handlers.SnowRecalculationHandler;
import serenetweaks.init.ModBlocks;

public class CommonProxy {
	
	
	public void init(){
		
	}
	
	public void registerEventListeners(){
		MinecraftForge.EVENT_BUS.register(new SnowRecalculationHandler());
		MinecraftForge.EVENT_BUS.register(new BranchHandler());
		//MinecraftForge.EVENT_BUS.register(new ModBlocks());
	}
	
	public void registerPostEventListeners(){
		
	}
}
