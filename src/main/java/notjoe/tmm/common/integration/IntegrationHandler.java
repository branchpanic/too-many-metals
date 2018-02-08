package notjoe.tmm.common.integration;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IntegrationHandler {
    List<Integrator> integrations;

    public IntegrationHandler() {
        integrations = new ArrayList<>();
        integrations.add(new ThermalExpansionIntegration());
        integrations.add(new TinkersIntegration());

        cleanupIntegrators();
    }

    private void cleanupIntegrators() {
        integrations = integrations.stream().filter(i -> Loader.isModLoaded(i.getIntegrationID())).collect(Collectors.toList());
    }

    public void onPreInit() {
        integrations.stream().filter(i -> i.getTargetState() == LoaderState.PREINITIALIZATION).forEach(Integrator::doIntegration);
    }

    public void onInit() {
        integrations.stream().filter(i -> i.getTargetState() == LoaderState.INITIALIZATION).forEach(Integrator::doIntegration);
    }
}
