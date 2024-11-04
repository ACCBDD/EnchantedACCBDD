package favouriteless.enchanted.common.curses;

import favouriteless.enchanted.api.curses.Curse;
import favouriteless.enchanted.common.init.registry.CurseTypes;
import favouriteless.enchanted.common.network.packets.client.SinkingCursePacket;
import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.server.level.ServerLevel;

public class CurseSinking extends Curse {

	public boolean wasSwimming = false;
	public boolean wasFlying = false;

	public CurseSinking() {
		super(CurseTypes.SINKING);
	}

	@Override
	protected void onTick() {
		if(targetPlayer != null) {
			boolean isSwimming = targetPlayer.isInWater();
			boolean isFlying = targetPlayer.isFallFlying();

			if(isSwimming != wasSwimming || isFlying != wasFlying) {
				if(isSwimming)
					CommonServices.NETWORK.sendToPlayer(new SinkingCursePacket(-0.025D * (level + 1)), targetPlayer);
				else if(isFlying)
					CommonServices.NETWORK.sendToPlayer(new SinkingCursePacket(-0.05D * (level + 1)), targetPlayer);
				else
					CommonServices.NETWORK.sendToPlayer(new SinkingCursePacket(0.0D), targetPlayer);
				wasSwimming = isSwimming;
				wasFlying = isFlying;
			}
		}
	}

	@Override
	public void onRemove(ServerLevel level) {
		if(targetPlayer == null || targetPlayer.isRemoved())
			targetPlayer = level.getServer().getPlayerList().getPlayer(targetUUID);
		if(targetPlayer != null) {
			CommonServices.NETWORK.sendToPlayer(new SinkingCursePacket(0.0D), targetPlayer); // Reset the player's sinking when removed
		}
	}

}
