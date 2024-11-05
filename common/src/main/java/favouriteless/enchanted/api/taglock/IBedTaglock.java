package favouriteless.enchanted.api.taglock;

import favouriteless.enchanted.api.ISerializable;
import favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.nbt.CompoundTag;

public interface IBedTaglock extends ISerializable<CompoundTag> {

    EntityRefData getData();

    void setData(EntityRefData data);

}
