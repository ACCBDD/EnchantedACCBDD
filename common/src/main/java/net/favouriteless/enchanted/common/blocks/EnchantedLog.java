package net.favouriteless.enchanted.common.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.function.Supplier;

/**
 * Forge will self-mixin this to handle log stripping. Fabric just uses its registry.
 */
public class EnchantedLog extends RotatedPillarBlock {

    private final Supplier<RotatedPillarBlock> stripped;

    public EnchantedLog(Properties properties, Supplier<RotatedPillarBlock> stripped) {
        super(properties);
        this.stripped = stripped;
    }

}
