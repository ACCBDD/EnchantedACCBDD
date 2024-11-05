package favouriteless.enchanted.common.blocks.entity;

import favouriteless.enchanted.api.power.IPowerConsumer;
import favouriteless.enchanted.api.rites.AbstractRite;
import favouriteless.enchanted.common.altar.SimplePowerPosHolder;
import favouriteless.enchanted.common.init.registry.EBlockEntityTypes;
import favouriteless.enchanted.common.init.registry.RiteTypes;
import favouriteless.enchanted.common.rites.RiteManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class ChalkGoldBlockEntity extends BlockEntity implements IPowerConsumer {

    private final SimplePowerPosHolder posHolder;
    private AbstractRite currentRite = null;

    public ChalkGoldBlockEntity(BlockPos pos, BlockState state) {
        super(EBlockEntityTypes.CHALK_GOLD.get(), pos, state);
        this.posHolder = new SimplePowerPosHolder(pos);
    }

    public void execute(BlockState state, Level _level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!_level.isClientSide && _level instanceof ServerLevel level) {
            if (currentRite == null) {

                AbstractRite rite = RiteTypes.getRiteAt(level, pos, player.getUUID());

                if (rite != null) {
                    currentRite = rite;
                    RiteManager.addRite(currentRite);
                    currentRite.start();
                } else {
                    level.playSound(null, pos.getX(), pos.getY() + 1, pos.getZ(), SoundEvents.NOTE_BLOCK_SNARE.value(), SoundSource.MASTER, 2f, 1f);
                }
            }
            else {
                if(!currentRite.isStarting())
                    currentRite.stopExecuting();
            }
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(t instanceof ChalkGoldBlockEntity blockEntity) {
            if(level != null && blockEntity.currentRite != null) {
                double dx = pos.getX() + Math.random();
                double dy = pos.getY() + Math.random() * 0.3D;
                double dz = pos.getZ() + Math.random();
                ((ServerLevel) level).sendParticles(new DustParticleOptions(new Vector3f(254 / 255F, 94 / 255F, 94 / 255F), 1.0F), dx, dy, dz, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public void setRite(AbstractRite rite) {
        currentRite = rite;;
    }

    public void clearRite() {
        currentRite = null;
    }

    public void stopRite() {
        currentRite.stopExecuting();
        clearRite();
    }

    public AbstractRite getRite() {
        return this.currentRite;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("posHolder", posHolder.serialize());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        posHolder.deserialize(nbt.getList("posHolder", 10));
    }

    @Override
    public @NotNull IPowerConsumer.IPowerPosHolder getPosHolder() {
        return posHolder;
    }
}
