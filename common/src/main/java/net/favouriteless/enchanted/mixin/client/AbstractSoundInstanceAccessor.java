package net.favouriteless.enchanted.mixin.client;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractSoundInstance.class)
public interface AbstractSoundInstanceAccessor {

    @Accessor("volume")
    void setVolume(float volume);

    @Accessor("volume")
    float getVolume();

}