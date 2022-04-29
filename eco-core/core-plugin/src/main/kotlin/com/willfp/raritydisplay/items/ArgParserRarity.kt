package com.willfp.raritydisplay.items

import com.willfp.eco.core.fast.FastItemStack
import com.willfp.eco.core.items.args.LookupArgParser
import com.willfp.eco.util.NamespacedKeyUtils
import com.willfp.raritydisplay.rarity.Rarities
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.function.Predicate

class ArgParserRarity: LookupArgParser {
    override fun parseArguments(args: Array<out String>?, meta: ItemMeta): Predicate<ItemStack>? {
        args?.forEach {
            val split = it.split(":")
            if (split.size < 2) return@forEach
            if (split[0].equals("rarity", true)) {
                val rarity = Rarities.getByName(split[1])?: return@forEach
                Rarities.setForItem(meta, rarity)
            }
        }
        return null
    }

    override fun serializeBack(meta: ItemMeta): String? {
        val rarity = if (meta.persistentDataContainer
                .has(NamespacedKeyUtils.create("raritydisplay", "rarity"), PersistentDataType.STRING)) {
            Rarities.getByName(
                meta.persistentDataContainer
                    .get(NamespacedKeyUtils.create("raritydisplay", "rarity"), PersistentDataType.STRING)
            )
        } else {
            null
        }
        return if (rarity != null) {
            "rarity:${rarity.name}"
        } else {
            null
        }
    }
}