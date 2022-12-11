package team.miohouse.freaworld.registry;

import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;
import team.miohouse.freaworld.Freaworld;

public class FRBlocks {
	public static final Block ANDESITE_MACHINE_CASING = register(
			new Block(QuiltBlockSettings.of(Material.METAL, MapColor.BROWN)), "andesite_machine_casing");

	private static <T extends Block> T register(T block, String name) {
		FRItems.register(new BlockItem(block, FRItems.Properties.DEFAULT), name);
		return Registry.register(Registry.BLOCK, Freaworld.id(name), block);
	}

	public static void load() {
	}
}
