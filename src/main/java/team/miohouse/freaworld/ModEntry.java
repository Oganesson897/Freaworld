package team.miohouse.freaworld;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModEntry {
    MI("modern_industrialization");

    protected final ModContainer mod;
    protected final String id;

    ModEntry(String modId) {
        Optional<ModContainer> optional = QuiltLoader.getModContainer(modId);
        if (!optional.isPresent())
            throw new RuntimeErrorException(new Error("ModContainer " + modId + " dosen't exist!"));
        this.mod = optional.get();
        this.id = modId;
    }

    public String getModId() {
        return this.id;
    }

    public Identifier asId(String path) {
        return new Identifier(getModId(), path);
    }

    public Item asItem(String name) {
        return Registry.ITEM.get(asId(name));
    }
}
