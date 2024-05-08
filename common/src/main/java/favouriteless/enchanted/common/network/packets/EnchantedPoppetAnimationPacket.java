package favouriteless.enchanted.common.network.packets;

import favouriteless.enchanted.client.particles.types.TwoToneColouredParticleType.TwoToneColouredData;
import favouriteless.enchanted.client.render.poppet.PoppetAnimationManager;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
import favouriteless.enchanted.common.items.poppets.AbstractPoppetItem;
import favouriteless.enchanted.common.network.EnchantedPacket;
import favouriteless.enchanted.common.poppet.PoppetColour;
import favouriteless.enchanted.common.poppet.PoppetHelper.PoppetResult;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class EnchantedPoppetAnimationPacket implements EnchantedPacket {

	private final PoppetResult result;
	private final ItemStack item;
	private final int entityId;

	public EnchantedPoppetAnimationPacket(PoppetResult result, ItemStack itemStack, int entityId) {
		this.result = result;
		this.item = itemStack;
		this.entityId = entityId;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeEnum(result);
		buffer.writeItem(item);
		buffer.writeInt(entityId);
	}

	public static EnchantedPoppetAnimationPacket decode(FriendlyByteBuf buffer) {
		return new EnchantedPoppetAnimationPacket(
				buffer.readEnum(PoppetResult.class),
				buffer.readItem(),
				buffer.readInt()
		);
	}

	@Override
	public void handle(ServerPlayer sender) {
		Minecraft mc = Minecraft.getInstance();
		Entity entity = mc.level.getEntity(entityId);
		if(entity != null) {
			if(item.getItem() instanceof AbstractPoppetItem) {
				PoppetColour poppetColour = ((AbstractPoppetItem)item.getItem()).colour;
				mc.particleEngine.createTrackingEmitter(entity, new TwoToneColouredData(EnchantedParticleTypes.POPPET.get(),
						poppetColour.rPrimary, poppetColour.gPrimary, poppetColour.gSecondary,
						poppetColour.rSecondary, poppetColour.gSecondary, poppetColour.bSecondary), 40);

				if(entity == mc.player)
					PoppetAnimationManager.startAnimation(result, item);
			}
		}
	}

}
