package team.miohouse.freaworld.mixin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.quiltmc.qsl.tag.api.QuiltTagKey;
import org.quiltmc.qsl.tag.api.TagType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.server.ServerReloadableResources;
import net.minecraft.tag.TagKey;
import net.minecraft.tag.TagManagerLoader;
import net.minecraft.util.Holder;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import team.miohouse.freaworld.helper.tags.LoadTagsCallback;
import team.miohouse.freaworld.helper.tags.TagsHandlerImpl;

@Mixin(ServerReloadableResources.class)
public class ServerReloadableResourcesMixin {
	@Inject(method = "updateRegistryTags(Lnet/minecraft/util/registry/DynamicRegistryManager;Lnet/minecraft/tag/TagManagerLoader$LoadResult;)V", at = @At("HEAD"), cancellable = true)
	private static <T> void updateRegistryTags(DynamicRegistryManager dynamicRegistryManager, TagManagerLoader.LoadResult<T> loadResult, CallbackInfo ci) {
		RegistryKey<? extends Registry<T>> registryKey = loadResult.key();
		Map<TagKey<T>, List<Holder<T>>> map = call(loadResult).tags().entrySet().stream().collect(Collectors.toMap(entry -> QuiltTagKey.of(registryKey, entry.getKey(), TagType.NORMAL), entry -> entry.getValue().values()));
		dynamicRegistryManager.get(registryKey).bindTags(map);
		ci.cancel();
	}

	@SuppressWarnings("unchecked")
	private static <T> TagManagerLoader.LoadResult<T> call(TagManagerLoader.LoadResult<T> value) {
		TagsHandlerImpl<T> impl = new TagsHandlerImpl<>(value);
		if (value.key().equals(Registry.ITEM_KEY))
			LoadTagsCallback.ITEMS.invoker().load((LoadTagsCallback.TagHandler<Item>) impl);
		if (value.key().equals(Registry.BLOCK_KEY))
			LoadTagsCallback.BLOCKS.invoker().load((LoadTagsCallback.TagHandler<Block>) impl);
		if (value.key().equals(Registry.FLUID_KEY))
			LoadTagsCallback.FLUIDS.invoker().load((LoadTagsCallback.TagHandler<Fluid>) impl);
		return new TagManagerLoader.LoadResult<>(value.key(), impl.get());
	}
}
