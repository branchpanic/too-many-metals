package notjoe.tmm.api.material;

public class WorldProperties {
    private final int blockMiningLevel;
    private final float blockHardness;
    private final float blockResistance;

    public WorldProperties(int blockMiningLevel, float blockHardness, float blockResistance) {
        this.blockMiningLevel = blockMiningLevel;
        this.blockHardness = blockHardness;
        this.blockResistance = blockResistance;
    }

    public int getBlockMiningLevel() {
        return blockMiningLevel;
    }

    public float getBlockHardness() {
        return blockHardness;
    }

    public float getBlockResistance() {
        return blockResistance;
    }
}
