package favouriteless.enchanted.common.init.registry;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.api.rites.AbstractRite;
import favouriteless.enchanted.api.rites.CreateItemRite;
import favouriteless.enchanted.common.circle_magic.RiteType;
import favouriteless.enchanted.common.circle_magic.rites.binding.*;
import favouriteless.enchanted.common.circle_magic.rites.curse.*;
import favouriteless.enchanted.common.circle_magic.rites.entity.*;
import favouriteless.enchanted.common.circle_magic.rites.world.*;
import favouriteless.enchanted.common.circle_magic.rites.entity.protection.RiteProtection;
import favouriteless.enchanted.common.circle_magic.rites.entity.protection.RiteProtectionTemporary;
import favouriteless.enchanted.common.circle_magic.rites.processing.RiteBroiling;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RiteTypes {

    private static final Map<ResourceLocation, RiteType<?>> RITE_TYPES = new HashMap<>();

    public static final RiteType<RiteBindingFamiliar> BINDING_FAMILIAR = register("binding_familiar", RiteBindingFamiliar::new);
    public static final RiteType<RiteBindingTalisman> BINDING_TALISMAN = register("binding_talisman", RiteBindingTalisman::new);
    public static final RiteType<RiteBindingTalisman> BINDING_TALISMAN_CHARGED = register("binding_talisman_charged", RiteBindingTalisman::new);
    public static final RiteType<RiteBindingWaystone> BINDING_WAYSTONE = register("binding_waystone", RiteBindingWaystone::new);
    public static final RiteType<RiteBindingWaystone> BINDING_WAYSTONE_CHARGED = register("binding_waystone_charged", RiteBindingWaystone::new);
    public static final RiteType<RiteBindingWaystoneDuplicate> BINDING_WAYSTONE_DUPLICATE = register("binding_waystone_duplicate", RiteBindingWaystoneDuplicate::new);
    public static final RiteType<RiteBindingWaystoneDuplicate> BINDING_WAYSTONE_DUPLICATE_CHARGED = register("binding_waystone_duplicate_charged", RiteBindingWaystoneDuplicate::new);
    public static final RiteType<RiteBindingWaystonePlayer> BINDING_WAYSTONE_PLAYER = register("binding_waystone_player", RiteBindingWaystonePlayer::new);
    public static final RiteType<RiteBindingWaystonePlayer> BINDING_WAYSTONE_PLAYER_CHARGED = register("binding_waystone_player_charged", RiteBindingWaystonePlayer::new);
    public static final RiteType<RiteBroiling> BROILING = register("broiling", RiteBroiling::new);
    public static final RiteType<RiteBroiling> BROILING_CHARGED = register("broiling_charged", RiteBroiling::new);
    public static final RiteType<CreateItemRite> CHARGING_STONE = register("charging_stone", (type, level, pos, caster) -> new CreateItemRite(type, level, pos, caster, EnchantedItems.ATTUNED_STONE_CHARGED.get().getDefaultInstance()));
    public static final RiteType<RiteCurseBlight> CURSE_OF_BLIGHT = register("curse_blight", RiteCurseBlight::new);
    public static final RiteType<RiteCurseMisfortune> CURSE_OF_MISFORTUNE = register("curse_misfortune", RiteCurseMisfortune::new);
    public static final RiteType<RiteCurseOverheating> CURSE_OF_OVERHEATING = register("curse_overheating", RiteCurseOverheating::new);
    public static final RiteType<RiteCurseSinking> CURSE_OF_SINKING = register("curse_sinking", RiteCurseSinking::new);
    public static final RiteType<RiteFertility> FERTILITY = register("fertility", RiteFertility::new);
    public static final RiteType<RiteFertility> FERTILITY_CHARGED = register("fertility_charged", RiteFertility::new);
    public static final RiteType<RiteForest> FOREST = register("forest", RiteForest::new);
    public static final RiteType<RiteImprisonment> IMPRISONMENT = register("imprisonment", RiteImprisonment::new);
    public static final RiteType<CreateItemRite> INFUSION_BROOM = register("infusion_broom", (type, level, pos, caster) -> new CreateItemRite(type, level, pos, caster, EnchantedItems.ENCHANTED_BROOMSTICK.get().getDefaultInstance()));
    public static final RiteType<RiteProtection> PROTECTION = register("protection", (type, level, pos, caster) -> new RiteProtection(type, level, pos, caster, 4));
    public static final RiteType<RiteProtection> PROTECTION_LARGE = register("protection_large", (type, level, pos, caster) -> new RiteProtection(type, level, pos, caster, 6));
    public static final RiteType<RiteProtectionTemporary> PROTECTION_TEMPORARY = register("protection_temporary", RiteProtectionTemporary::new);
    public static final RiteType<RiteRemoveMisfortune> REMOVE_MISFORTUNE = register("remove_misfortune", RiteRemoveMisfortune::new);
    public static final RiteType<RiteRemoveOverheating> REMOVE_OVERHEATING = register("remove_overheating", RiteRemoveOverheating::new);
    public static final RiteType<RiteRemoveSinking> REMOVE_SINKING = register("remove_sinking", RiteRemoveSinking::new);
    public static final RiteType<RiteSanctity> SANCTITY = register("sanctity", RiteSanctity::new);
    public static final RiteType<RiteSkyWrath> SKY_WRATH = register("sky_wrath", RiteSkyWrath::new);
    public static final RiteType<RiteSkyWrath> SKY_WRATH_CHARGED = register("sky_wrath_charged", RiteSkyWrath::new);
    public static final RiteType<RiteSummonEntity> SUMMON_ENTITY = register("summon_entity", RiteSummonEntity::new);
    public static final RiteType<RiteSummonFamiliar> SUMMON_FAMILIAR = register("summon_familiar", RiteSummonFamiliar::new);
    public static final RiteType<RiteTotalEclipse> TOTAL_ECLIPSE = register("total_eclipse", RiteTotalEclipse::new);
    public static final RiteType<RiteTotalEclipse> TOTAL_ECLIPSE_CHARGED = register("total_eclipse_charged", RiteTotalEclipse::new);
    public static final RiteType<RiteTranspositionIron> TRANSPOSITION_IRON = register("transposition_iron", RiteTranspositionIron::new);
    public static final RiteType<RiteTranspositionPlayer> TRANSPOSITION_PLAYER = register("transposition_player", RiteTranspositionPlayer::new);
    public static final RiteType<RiteTranspositionPlayerBlooded> TRANSPOSITION_PLAYER_BLOODED = register("transposition_player_blooded", RiteTranspositionPlayerBlooded::new);

    /**
     * Register a {@link RiteType} to be used by Enchanted.
     */
    public static <T extends AbstractRite> RiteType<T> register(ResourceLocation id, RiteType.RiteFactory<T> factory) {
        RiteType<T> riteType = new RiteType<>(id, factory);
        RITE_TYPES.put(id, riteType);
        return riteType;
    }

    private static <T extends AbstractRite> RiteType<T> register(String id, RiteType.RiteFactory<T> factory) {
        return register(Enchanted.id(id), factory);
    }

    public static RiteType<?> get(ResourceLocation id) {
        return RITE_TYPES.get(id);
    }

    /**
     * Attempt to create a rite instance in the specified {@link ServerLevel} at the specified {@link BlockPos} if the
     * requirements for the rite are met at that position.
     *
     * @param level The {@link ServerLevel} to create the Rite in.
     * @param pos The {@link BlockPos} to create the Rite at.
     *
     * @return An instance of the {@link AbstractRite} implementation which has its requirements met at level, pos. Or
     * if no rites have their requirements met, null.
     */
    public static AbstractRite getRiteAt(ServerLevel level, BlockPos pos, UUID caster) {
        AbstractRite currentRite = null;
        int currentDiff = Integer.MAX_VALUE;

        for(RiteType<?> type : RITE_TYPES.values()) {
            AbstractRite rite = type.create(level, pos, caster);
            int diff = rite.differenceAt(level, pos);
            if(diff != -1 && diff < currentDiff) {
                currentRite = rite;
                currentDiff = diff;
            }
        }
        return currentRite;
    }

    /**
     * This method is only used by patchouli and/or JEI to grab the requirements of a rite.
     */
    public static AbstractRite getDefaultByName(ResourceLocation id) {
        RiteType<?> type = RITE_TYPES.get(id);
        return type != null ? type.create() : null;
    }

    /**
     * Get a collection of all entries for the registry.
     * @return Registry values collection.
     */
    public static Collection<RiteType<?>> getEntries() {
        return RITE_TYPES.values();
    }

    public static Collection<ResourceLocation> getKeys() {
        return RITE_TYPES.keySet();
    }

}
