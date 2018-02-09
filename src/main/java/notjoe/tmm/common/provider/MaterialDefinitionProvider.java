package notjoe.tmm.common.provider;

import io.vavr.collection.Vector;
import io.vavr.control.Option;
import notjoe.tmm.api.material.*;
import notjoe.tmm.api.resource.Resource;
import org.codejargon.feather.Provides;

import java.awt.*;
import java.io.File;

public class MaterialDefinitionProvider {
    private final File definitionDirectory;

    public MaterialDefinitionProvider(File definitionDirectory) {
        this.definitionDirectory = definitionDirectory;
    }

    @Provides
    public Vector<MaterialDefinition> loadData() {
        if (!definitionDirectory.isDirectory()) {
            return Vector.empty();
        }

        return Vector.of(
                new MaterialDefinition(
                        "Kralebium",
                        new FluidProperties(
                                1, 1, 1, 1, true
                        ),
                        new WorldProperties(
                                1, 1, 1
                        ),
                        new VisualProperties(
                                Color.CYAN, Option.none()
                        ),
                        new ResourceProperties(
                                Vector.of(Resource.INGOT), Option.none(), true, 1

                        )
                ),
                new MaterialDefinition(
                        "Squidwardium",
                        new FluidProperties(
                                1, 1, 1, 1, true
                        ),
                        new WorldProperties(
                                1, 1, 1
                        ),
                        new VisualProperties(
                                Color.RED, Option.none()
                        ),
                        new ResourceProperties(
                                Vector.of(Resource.INGOT), Option.none(), true, 1

                        )
                )
        );
    }
}
