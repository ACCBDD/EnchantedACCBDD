package favouriteless.enchanted.common.altar;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.api.power.IPowerConsumer.IPowerPosHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple {@link IPowerPosHolder} implementation which stores
 * the provided {@link BlockPos} by their distance from the holder.
 */
public class SimplePowerPosHolder implements IPowerPosHolder {

	private final List<BlockPos> altars = new ArrayList<>();
	private final BlockPos pos;

	public SimplePowerPosHolder(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	@NotNull
	public List<BlockPos> getPositions() {
		return altars;
	}

	@Override
	public void remove(BlockPos altarPos) {
		altars.remove(altarPos);
	}

	@Override
	public void add(BlockPos altarPos) {
		if(altars.isEmpty()) {
			altars.add(altarPos);
		}
		else {
			if(!altars.contains(pos)) {
				for(int i = 0; i < altars.size(); i++) {
					if(pos.distSqr(altarPos) < pos.distSqr(altars.get(i))) {
						altars.add(i, altarPos);
						return;
					}
					else if(i == altars.size() - 1) {
						altars.add(altarPos);
						return;
					}
				}
			}
		}
	}

	@Override
	public CompoundTag serialize() {
		CompoundTag tag = new CompoundTag();
		tag.put("altars", BlockPos.CODEC.listOf().encodeStart(NbtOps.INSTANCE, altars).getOrThrow(false, Enchanted.LOG::error));
		return tag;
	}

	@Override
	public void deserialize(CompoundTag tag) {
		altars.clear();
		altars.addAll(BlockPos.CODEC.listOf().parse(NbtOps.INSTANCE, tag.get("altars")).getOrThrow(false, Enchanted.LOG::error));
	}

}
