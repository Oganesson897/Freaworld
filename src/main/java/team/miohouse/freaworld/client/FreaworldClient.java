package team.miohouse.freaworld.client;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class FreaworldClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(ModContainer mod) {
        ColorProviderRegistry.ITEM.register((stack, i) -> ((ItemColorProvider) stack.getItem()).getColor(stack, i),
                Registry.ITEM.stream().filter(item -> item instanceof ItemColorProvider).toArray(Item[]::new));
    }

}
