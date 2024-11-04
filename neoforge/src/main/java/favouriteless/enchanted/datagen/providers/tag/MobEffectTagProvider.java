package favouriteless.enchanted.datagen.providers.tag;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.init.EnchantedTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MobEffectTagProvider extends IntrinsicHolderTagsProvider<MobEffect> {

    public MobEffectTagProvider(PackOutput output, CompletableFuture<Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.MOB_EFFECT, provider, mobEffect -> ForgeRegistries.MOB_EFFECTS.getResourceKey(mobEffect).orElse(null), Enchanted.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        addEnchantedTags(provider);
    }

    public void addEnchantedTags(Provider provider) {
        tag(EnchantedTags.MobEffects.BLIGHT_EFFECTS)
                .add(MobEffects.BLINDNESS,  MobEffects.CONFUSION, MobEffects.HUNGER, MobEffects.POISON,
                        MobEffects.WEAKNESS, MobEffects.WITHER);
        tag(EnchantedTags.MobEffects.FERTILITY_CURE_EFFECTS)
                .add(MobEffects.BLINDNESS,  MobEffects.CONFUSION, MobEffects.HUNGER, MobEffects.POISON,
                        MobEffects.WEAKNESS, MobEffects.WITHER);
        tag(EnchantedTags.MobEffects.MISFORTUNE_EFFECTS)
                .add(MobEffects.BLINDNESS,  MobEffects.CONFUSION, MobEffects.DIG_SLOWDOWN, MobEffects.HUNGER,
                        MobEffects.MOVEMENT_SLOWDOWN, MobEffects.UNLUCK ,MobEffects.WEAKNESS);
    }

}
