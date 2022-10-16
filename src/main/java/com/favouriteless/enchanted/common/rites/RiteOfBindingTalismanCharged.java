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

package com.favouriteless.enchanted.common.rites;

import com.favouriteless.enchanted.common.init.EnchantedItems;
import com.favouriteless.enchanted.common.init.EnchantedRiteTypes;
import com.favouriteless.enchanted.common.util.rite.RiteType;
import net.minecraft.world.item.Items;

public class RiteOfBindingTalismanCharged extends RiteOfBindingTalisman {

    public RiteOfBindingTalismanCharged() {
        super(0, 0); // Power, power per tick
        ITEMS_REQUIRED.put(EnchantedItems.CIRCLE_TALISMAN.get(), 1);
        ITEMS_REQUIRED.put(Items.GLOWSTONE_DUST, 1);
        ITEMS_REQUIRED.put(EnchantedItems.ATTUNED_STONE_CHARGED.get(), 1);
    }

    @Override
    public RiteType<?> getType() {
        return EnchantedRiteTypes.BINDING_TALISMAN_CHARGED.get();
    }

}
