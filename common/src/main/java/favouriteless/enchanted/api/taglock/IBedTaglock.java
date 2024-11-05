package favouriteless.enchanted.api.taglock;

import favouriteless.enchanted.api.ISerializable;
import favouriteless.enchanted.common.items.component.TaglockData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public interface IBedTaglock extends ISerializable<CompoundTag> {

    TaglockData getData();

    void setData(TaglockData data);

}
