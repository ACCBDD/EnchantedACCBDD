package favouriteless.enchanted.common.circle_magic;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.init.EData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
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
		return level.getDataStorage().computeIfAbsent(tag -> load(level, tag), () -> new RiteManager(level), NAME);
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
	public CompoundTag save(CompoundTag tag) {
		CompoundTag out = new CompoundTag();
		ListTag riteList = new ListTag();
		Registry<RiteType> registry = level.registryAccess().registryOrThrow(EData.RITE_TYPES_REGISTRY);

		for(Rite rite : activeRites) {
			ResourceLocation typeId = registry.getKey(rite.getType());
			if(typeId == null)
				continue;

			CompoundTag riteTag = rite.save();

			riteTag.put("type", ResourceLocation.CODEC.encodeStart(NbtOps.INSTANCE, typeId).getOrThrow(false, Enchanted.LOG::error));
			riteTag.put("pos", BlockPos.CODEC.encodeStart(NbtOps.INSTANCE, rite.getPos()).getOrThrow(false, Enchanted.LOG::error));

			riteList.add(riteTag);
		}
		out.put("rites", riteList);
		return out;
	}

	public static RiteManager load(ServerLevel level, CompoundTag tag) {
		RiteManager manager = new RiteManager(level);
		ListTag riteList = tag.getList("rites", CompoundTag.TAG_COMPOUND);
		Registry<RiteType> registry = level.registryAccess().registryOrThrow(EData.RITE_TYPES_REGISTRY);

		for(int i = 0; i < riteList.size(); i++) {
			try {
				CompoundTag riteTag = riteList.getCompound(i);

				RiteType type = registry.get(ResourceLocation.CODEC.parse(NbtOps.INSTANCE, riteTag.get("type")).getOrThrow(false, Enchanted.LOG::error));
				BlockPos pos = BlockPos.CODEC.parse(NbtOps.INSTANCE, riteTag.get("pos")).getOrThrow(false, Enchanted.LOG::error);

				Rite rite = type.create(level, pos);
				rite.load(riteTag);

				manager.activeRites.add(rite);
			}
			catch(Exception e) {
				Enchanted.LOG.error("Failed to load Rite, skipping: {}", e.getMessage());
			}
		}

		return manager;
	}

}
