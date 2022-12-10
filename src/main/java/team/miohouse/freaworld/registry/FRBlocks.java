package team.miohouse.freaworld.registry;

import org.quiltmc.qsl.block.*;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;
import team.miohouse.freaworld.Freaworld;

public class FRBlocks {
	private static <T extends Block> T register(T block, String name) {
		FRItems.register(new BlockItem(block, FRItems.Properties.DEFAULT), name);
		return Registry.register(Registry.BLOCK, Freaworld.id(name), block);
	}
}
