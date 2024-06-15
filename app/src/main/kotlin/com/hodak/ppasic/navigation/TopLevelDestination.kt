/*
 * Copyright 2024 hodak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hodak.ppasic.navigation

import com.hodak.ppasic.R
import com.hodak.ppasic.core.designsystem.R as designSystemR
import com.hodak.ppasic.feature.cart.R as cartR
import com.hodak.ppasic.feature.cook.R as cookR
import com.hodak.ppasic.feature.fridge.R as fridgeR
import com.hodak.ppasic.feature.recipe.R as recipeR

enum class TopLevelDestination(
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    COOK(
        selectedIconId = designSystemR.drawable.core_designsystem_ic_chef_hat,
        unselectedIconId = designSystemR.drawable.core_designsystem_ic_chef_hat,
        iconTextId = cookR.string.feature_cook_title,
        titleTextId = R.string.app_name,
    ),
    FRIDGE(
        selectedIconId = designSystemR.drawable.core_designsystem_ic_fridge,
        unselectedIconId = designSystemR.drawable.core_designsystem_ic_fridge_outline,
        iconTextId = fridgeR.string.feature_fridge_title,
        titleTextId = fridgeR.string.feature_fridge_title,
    ),
    RECIPE(
        selectedIconId = designSystemR.drawable.core_designsystem_ic_recipe,
        unselectedIconId = designSystemR.drawable.core_designsystem_ic_recipe_outline,
        iconTextId = recipeR.string.feature_recipe_title,
        titleTextId = recipeR.string.feature_recipe_title,
    ),
    CART(
        selectedIconId = designSystemR.drawable.core_designsystem_ic_cart,
        unselectedIconId = designSystemR.drawable.core_designsystem_ic_cart_outline,
        iconTextId = cartR.string.feature_cart_title,
        titleTextId = cartR.string.feature_cart_title,
    ),
}
