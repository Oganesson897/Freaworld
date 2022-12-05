package team.miohouse.freaworld.helper.tags;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.Holder;

import org.jetbrains.annotations.Nullable;

public class HolderAccess {
	@Nullable
	@SuppressWarnings({"unchecked", "deprecation"})
	public static <O> Holder<O> getHolder(O object) {
		if (object instanceof Item i) return (Holder<O>) i.getBuiltInRegistryHolder();
		if (object instanceof Block i) return (Holder<O>) i.getBuiltInRegistryHolder();
		if (object instanceof Fluid i) return (Holder<O>) i.getBuiltInRegistryHolder();
		return null;
	}
}
