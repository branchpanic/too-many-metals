package notjoe.tmm.common.integration;

import net.minecraftforge.fml.common.LoaderState;

public interface Integrator {
    String getIntegrationID();

    void doIntegration();

    LoaderState getTargetState();
}
