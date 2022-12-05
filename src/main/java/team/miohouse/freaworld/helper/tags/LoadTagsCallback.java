package team.miohouse.freaworld.helper.tags;

import org.quiltmc.qsl.base.api.event.Event;
import org.quiltmc.qsl.base.api.event.EventAwareListener;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;

import java.util.Map;

@FunctionalInterface
public interface LoadTagsCallback<T> extends EventAwareListener {
	void load(TagHandler<T> handler);

	Event<LoadTagsCallback<Item>> ITEMS = Event.create(LoadTagsCallback.class, callbacks -> handler -> {
		for (LoadTagsCallback<Item> callback : callbacks)
			callback.load(handler);
	});

	Event<LoadTagsCallback<Block>> BLOCKS = Event.create(LoadTagsCallback.class, callbacks -> handler -> {
		for (LoadTagsCallback<Block> callback : callbacks)
			callback.load(handler);
	});

	Event<LoadTagsCallback<Fluid>> FLUIDS = Event.create(LoadTagsCallback.class, callbacks -> handler -> {
		for (LoadTagsCallback<Fluid> callback : callbacks)
			callback.load(handler);
	});

	interface TagHandler<T> {
		Map<Identifier, Tag<Holder<T>>> get();

		void register(Identifier tag, T... values);

		void remove(Identifier tag, T... values);
	}
}
