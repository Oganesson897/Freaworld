package team.miohouse.freaworld;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import com.dm.earth.tags_binder.api.LoadTagsCallback;

import net.minecraft.item.ItemGroup;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import team.miohouse.freaworld.data.ItemTagTweaks;
import team.miohouse.freaworld.registry.FRItems;

public class Freaworld implements ModInitializer {

    public static final String ID = "freaworld";
    public static final ItemGroup MAIN_GROUP = QuiltItemGroup.createWithIcon(id("main"),
            () -> FRItems.CRUSHED_ANTIMONY.getDefaultStack());

    @Override
    public void onInitialize(ModContainer mod) {
        ResourceLoader.registerBuiltinResourcePack(id("data_overrides"), ResourcePackActivationType.ALWAYS_ENABLED,
                new TranslatableText("pack." + ID + ".data_overrides"));
        LoadTagsCallback.ITEM.register(new ItemTagTweaks());
    }

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

}
