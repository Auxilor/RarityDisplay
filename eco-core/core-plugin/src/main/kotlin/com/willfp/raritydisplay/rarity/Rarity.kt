package com.willfp.raritydisplay.rarity

import com.willfp.eco.core.items.TestableItem
import com.willfp.eco.core.recipe.parts.EmptyTestableItem
import org.bukkit.inventory.ItemStack

class Rarity(
    val name: String,
    val items: MutableSet<TestableItem>,
    val display: String
) {
    init {
        items.removeIf { it is EmptyTestableItem }
    }

    fun matches(itemStack: ItemStack): Boolean {
        for (item in items) {
            if (item.matches(itemStack)) {
                return true
            }
        }
        return false
    }
}
