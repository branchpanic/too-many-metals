package notjoe.tmm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import notjoe.tmm.common.CommonProxy;
import org.apache.logging.log4j.Logger;

@Mod(modid = TooManyMetals.MODID, version = TooManyMetals.VERSION)
public class TooManyMetals {
    public static final String MODID = "tmm";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "notjoe.tmm.client.ClientProxy",
            serverSide = "notjoe.tmm.common.CommonProxy")
    public static CommonProxy PROXY;
    public static Logger LOGGER;

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        LOGGER.info("Starting pre-init.");
        PROXY.onPreInit(event);
        LOGGER.info("Pre-init completed.");
    }
    
    @EventHandler
    public void onInit(FMLInitializationEvent event) {

    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {

    }
}
