package team.miohouse.freaworld.helper.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.jetbrains.annotations.ApiStatus.Internal;

import net.minecraft.tag.Tag;
import net.minecraft.tag.TagManagerLoader;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;

@Internal
public class TagsHandlerImpl<T> implements LoadTagsCallback.TagHandler<T> {
	protected HashMap<Identifier, Tag<Holder<T>>> map;

	public TagsHandlerImpl(TagManagerLoader.LoadResult<T> value) {
		this.map = new HashMap<>(value.tags());
	}

	@Override
	public Map<Identifier, Tag<Holder<T>>> get() {
		return map;
	}

	@SafeVarargs
	@Override
	public final void register(Identifier tag, T... values) {
		if (map.containsKey(tag)) {
			ArrayList<Holder<T>> tagsList = new ArrayList<>(map.get(tag).values());
			for (T value : values)
				if (tagsList.stream().noneMatch(h -> h.value() == value))
					tagsList.add(HolderAccess.getHolder(value));
			map.replace(tag, new Tag<>(tagsList));
		} else {
			ArrayList<Holder<T>> tagsList = new ArrayList<>();
			for (T value : values)
				tagsList.add(HolderAccess.getHolder(value));
			map.put(tag, new Tag<>(tagsList));
		}
	}

	@SafeVarargs
	@Override
	public final void remove(Identifier tag, T... values) {
		if (map.containsKey(tag))
			if (values.length > 0) {
				ArrayList<Holder<T>> tagsList = new ArrayList<>(map.get(tag).values());
				for (T value : values)
					tagsList.removeIf(h -> h.value() == value);
				map.replace(tag, new Tag<>(tagsList));
			} else
				map.remove(tag);
	}
}
