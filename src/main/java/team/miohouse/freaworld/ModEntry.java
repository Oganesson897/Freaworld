package team.miohouse.freaworld;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModEntry {
	MC("minecraft"), MI("modern_industrialization"), BE("betterend"), C("c"), BALM("balm"), CR("create");

	private final ModContainer mod;
	private final String id;

	ModEntry(String modId) {
		this.mod = QuiltLoader.getModContainer(modId).orElse(null);
		this.id = modId;
	}

	@Nullable
	public ModContainer getContainer() {
		return this.mod;
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
