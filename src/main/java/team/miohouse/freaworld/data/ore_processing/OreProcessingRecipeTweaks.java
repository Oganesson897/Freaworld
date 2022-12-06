package team.miohouse.freaworld.data.ore_processing;

import static team.miohouse.freaworld.data.util.JRecipeUtil.fluidEntry;
import static team.miohouse.freaworld.data.util.JRecipeUtil.itemEntry;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.util.Identifier;

public class OreProcessingRecipeTweaks implements AddRecipesCallback, RemoveRecipesCallback {
    private static JsonObject generateMelting(Identifier input, Identifier fluid, long amount, Identifier byProduct,
            long byAmount) {
        JsonObject json = new JsonObject();
        json.addProperty("type", (new Identifier("tconstruct", "melting")).toString());
        json.add("ingredient", itemEntry(input));
        json.add("result", fluidEntry(fluid, amount));
        json.addProperty("temperature", 500);
        json.addProperty("time", 20);
        if (byProduct != null) {
            JsonArray byproducts = new JsonArray();
            byproducts.add(fluidEntry(byProduct, byAmount));
            json.add("byproducts", byproducts);
        }
        return json;
    }

    @Override
    public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {

    }

    @Override
    public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
      
    }
}
