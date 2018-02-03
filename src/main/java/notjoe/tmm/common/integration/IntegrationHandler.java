package notjoe.tmm.common.integration;

import net.minecraftforge.fml.common.Loader;

public class IntegrationHandler {
    public static void onPreInit() {
        if (Loader.isModLoaded("tconstruct")) {
            TinkersIntegration.sendSmelteryDefinitions();
        }
    }
}
