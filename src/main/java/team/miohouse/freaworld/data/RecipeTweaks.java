package team.miohouse.freaworld.data;

import static team.miohouse.freaworld.ModEntry.CR;
import static team.miohouse.freaworld.ModEntry.MC;
import static team.miohouse.freaworld.ModEntry.MI;

import java.util.List;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import team.miohouse.freaworld.Freaworld;

public class RecipeTweaks implements RemoveRecipesCallback, AddRecipesCallback {

    private static final List<? extends Item> REMOVED_ITEMS = List.of(MI.asItem("forge_hammer"),
            CR.asItem("mechanical_press"), CR.asItem("encased_fan"), CR.asItem("millstone"),
            CR.asItem("mechanical_mixer"), CR.asItem("deployer"), CR.asItem("basin"), CR.asItem("mechanical_saw"),
            MC.asItem("smithing_table"));

    @Override
    public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
        handler.removeIf(recipe -> REMOVED_ITEMS.contains(recipe.getOutput().getItem()) && notFr(recipe));
    }

    @Override
    public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
        /*
         * example:
         * handler.register(recipeId("crafting", "smithing_table"),
         * id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AOA", "AAA")
         * .ingredient('A', CR.asItem("iron_sheet"))
         * .ingredient('O', Items.CRAFTING_TABLE)
         * .output(Items.SMITHING_TABLE.getDefaultStack())
         * .build(id, ""));
         */
        handler.register(recipeId("crafting", "smithing_table"),
                id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AOA", "AAA")
                        .ingredient('A', CR.asItem("iron_sheet"))
                        .ingredient('O', Items.CRAFTING_TABLE)
                        .output(Items.SMITHING_TABLE.getDefaultStack())
                        .build(id, ""));
    }

    private static boolean notFr(Recipe<?> recipe) {
        return !recipe.getId().getNamespace().equals(Freaworld.ID);
    }

    private static Identifier recipeId(String type, String name) {
        return Freaworld.id(type + "/" + name);
    }

}
