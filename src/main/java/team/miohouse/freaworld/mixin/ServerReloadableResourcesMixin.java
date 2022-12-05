package team.miohouse.freaworld.mixin;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.server.ServerReloadableResources;
import net.minecraft.tag.TagManagerLoader;
import net.minecraft.util.registry.Registry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import team.miohouse.freaworld.helper.tags.LoadTagsCallback;
import team.miohouse.freaworld.helper.tags.TagsHandlerImpl;

@Mixin(ServerReloadableResources.class)
public class ServerReloadableResourcesMixin {
	@ModifyVariable(method = "updateRegistryTags(Lnet/minecraft/util/registry/DynamicRegistryManager;Lnet/minecraft/tag/TagManagerLoader$LoadResult;)V", at = @At("HEAD"), name = "loadResult")
	@SuppressWarnings("unchecked")
	private static <T> TagManagerLoader.LoadResult<T> updateRegistryTags(TagManagerLoader.LoadResult<T> value) {
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
