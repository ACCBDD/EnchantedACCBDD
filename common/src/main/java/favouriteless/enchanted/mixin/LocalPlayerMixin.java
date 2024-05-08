package favouriteless.enchanted.mixin;

import favouriteless.enchanted.client.client_handlers.misc.EnchantedClientValues;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

	@Inject(method="aiStep", at=@At("TAIL"))
	private void aiStep(CallbackInfo ci) {
		LocalPlayer player = (LocalPlayer)(Object)this;
		player.setDeltaMovement(player.getDeltaMovement().add(0.0D, EnchantedClientValues.CURSE_SINKING_SPEED, 0.0D));
	}

}
