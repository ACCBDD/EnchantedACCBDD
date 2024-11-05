package net.favouriteless.enchanted.api.taglock;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link BedTaglockSavedData} is where the UUID and name of the last player to use a {@link BedBlockEntity} is stored,
 * which is used for taglocks.
 */
public class BedTaglockSavedData extends SavedData {

    private static final String NAME = "enchanted_bed_taglocks";
    private final Map<BlockPos, IBedTaglock> entries = new HashMap<>();

    public BedTaglockSavedData() {
        super();
    }

    /**
     * @param bed The {@link BlockEntity} to grab data from.
     *
     * @return The {@link IBedTaglock} associated with a specific {@link BlockEntity}.
     */
    public IBedTaglock getEntry(BlockEntity bed) {
        return getEntry(bed.getBlockPos());
    }

    /**
     * @param level The {@link Level} to grab data from.
     *
     * @return An instance of {@link BedTaglockSavedData} belonging to level.
     */
    public static BedTaglockSavedData get(Level level) {
        if(level instanceof ServerLevel serverLevel)
            return serverLevel.getDataStorage().computeIfAbsent(new Factory<>(BedTaglockSavedData::new, BedTaglockSavedData::load, null), NAME);
        else
            throw new RuntimeException("Game attempted to load serverside taglock (bed) data from a clientside world.");
    }

    // -------------------- IMPLEMENTATION  DETAILS BELOW THIS POINT, NOT NEEDED FOR API USERS --------------------

    private IBedTaglock getEntry(BlockPos pos) {
        return entries.computeIfAbsent(pos, (_pos) -> new BedTaglockImpl());
    }

    private static BedTaglockSavedData load(CompoundTag nbt, Provider registries) {
        BedTaglockSavedData data = new BedTaglockSavedData();
        ListTag entryList = nbt.getList("entryList", Tag.TAG_COMPOUND);

        for(Tag e : entryList) {
            CompoundTag entryNbt = (CompoundTag)e; // This cast should be safe.
            data.getEntry(BlockPos.of(entryNbt.getLong("pos"))).deserialize(entryNbt);
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, Provider registries) {
        ListTag list = new ListTag();

        entries.forEach((pos, data) -> {
            if(data.getData() != EntityRefData.EMPTY) {
                CompoundTag entryTag = data.serialize();
                entryTag.putLong("pos", pos.asLong());
            }
        });

        tag.put("entries", list);
        return tag;
    }



    private static class BedTaglockImpl implements IBedTaglock {

        private EntityRefData data = EntityRefData.EMPTY;

        private BedTaglockImpl() {}

        @Override
        public CompoundTag serialize() {
            CompoundTag tag = new CompoundTag();
            tag.put("taglockData", EntityRefData.CODEC.encode(data, NbtOps.INSTANCE, new CompoundTag()).getOrThrow());
            return tag;
        }

        @Override
        public void deserialize(CompoundTag tag) {
            data = EntityRefData.CODEC.parse(NbtOps.INSTANCE, tag.get("taglockData"))
                    .resultOrPartial(e -> Enchanted.LOG.error("Tried to load invalid Taglock data: '{}'", e))
                    .orElse(EntityRefData.EMPTY);
        }

        @Override
        public @NotNull EntityRefData getData() {
            return data;
        }

        @Override
        public void setData(@NotNull EntityRefData data) {
            this.data = data;
        }
    }

}