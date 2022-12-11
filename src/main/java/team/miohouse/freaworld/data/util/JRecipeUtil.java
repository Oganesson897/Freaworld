package team.miohouse.freaworld.data.util;

import static team.miohouse.freaworld.ModEntry.TC;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class JRecipeUtil {
	public static JsonObject itemEntry(Identifier id, int count) {
		JsonObject json = new JsonObject();
		json.addProperty("item", id.toString());
		json.addProperty("count", count);
		return json;
	}

	public static JsonObject itemEntry(Identifier id) {
		JsonObject json = new JsonObject();
		json.addProperty("item", id.toString());
		return json;
	}

	public static JsonObject itemEntry(Identifier id, int count, double chance) {
		JsonObject json = itemEntry(id, count);
		json.addProperty("chance", chance);
		return json;
	}

	public static JsonObject fluidEntry(Identifier id, int amount) {
		JsonObject json = new JsonObject();
		json.addProperty("fluid", id.toString());
		json.addProperty("amount", amount);
		return json;
	}

	public static JsonObject fluidEntry(Identifier id, long amount) {
		return fluidEntry(id, (int) amount);
	}

	/*
	 * generate a melting recipe from TConstruct.
	 */
	public static Recipe<?> generateMelting(Identifier id, Identifier input, Identifier fluid, long amount,
			@Nullable Identifier byProduct, long byAmount) {
		JsonObject json = new JsonObject();
		json.addProperty("type", TC.asId("melting").toString());
		json.add("ingredient", itemEntry(input));
		json.add("result", fluidEntry(fluid, amount));
		json.addProperty("temperature", 500);
		json.addProperty("time", 20);
		if (byProduct != null) {
			JsonArray byproducts = new JsonArray();
			byproducts.add(fluidEntry(byProduct, byAmount));
			json.add("byproducts", byproducts);
		}
		return RecipeManager.deserialize(id, json);
	}
}
