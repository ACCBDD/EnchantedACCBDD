package favouriteless.enchanted.common.init.registry;

import favouriteless.enchanted.common.altar.PowerProvider;

import java.util.ArrayList;
import java.util.List;

public class PowerProviderRegistry<T> {

	private final List<PowerProvider<T>> providers = new ArrayList<>();

	public void register(PowerProvider<T> provider) {
		if(!providers.isEmpty()) {
			for(int i = 0; i < providers.size(); i++) {
				PowerProvider<T> oldProvider = providers.get(i);

				if(provider.power() > oldProvider.power()) { // Prioritise the highest power per block first.
					providers.add(i, provider);
					break;
				}
				else if(provider.power() == oldProvider.power()) { // Then prioritise whatever has the highest limit
					if(provider.limit() > oldProvider.limit()) {
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

	public PowerProvider<T> get(T key) {
		for(PowerProvider<T> provider : providers) {
			if(provider.is(key))
				return provider;
		}
		return null;
	}

	public List<PowerProvider<T>> getAll() {
		return providers;
	}

	public void reset() {
		providers.clear();
	}

}
