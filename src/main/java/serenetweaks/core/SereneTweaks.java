package serenetweaks.core;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import serenetweaks.proxy.CommonProxy;

@Mod(modid = SereneTweaks.MODID, name = SereneTweaks.NAME, version = SereneTweaks.VERSION, acceptedMinecraftVersions = SereneTweaks.MC_VERSION, dependencies = "required-after:sereneseasons@1.2.18")
public class SereneTweaks {

	public static final String MODID = "serenetweaks";
	public static final String NAME = "Serene Tweaks";
	public static final String VERSION = "1.0.0";
	public static final String MC_VERSION = "[1.12.2]";

	public static final Logger LOGGER = LogManager.getLogger(SereneTweaks.MODID);
	
	@SidedProxy(clientSide = "serenetweaks.proxy.ClientProxy", serverSide = "serenetweaks.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		proxy.registerEventListeners();
		LOGGER.info(SereneTweaks.NAME + " is present, enjoy your winter wonderland!");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.registerPostEventListeners();
	}

}