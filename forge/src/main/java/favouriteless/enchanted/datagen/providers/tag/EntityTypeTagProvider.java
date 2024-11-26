package favouriteless.enchanted.datagen.providers.tag;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.EnchantedTags.EntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTagProvider extends IntrinsicHolderTagsProvider<EntityType<?>> {


    public EntityTypeTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ENTITY_TYPE, lookupProvider, entityType -> entityType.builtInRegistryHolder().key(), Enchanted.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        addEnchantedTags();
    }

    public void addEnchantedTags() {
        tag(EntityTypes.MONSTERS)
                .add(EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED,
                        EntityType.ELDER_GUARDIAN, EntityType.ENDERMITE, EntityType.GHAST, EntityType.GIANT,
                        EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.ILLUSIONER,
                        EntityType.MAGMA_CUBE, EntityType.PHANTOM, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE,
                        EntityType.PILLAGER, EntityType.RAVAGER, EntityType.SHULKER, EntityType.SILVERFISH,
                        EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX,
                        EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER,
                        EntityType.ZOMBIFIED_PIGLIN, EntityType.ZOGLIN, EntityType.WARDEN);
        tag(EntityTypes.TAGLOCK_BLACKLIST)
                .add(EntityType.ENDER_DRAGON, EntityType.WITHER, EntityType.WARDEN, EnchantedEntityTypes.FAMILIAR_CAT.get());
    }

}
