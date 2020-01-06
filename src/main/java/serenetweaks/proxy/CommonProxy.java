package serenetweaks.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import serenetweaks.handlers.SnowRecalculationHandler;

public class CommonProxy {
	
	
	public void init(){
		
	}
	
	public void registerEventListeners(){
		MinecraftForge.EVENT_BUS.register(new SnowRecalculationHandler());
	}
	
	public void registerPostEventListeners(){
		
	}
}
