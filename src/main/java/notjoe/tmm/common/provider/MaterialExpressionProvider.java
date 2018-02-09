package notjoe.tmm.common.provider;

import io.vavr.collection.Vector;
import notjoe.tmm.api.material.MaterialDefinition;

import javax.inject.Inject;

public class MaterialExpressionProvider {
    private final Vector<MaterialDefinition> materialDefinitions;

    @Inject
    public MaterialExpressionProvider(Vector<MaterialDefinition> materialDefinitions) {
        this.materialDefinitions = materialDefinitions;
    }
}
