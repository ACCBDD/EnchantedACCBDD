package net.favouriteless.enchanted.common.circle_magic.rites;

import net.favouriteless.enchanted.common.circle_magic.CircleMagicShape;
import net.favouriteless.enchanted.common.init.EData;
import net.favouriteless.enchanted.common.init.registry.EItems;
import net.favouriteless.enchanted.common.items.CircleTalismanItem;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class BindTalismanRite extends Rite {

    public BindTalismanRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected boolean onStart(RiteParams params) {
        Registry<CircleMagicShape> registry = level.registryAccess().registryOrThrow(EData.CIRCLE_SHAPE_REGISTRY);

        Map<CircleMagicShape, Block> shapes = new HashMap<>();
        for(CircleMagicShape shape : registry) {
            Block block = shape.getBlockAt(level, pos);
            if (block != null)
                shapes.put(shape, block);
        }

        if(shapes.isEmpty())
            return cancel();

        HashMap<ResourceLocation, Block> component = new HashMap<>();
        shapes.forEach((shape, block) -> component.put(registry.getKey(shape), block));
        shapes.keySet().forEach(shape -> shape.remove(level, pos));
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

        ItemStack out = new ItemStack(EItems.CIRCLE_TALISMAN.get());
        CompoundTag shapesTag = new CompoundTag();
        for(Map.Entry<ResourceLocation, Block> entry : component.entrySet()) {
            shapesTag.putString(entry.getKey().toString(), BuiltInRegistries.BLOCK.getKey(entry.getValue()).toString());
        }
        out.getOrCreateTag().put(CircleTalismanItem.SHAPE_TAG, shapesTag);

        ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, out);
        level.addFreshEntity(entity);

        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        randomParticles(ParticleTypes.WITCH);
        return false;
    }

}
