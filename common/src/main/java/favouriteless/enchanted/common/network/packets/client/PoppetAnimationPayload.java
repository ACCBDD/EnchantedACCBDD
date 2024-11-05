package favouriteless.enchanted.common.network.packets.client;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.client.particles.types.TwoToneColouredParticleType.TwoToneColouredData;
import favouriteless.enchanted.client.render.poppet.PoppetAnimationManager;
import favouriteless.enchanted.common.init.registry.EParticleTypes;
import favouriteless.enchanted.common.items.poppets.PoppetItem;
import favouriteless.enchanted.common.network.PacketContext;
import favouriteless.enchanted.common.poppet.PoppetColour;
import favouriteless.enchanted.common.poppet.PoppetUseResult.ResultType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class PoppetAnimationPayload implements CustomPacketPayload {

	public static final Type<PoppetAnimationPayload> TYPE = new Type<>(Enchanted.id("poppet_animation"));

	public static final StreamCodec<RegistryFriendlyByteBuf, PoppetAnimationPayload> STREAM_CODEC = StreamCodec.composite(
			ResultType.STREAM_CODEC, p -> p.result,
			ItemStack.STREAM_CODEC, p -> p.item,
			ByteBufCodecs.INT, p -> p.entityId,
			PoppetAnimationPayload::new
	);

	private final ResultType result;
	private final ItemStack item;
	private final int entityId;

	public PoppetAnimationPayload(ResultType result, ItemStack itemStack, int entityId) {
		this.result = result;
		this.item = itemStack;
		this.entityId = entityId;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return null;
	}

	public static void handle(PoppetAnimationPayload payload, PacketContext context) {
		Minecraft mc = Minecraft.getInstance();
		Entity entity = mc.level.getEntity(payload.entityId);
		if(entity != null) {
			if(payload.item.getItem() instanceof PoppetItem) {
				PoppetColour poppetColour = ((PoppetItem)payload.item.getItem()).colour;
				mc.particleEngine.createTrackingEmitter(entity, new TwoToneColouredData(EParticleTypes.POPPET.get(),
						poppetColour.rPrimary, poppetColour.gPrimary, poppetColour.gSecondary,
						poppetColour.rSecondary, poppetColour.gSecondary, poppetColour.bSecondary), 40);

				if(entity == mc.player)
					PoppetAnimationManager.startAnimation(payload.result, payload.item);
			}
		}
	}

}
