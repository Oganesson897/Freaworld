package team.miohouse.freaworld.content.item;

import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SimpleColoredItem extends Item implements ItemColorProvider {

    protected final int tint;

    public SimpleColoredItem(Settings settings, int tint) {
        super(settings);
        this.tint = tint;
    }

    @Override
    public int getColor(ItemStack itemStack, int i) {
        return tint;
    }
    
}
