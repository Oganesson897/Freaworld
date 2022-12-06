package team.miohouse.freaworld.data;

import static team.miohouse.freaworld.ModEntry.BE;
import static team.miohouse.freaworld.ModEntry.C;

import net.minecraft.item.Item;
import team.miohouse.freaworld.helper.tags.LoadTagsCallback;

public class TagTweaks implements LoadTagsCallback<Item> {

    @Override
    public void load(TagHandler<Item> handler) {
        handler.remove(C.asId("iron_ingots"), BE.asItem("thallasium_ingot"));
    }

}