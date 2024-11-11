package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.curses.CurseType;
import net.favouriteless.enchanted.common.curses.CurseTypes;
import net.favouriteless.enchanted.common.rites.rites.ApplyCurseRite;
import net.favouriteless.enchanted.common.rites.rites.RemoveCurseRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.minecraft.resources.ResourceLocation;

public class RemoveCurseFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("remove_curse");

    public static final MapCodec<RemoveCurseFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("curse").forGetter(f -> f.curse.getId())
    ).apply(instance, RemoveCurseFactory::new));

    private final CurseType<?> curse;

    public RemoveCurseFactory(ResourceLocation id) {
        curse = CurseTypes.get(id);
        if(curse == null)
            throw new IllegalArgumentException("Error creating RemoveCurseFactory: " + id.toString() + " is not a curse type.");
    }

    @Override
    public Rite create(BaseRiteParams params) {
        return new RemoveCurseRite(params, curse);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}
