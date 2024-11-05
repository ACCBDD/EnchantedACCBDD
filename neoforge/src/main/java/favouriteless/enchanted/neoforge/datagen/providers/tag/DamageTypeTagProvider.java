package favouriteless.enchanted.neoforge.datagen.providers.tag;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.EDamageTypes;
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
                .add(EDamageTypes.SACRIFICE, EDamageTypes.SOUND, EDamageTypes.VOODOO);
        tag(DamageTypeTags.BYPASSES_EFFECTS)
                .add(EDamageTypes.SACRIFICE, EDamageTypes.SOUND, EDamageTypes.VOODOO);
        tag(DamageTypeTags.BYPASSES_COOLDOWN)
                .add(EDamageTypes.SACRIFICE);
        tag(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .add(EDamageTypes.SACRIFICE, EDamageTypes.SOUND, EDamageTypes.VOODOO);
        tag(DamageTypeTags.BYPASSES_INVULNERABILITY)
                .add(EDamageTypes.SACRIFICE);
        tag(DamageTypeTags.BYPASSES_RESISTANCE)
                .add(EDamageTypes.SACRIFICE, EDamageTypes.SOUND, EDamageTypes.VOODOO);
    }

}
