package favouriteless.enchanted.common.init.registry;

import favouriteless.enchanted.common.altar.AltarPowerProvider;

import java.util.ArrayList;
import java.util.List;

public class PowerProviderRegistry<T> {

	private final List<AltarPowerProvider<T>> providers = new ArrayList<>();

	public void register(AltarPowerProvider<T> provider) {
		if(!providers.isEmpty()) {
			for(int i = 0; i < providers.size(); i++) {
				AltarPowerProvider<T> oldProvider = providers.get(i);

				if(provider.getPower() > oldProvider.getPower()) { // Prioritise the highest power per block first.
					providers.add(i, provider);
					break;
				}
				else if(provider.getPower() == oldProvider.getPower()) { // Then prioritise whatever has the highest limit
					if(provider.getLimit() > oldProvider.getLimit()) {
						providers.add(i, provider);
						break;
					}
				}
				else if(i == providers.size() - 1) { // Lastly tack on end if at end of list
					providers.add(provider);
				}
			}
		}
		else {
			providers.add(provider);
		}
	}

	public AltarPowerProvider<T> get(T key) {
		for(AltarPowerProvider<T> provider : providers) {
			if(provider.is(key))
				return provider;
		}
		return null;
	}

	public List<AltarPowerProvider<T>> getAll() {
		return providers;
	}

	public void reset() {
		providers.clear();
	}

}
