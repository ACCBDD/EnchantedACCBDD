package favouriteless.enchanted.common.curses;

import favouriteless.enchanted.api.curses.RandomCurse;
import favouriteless.enchanted.common.init.EnchantedTags.Biomes;
import favouriteless.enchanted.common.init.registry.CurseTypes;
import net.minecraft.world.level.Level;

public class CurseOverheating extends RandomCurse {

	public CurseOverheating() {
		super(CurseTypes.OVERHEATING, 30, 90); // Executes once every 0.5-1.5 minutes
	}

	@Override
	protected void execute() {
		if(targetPlayer.level().getBiome(targetPlayer.blockPosition()).is(Biomes.OVERHEATING_BIOMES)
		|| targetPlayer.level().dimension() == Level.NETHER) {
			int duration = 4;
			for(int i = 0; i < level; i++) {
				if(Math.random() < 0.75D)
					duration += 4;
			}
			targetPlayer.setSecondsOnFire(duration);
		}
	}

}
