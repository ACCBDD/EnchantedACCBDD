package net.favouriteless.enchanted.common.rites;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum RiteTargetingType implements StringRepresentable {
    DEFAULT,
    WAYSTONE,
    ENTITY;

    public static final Codec<RiteTargetingType> CODEC = StringRepresentable.fromEnum(RiteTargetingType::values);

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase();
    }

}
