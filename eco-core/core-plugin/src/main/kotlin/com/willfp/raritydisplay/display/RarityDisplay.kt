@file:Suppress("UsePropertyAccessSyntax")

package com.willfp.raritydisplay.display

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.display.Display
import com.willfp.eco.core.display.DisplayModule
import com.willfp.eco.core.display.DisplayPriority
import com.willfp.eco.core.fast.FastItemStack
import com.willfp.raritydisplay.rarity.Rarities
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class RarityDisplay(plugin: EcoPlugin) : DisplayModule(plugin, DisplayPriority.HIGHEST) {
    override fun display(
        itemStack: ItemStack,
        player: Player?,
        vararg args: Any
    ) {
        if (player != null) {
            val inventory = player.openInventory.topInventory
            if (inventory.contents.contains(itemStack) && inventory.holder == null) {
                return
            }
        }

        val rarity = Rarities.getForItem(itemStack)
        val fis = FastItemStack.wrap(itemStack)
        val lore = fis.lore.toMutableList()
        if (plugin.configYml.getBool("insert-blank-line")) {
            lore.add(Display.PREFIX)
        }
        lore.add("${Display.PREFIX}${rarity.display}")
        fis.lore = lore
    }
}
