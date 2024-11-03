package favouriteless.enchanted.common.rites.entity.protection;

import favouriteless.enchanted.api.rites.AbstractRite;
import favouriteless.enchanted.client.particles.types.DoubleParticleType.DoubleParticleData;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
import favouriteless.enchanted.common.rites.CirclePart;
import favouriteless.enchanted.common.rites.RiteType;
import favouriteless.enchanted.util.WaystoneHelper;
import net.favouriteless.stateobserver.api.StateObserverManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.UUID;

public class RiteProtection extends AbstractRite {

    private RiteProtectionObserver stateObserver = null;
    protected final int radius;
    protected final Block block;
    protected ServerLevel targetLevel = null;
    protected BlockPos targetPos = null;

    protected RiteProtection(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster, int radius, Block block) {
        super(type, level, pos, caster);
        this.radius = radius;
        this.block = block;
    }

    public RiteProtection(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster, int radius) {
        this(type, level, pos, caster, radius, EnchantedBlocks.PROTECTION_BARRIER.get()); // Power, power per tick, radius
    }

    @Override
    public void execute() {
        findTargetPos();
        generateSphere(block);
        getOrCreateObserver();
        targetLevel.sendParticles(new DoubleParticleData(EnchantedParticleTypes.PROTECTION_SEED.get(), radius), targetPos.getX()+0.5D, targetPos.getY()+0.6D, targetPos.getZ()+0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void onTick() {
        if(targetLevel.isLoaded(targetPos))
            if(ticks % 20 == 0) {
                stateObserver.checkChanges();
                targetLevel.sendParticles(new DoubleParticleData(EnchantedParticleTypes.PROTECTION_SEED.get(), radius), targetPos.getX()+0.5D, targetPos.getY()+0.6D, targetPos.getZ()+0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
    }

    @Override
    public void onStopExecuting() {
        generateSphere(Blocks.AIR);
        StateObserverManager.get().removeObserver(stateObserver);
    }

    protected void generateSphere(Block block) {
        MutableBlockPos spherePos = new MutableBlockPos(0, 0, 0);
        double increment = 1.0D / radius; // Total radians / circumference for radians per step
        for(double y = 0; y <= Math.PI*2 + increment/2; y += increment) {
            for(double p = 0; p <= Math.PI*2 + increment/2; p += increment) {
                double cosY = Math.cos(y);
                double sinY = Math.sin(y);
                double cosP = Math.cos(p);
                double sinP = Math.sin(p);
                spherePos.set((int) Math.round(sinY * cosP * radius) + targetPos.getX(), (int) Math.round(sinP * radius) + targetPos.getY(), (int) Math.round(cosY * cosP * radius) + targetPos.getZ());

                if(targetLevel.getBlockState(spherePos).isAir() || targetLevel.getBlockState(spherePos).is(EnchantedBlocks.PROTECTION_BARRIER.get()) || targetLevel.getBlockState(spherePos).is(EnchantedBlocks.PROTECTION_BARRIER_TEMPORARY.get()))
                    targetLevel.setBlockAndUpdate(spherePos, block.defaultBlockState());
            }
        }
    }

    protected void findTargetPos() {
        ServerLevel level = getLevel();
        BlockPos pos = getPos();
        List<Entity> items = CirclePart.SMALL.getEntitiesInside(level, pos);
        for(Entity entity : items) {
            if(entity instanceof ItemEntity itemEntity) {
                ItemStack stack = itemEntity.getItem();
                if(stack.getItem() == EnchantedItems.BOUND_WAYSTONE.get()) {
                    targetLevel = (ServerLevel) WaystoneHelper.getLevel(level, stack);
                    targetPos = WaystoneHelper.getPos(stack);
                    consumeItemNoRequirement(itemEntity);
                    break;
                }
                else if(stack.getItem() == EnchantedItems.BLOODED_WAYSTONE.get()) {
                    setTargetEntity(WaystoneHelper.getEntity(level, stack));
                    targetLevel = (ServerLevel)getTargetEntity().level();
                    targetPos = getTargetEntity().blockPosition();
                    consumeItemNoRequirement(itemEntity);
                    break;
                }
            }
        }
        if(targetLevel == null)
            targetLevel = level;
        if(targetPos == null)
            targetPos = pos;
    }

    @Override
    protected boolean loadAdditional(CompoundTag nbt, Level level) {
        if(!nbt.contains("targetLevel"))
            return false;
        if(!nbt.contains("targetX"))
            return false;
        if(!nbt.contains("targetY"))
            return false;
        if(!nbt.contains("targetZ"))
            return false;

        targetLevel = level.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt.getString("targetLevel"))));
        targetPos = new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ"));
        getOrCreateObserver();
        generateSphere(block);

        return true;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putString("targetLevel", targetLevel.dimension().location().toString());
        nbt.putInt("targetX", targetPos.getX());
        nbt.putInt("targetY", targetPos.getY());
        nbt.putInt("targetZ", targetPos.getZ());
    }

    protected void getOrCreateObserver() {
        if(stateObserver == null)
            stateObserver = StateObserverManager.get().getObserver(getLevel(), targetPos, RiteProtectionObserver.class);
        if(stateObserver == null)
            stateObserver = StateObserverManager.get().addObserver(new RiteProtectionObserver(targetLevel, targetPos, radius + 1, radius + 1, radius + 1, block));
    }

}
