package net.favouriteless.enchanted.common.rites;

import net.favouriteless.enchanted.common.init.EData;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;
import java.util.List;

public class RiteManager extends SavedData {

    private static final String NAME = "enchanted_rites";
    private final List<Rite> activeRites = new ArrayList<>();
    public final ServerLevel level;

    public RiteManager(ServerLevel level) {
        this.level = level;
    }

    public static RiteManager get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(() -> new RiteManager(level), (tag, registries) -> load(level, tag), null), NAME);
    }

    public static void addRite(ServerLevel level, Rite rite) {
        RiteManager manager = get(level);
        manager.activeRites.add(rite);
        manager.setDirty();
    }

    public static void removeRite(ServerLevel level, Rite rite) {
        RiteManager manager = get(level);
        manager.activeRites.remove(rite);
        manager.setDirty();
    }

    public static void tick(ServerLevel level) {
        RiteManager manager = get(level);
        manager.activeRites.removeIf(rite -> !rite.tick());
        manager.setDirty();
    }

    public static Rite getRiteAt(ServerLevel level, BlockPos pos) {
        RiteManager manager = get(level);
        for(Rite rite : manager.activeRites) {
            if(rite.getPos().equals(pos))
                return rite;
        }
        return null;
    }

    @Override
    public CompoundTag save(CompoundTag tag, Provider registries) {
        CompoundTag out = new CompoundTag();
        ListTag riteList = new ListTag();
        for(Rite rite : activeRites) {
            riteList.add(rite.save(level));
        }
        out.put("rites", riteList);
        return out;
    }

    public static RiteManager load(ServerLevel level, CompoundTag tag) {
        RiteManager data = new RiteManager(level);

        ListTag riteList = tag.getList("rites", CompoundTag.TAG_COMPOUND);
        for(int i = 0; i < riteList.size(); i++) {
            CompoundTag riteTag = riteList.getCompound(i);

            ResourceLocation id = ResourceLocation.CODEC.parse(NbtOps.INSTANCE, riteTag.get("type")).getOrThrow();
            RiteType type = level.registryAccess().registryOrThrow(EData.RITE_TYPES_REGISTRY).get(id);
            if(type != null) {
                Rite rite = type.create(level, null, null, new ArrayList<>());
                rite.load(riteTag, level);
                data.activeRites.add(rite);
            }
        }

        return data;
    }

}
