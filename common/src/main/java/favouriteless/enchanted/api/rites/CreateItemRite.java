package favouriteless.enchanted.api.rites;

import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

/**
 * Simple implementation of {@link AbstractRite} for creating and spawning a list of {@link ItemStack}s on execution.
 *
 * <p>Rites implementing this will automatically have compatibility for JEI.</p>
 */
public class CreateItemRite extends AbstractRite {

    private final ItemStack[] resultItems;

    public CreateItemRite(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster, ItemStack... resultItems) {
        super(type, level, pos, caster);
        this.resultItems = resultItems;
    }

    @Override
    public void execute() {
        if(getLevel() != null && !getLevel().isClientSide) {
            BlockPos pos = getPos();
            for(ItemStack stack : getResultItems()) {
                ItemEntity itemEntity = new ItemEntity(getLevel(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack.copy());
                getLevel().addFreshEntity(itemEntity);
            }
            getLevel().playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
            spawnMagicParticles();
        }
        stopExecuting();
    }

    /**
     * Use a copy of these itemstacks if making any changes.
     * @return An array containing the {@link ItemStack}s created by this rite.
     */
    public ItemStack[] getResultItems() {
        for(int i = 0; i < resultItems.length; i++) {
            setupItemNbt(i, resultItems[i]);
        }
        return resultItems;
    }

    /**
     * For any custom nbt which needs to be added to the {@link ItemStack}s at time of execution.
     * @param index The index of the {@link ItemStack} within the result items array.
     * @param stack The {@link ItemStack} being modified.
     */
    public void setupItemNbt(int index, ItemStack stack) {}

    /**
     * @return True if JEI entry should display additional requirements message, this includes the list of required
     * entities.
     */
    public boolean hasAdditionalRequirements() {
        return false;
    }

}
