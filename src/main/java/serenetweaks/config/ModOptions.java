package serenetweaks.config;

import net.minecraftforge.common.config.Config;
import serenetweaks.core.SereneTweaks;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Type;

public class ModOptions{
	@Config(modid = SereneTweaks.MODID, type = Type.INSTANCE, name = SereneTweaks.MODID + "_options")
	public static class Settings{
		@Comment("This setting determines if the server will recalculate snow based on the season as you explore your world.\nThis makes your world much prettier!")
	    public static boolean shouldRecalculateSnow = true;
		@Comment("This setting determines how long a chunk must be unloaded in order to have its snow and ice recalculated.\nEnter a value in minutes.")
	    public static int timeToRecalculateSnow = 20;
	}
}
