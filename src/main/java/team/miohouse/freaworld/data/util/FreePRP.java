package team.miohouse.freaworld.data.util;

import java.util.List;

import com.simibubi.create.content.contraptions.processing.HeatCondition;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.fluid.FluidIngredient;

import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class FreePRP extends ProcessingRecipeBuilder.ProcessingRecipeParams {
    /*
     * FreePRP is a free version of ProcessingRecipeParams.
     */
    protected FreePRP(Identifier id) {
        super(id);
    }

	public static FreePRP of(Identifier id) {
		return new FreePRP(id);
	}

    public FreePRP setIngredient(List<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
        return this;
    }

    public FreePRP setIngredient(Ingredient... ingredients) {
        return this.setIngredient(List.of(ingredients));
    }

    public FreePRP setResult(List<ProcessingOutput> results) {
        this.results.addAll(results);
        return this;
    }

    public FreePRP setResult(ProcessingOutput... results) {
        return this.setResult(List.of(results));
    }

    public FreePRP setFluidIngredient(List<FluidIngredient> ingredients) {
        this.fluidIngredients.addAll(ingredients);
        return this;
    }

    public FreePRP setFluidIngredient(FluidIngredient... ingredients) {
        return this.setFluidIngredient(List.of(ingredients));
    }

    public FreePRP setFluidResult(List<FluidStack> results) {
        this.fluidResults.addAll(results);
        return this;
    }

    public FreePRP setFluidResult(FluidStack... results) {
        return this.setFluidResult(List.of(results));
    }

    public FreePRP setProcessingTime(int processingTime) {
        this.processingDuration = processingTime;
        return this;
    }

    public FreePRP setHeatRequirement(HeatCondition condition) {
        this.requiredHeat = condition;
        return this;
    }

    public FreePRP keepHeldItem(Boolean bool) {
        this.keepHeldItem = bool;
        return this;
    }

    public FreePRP keepHeldItem() {
        return this.keepHeldItem(true);
    }
}
