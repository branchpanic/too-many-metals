package notjoe.tmm.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import notjoe.tmm.common.content.BlockMaterial;
import notjoe.tmm.common.content.ItemMaterial;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TMaterialContentFactory {
    INSTANCE;

    private TMaterialRegistry registry = TMaterialRegistry.INSTANCE;
    private Map<String, Map<ResourceType, Object>> materialContent;

    public Map<String, Map<ResourceType, Object>> getMaterialContent() {
        if (materialContent == null) {
            materialContent = new HashMap<>();
        }

        return materialContent;
    }

    public List<ItemMaterial> getItems() {
        List<ItemMaterial> materials = new ArrayList<>();
        for (String name : materialContent.keySet()) {
            for (ResourceType resourceType : registry.getMaterial(name).getResourceTypes()) {
                ItemMaterial item = getItem(name, resourceType);
                if (item != null) {
                    materials.add(item);
                }
            }
        }

        return materials;
    }

    public List<BlockMaterial> getBlocks() {
        List<BlockMaterial> materials = new ArrayList<>();
        for (String name : materialContent.keySet()) {
            for (ResourceType resourceType : registry.getMaterial(name).getResourceTypes()) {
                BlockMaterial block = getBlock(name, resourceType);
                if (block != null) {
                    materials.add(block);
                }
            }
        }

        return materials;
    }

    @Nonnull
    public ItemStack getItemStack(String material, ResourceType resourceType) {
        return getItemStack(material, resourceType, 1);
    }

    @Nonnull
    public ItemStack getItemStack(String material, ResourceType resourceType, int amount) {
        if (resourceType.isItem()) {
            Item resourceItem = getItem(material, resourceType);
            if (resourceItem != null) {
                return new ItemStack(resourceItem, amount);
            }
        } else {
            Block resourceBlock = getBlock(material, resourceType);
            if (resourceBlock != null) {
                return new ItemStack(resourceBlock, amount);
            }
        }

        return ItemStack.EMPTY;
    }

    @Nullable
    public ItemMaterial getItem(String material, ResourceType resourceType) {
        if (resourceType.isItem()) {
            Map<ResourceType, Object> contentMap = getMaterialContent().get(material);
            if (contentMap != null) {
                return (ItemMaterial) contentMap.get(resourceType);
            }
        }

        return null;
    }


    @Nullable
    public BlockMaterial getBlock(String material, ResourceType resourceType) {
        if (!resourceType.isItem()) {
            Map<ResourceType, Object> contentMap = getMaterialContent().get(material);
            if (contentMap != null) {
                return (BlockMaterial) contentMap.get(resourceType);
            }
        }

        return null;
    }

    public void registerMaterial(TMaterial material) {
        Map<ResourceType, Object> contentMap = new HashMap<>();
        for (ResourceType resourceType : material.getResourceTypes()) {
            if (resourceType.isItem()) {
                contentMap.put(resourceType, new ItemMaterial(material, resourceType));
            } else {
                contentMap.put(resourceType, new BlockMaterial(material, resourceType));
            }
        }

        getMaterialContent().put(material.getName(), contentMap);
    }
}
