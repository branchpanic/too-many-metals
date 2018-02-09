package notjoe.tmm.common.content;

import notjoe.tmm.api.material.MaterialDefinition;
import notjoe.tmm.api.resource.ResourceType;

public interface ResourceExpression {
    MaterialDefinition getMaterialDefinition();

    ResourceType getResourceType();
}
