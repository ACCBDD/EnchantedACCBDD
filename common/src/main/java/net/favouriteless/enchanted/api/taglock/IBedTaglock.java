package net.favouriteless.enchanted.api.taglock;

import net.favouriteless.enchanted.api.ISerializable;
import net.favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.nbt.CompoundTag;

public interface IBedTaglock extends ISerializable<CompoundTag> {

    EntityRefData getData();

    void setData(EntityRefData data);

}
