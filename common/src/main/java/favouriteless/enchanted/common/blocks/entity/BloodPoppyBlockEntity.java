package favouriteless.enchanted.common.blocks.entity;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.registry.EBlockEntityTypes;
import favouriteless.enchanted.common.items.component.TaglockData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BloodPoppyBlockEntity extends BlockEntity {

    private TaglockData data = TaglockData.EMPTY;

    public BloodPoppyBlockEntity(BlockPos pos, BlockState state) {
        super(EBlockEntityTypes.BLOOD_POPPY.get(), pos, state);
    }

    public void setTaglockData(TaglockData data) {
        this.data = data;
    }

    public TaglockData getTaglockData() {
        return data;
    }

    public void reset() {
        data = TaglockData.EMPTY;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, @NotNull Provider registries) {
        tag.put("taglockData", TaglockData.CODEC.encode(data, NbtOps.INSTANCE, new CompoundTag()).getOrThrow());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, @NotNull Provider registries) {
        data = TaglockData.CODEC.parse(NbtOps.INSTANCE, tag.get("taglockData"))
                .resultOrPartial(e -> Enchanted.LOG.error("Tried to load invalid taglock data: '{}'", e))
                .orElse(TaglockData.EMPTY);
    }

}
