package team.miohouse.freaworld;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import com.dm.earth.tags_binder.api.LoadTagsCallback;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import team.miohouse.freaworld.data.ItemTagTweaks;

public class Freaworld implements ModInitializer {

    public static final String ID = "freaworld";

    @Override
    public void onInitialize(ModContainer mod) {
        ResourceLoader.registerBuiltinResourcePack(id("data_overrides"), ResourcePackActivationType.ALWAYS_ENABLED,
                Text.of("Freaworld: Data Overrides"));
	    LoadTagsCallback.ITEM.register(new ItemTagTweaks());
    }

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

}
