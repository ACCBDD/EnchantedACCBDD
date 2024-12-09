package net.favouriteless.enchanted.datagen.providers.tag;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.init.EnchantedDamageTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagProvider extends DamageTypeTagsProvider {

    public DamageTypeTagProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider, Enchanted.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        addVanillaTags(provider);
    }

    public void addVanillaTags(Provider provider) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
                .add(EnchantedDamageTypes.SACRIFICE, EnchantedDamageTypes.SOUND, EnchantedDamageTypes.VOODOO);
        tag(DamageTypeTags.BYPASSES_EFFECTS)
                .add(EnchantedDamageTypes.SACRIFICE, EnchantedDamageTypes.SOUND, EnchantedDamageTypes.VOODOO);
        tag(DamageTypeTags.BYPASSES_COOLDOWN)
                .add(EnchantedDamageTypes.SACRIFICE);
        tag(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .add(EnchantedDamageTypes.SACRIFICE, EnchantedDamageTypes.SOUND, EnchantedDamageTypes.VOODOO);
        tag(DamageTypeTags.BYPASSES_INVULNERABILITY)
                .add(EnchantedDamageTypes.SACRIFICE);
        tag(DamageTypeTags.BYPASSES_RESISTANCE)
                .add(EnchantedDamageTypes.SACRIFICE, EnchantedDamageTypes.SOUND, EnchantedDamageTypes.VOODOO);
    }

}
