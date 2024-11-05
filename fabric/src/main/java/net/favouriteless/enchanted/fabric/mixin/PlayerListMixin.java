package net.favouriteless.enchanted.fabric.mixin;

import net.favouriteless.enchanted.common.CommonEvents;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class PlayerListMixin {

    @Inject(method="placeNewPlayer", at=@At("TAIL"))
    private void placeNewPlayer(Connection netManager, ServerPlayer player, CallbackInfo ci) {
        CommonEvents.onPlayerLoggedIn(player);
    }

    @Inject(method="remove", at=@At("HEAD"))
    private void placeNewPlayer(ServerPlayer player, CallbackInfo ci) {
        CommonEvents.onPlayerLoggedOut(player);
    }

}
