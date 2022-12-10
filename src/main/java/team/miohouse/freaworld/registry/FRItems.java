package team.miohouse.freaworld.registry;

import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import team.miohouse.freaworld.Freaworld;
import team.miohouse.freaworld.content.item.SimpleColoredItem;

public class FRItems {
    public static final Item FREAWORLD = register(new Item(new QuiltItemSettings().maxCount(1).rarity(Rarity.RARE)),
            "freaworld");

    public static final Item CRUSHED_ANTIMONY_ORE = register(
            new SimpleColoredItem(Properties.DEFAULT, 0xb8b8c9), "crushed_antimony_ore");
    public static final Item CRUSHED_BAUXITE_ORE = register(
            new SimpleColoredItem(Properties.DEFAULT, 0xEEB4B4), "crushed_bauxite_ore");
    public static final Item CRUSHED_IRIDIUM_ORE = register(
            new SimpleColoredItem(Properties.DEFAULT, 0xF0FFFF), "crushed_iridium_ore");
    public static final Item CRUSHED_TITANIUM_ORE = register(
            new SimpleColoredItem(Properties.DEFAULT, 0xFF83FA), "crushed_titanium_ore");
    public static final Item CRUSHED_TUNGSTEN_ORE = register(
            new SimpleColoredItem(Properties.DEFAULT, 0x551A8B), "crushed_tungsten_ore");

    public static <T extends Item> T register(T item, String name) {
        return Registry.register(Registry.ITEM, Freaworld.id(name), item);
    }

    public static class Properties {
        public static final Item.Settings DEFAULT = new QuiltItemSettings().group(Freaworld.MAIN_GROUP);
    }
}
