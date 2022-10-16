/*
 * Copyright (c) 2022. Favouriteless
 * Enchanted, a minecraft mod.
 * GNU GPLv3 License
 *
 *     This file is part of Enchanted.
 *
 *     Enchanted is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Enchanted is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Enchanted.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.favouriteless.enchanted.common.blocks.crops;

import com.favouriteless.enchanted.common.entities.MandrakeEntity;
import com.favouriteless.enchanted.common.init.EnchantedBlocks;
import com.favouriteless.enchanted.common.init.EnchantedEntityTypes;
import com.favouriteless.enchanted.common.init.EnchantedItems;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MandrakeBlock extends CropsBlockAgeFive {

    public MandrakeBlock(Properties properties) {
        super(properties);
    }

    protected ItemLike getBaseSeedId() {
        return EnchantedItems.MANDRAKE_SEEDS.get();
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity tileEntity, ItemStack stack) {
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);

        if(!world.isClientSide() && world.getDifficulty() != Difficulty.PEACEFUL) {
            if (state.is(EnchantedBlocks.MANDRAKE.get()) && state.getValue(AGE_FIVE) == 4) {
                if (world.isDay()) {
                    spawnMandrake(world, pos);
                    return;
                } else { // 1/5 Chance to "wake up" mandrake at night
                    if (RANDOM.nextInt(5) == 0) {
                        spawnMandrake(world, pos);
                        return;
                    }
                }
            }
        }
        dropResources(state, world, pos, tileEntity, player, stack);
    }

    public static void spawnMandrake(Level world, BlockPos pos) {
        MandrakeEntity entity = EnchantedEntityTypes.MANDRAKE.get().create(world);
        entity.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.addFreshEntity(entity);
    }

}
