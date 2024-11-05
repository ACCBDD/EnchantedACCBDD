package net.favouriteless.enchanted.mixin.common;

import net.favouriteless.enchanted.common.lootextensions.LootExtensions;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @ModifyReturnValue(method="getDrops", at=@At("RETURN"))
    public List<ItemStack> getDrops(List<ItemStack> original, BlockState state, LootParams.Builder params) {
        List<ItemStack> out = original.isEmpty() ? new ObjectArrayList<>() : original;
        out.addAll(LootExtensions.tryRollBlock(state, params));
        return out;
    }

}
