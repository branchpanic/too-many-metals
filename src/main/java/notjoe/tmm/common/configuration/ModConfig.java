package notjoe.tmm.common.configuration;

import net.minecraftforge.common.config.Config;
import notjoe.tmm.TooManyMetals;

@Config(modid = TooManyMetals.MODID, name = "TooManyMetals/TooManyMetals")
public class ModConfig {
    @Config.Comment({"If true, a list of materials and IDs is logged on game start."})
    public static boolean logMaterials = true;
}
