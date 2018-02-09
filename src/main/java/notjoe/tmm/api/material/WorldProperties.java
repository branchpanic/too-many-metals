package notjoe.tmm.api.material;

/**
 * Represents physical in-world properties of a material. Applies to block resource expressions.
 */
public class WorldProperties {
    private final int blockMiningLevel;
    private final float blockHardness;
    private final float blockResistance;

    /**
     * Constructs new BlockProperties from given values.
     * @param blockMiningLevel Mining level required to mine a block of this material.
     * @param blockHardness Hardness of the material block.
     * @param blockResistance Resistance of the material block.
     */
    public WorldProperties(int blockMiningLevel, float blockHardness, float blockResistance) {
        this.blockMiningLevel = blockMiningLevel;
        this.blockHardness = blockHardness;
        this.blockResistance = blockResistance;
    }

    /**
     * @return Mining level of this material's blocks.
     */
    public int getBlockMiningLevel() {
        return blockMiningLevel;
    }

    /**
     * @return Hardness of this material's blocks.
     */
    public float getBlockHardness() {
        return blockHardness;
    }

    /**
     * @return Resistance of this material's blocks.
     */
    public float getBlockResistance() {
        return blockResistance;
    }
}
