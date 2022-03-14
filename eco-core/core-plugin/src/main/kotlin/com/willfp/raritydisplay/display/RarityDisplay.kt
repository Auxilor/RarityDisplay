@file:Suppress("UsePropertyAccessSyntax")

package com.willfp.raritydisplay.display

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.display.Display
import com.willfp.eco.core.display.DisplayModule
import com.willfp.eco.core.display.DisplayPriority
import com.willfp.eco.core.fast.FastItemStack
import com.willfp.raritydisplay.rarity.Rarities
import org.bukkit.inventory.ItemStack

class RarityDisplay(plugin: EcoPlugin) : DisplayModule(plugin, DisplayPriority.HIGHEST) {
    override fun display(
        itemStack: ItemStack,
        vararg args: Any
    ) {
        val rarity = Rarities.getForItem(itemStack)
        val fis = FastItemStack.wrap(itemStack)
        val lore = fis.lore.toMutableList()
        if (plugin.configYml.getBool("insert-blank-line")) {
            lore.add(Display.PREFIX)
        }
        lore.add(rarity.display)
        fis.lore = lore
    }
}
