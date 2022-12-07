package team.miohouse.freaworld.data;

import static team.miohouse.freaworld.ModEntry.BALM;
import static team.miohouse.freaworld.ModEntry.BE;
import static team.miohouse.freaworld.ModEntry.C;

import com.dm.earth.tags_binder.api.LoadTagsCallback;

import net.minecraft.item.Item;

public class ItemTagTweaks implements LoadTagsCallback<Item> {

	@Override
	public void load(TagHandler<Item> handler) {
		handler.remove(C.asId("iron_ingots"), BE.asItem("thallasium_ingot"));
		handler.remove(BALM.asId("iron_ingots"), BE.asItem("thallasium_ingot"));
	}

}
