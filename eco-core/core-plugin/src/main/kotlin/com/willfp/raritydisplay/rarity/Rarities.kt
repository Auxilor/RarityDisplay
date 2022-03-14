package com.willfp.raritydisplay.rarity

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.willfp.eco.core.config.updating.ConfigUpdater
import com.willfp.eco.core.items.HashedItem
import com.willfp.eco.core.items.Items
import com.willfp.raritydisplay.RarityDisplayPlugin
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.concurrent.TimeUnit

object Rarities {
    private val REGISTERED = mutableMapOf<String, Rarity>()

    private val CACHE: Cache<HashedItem, Optional<Rarity>> = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.MINUTES)
        .maximumSize(5000)
        .build()

    init {
        update(RarityDisplayPlugin.instance)
    }

    fun getByName(name: String): Rarity? {
        return REGISTERED[name]
    }

    fun getForItem(item: ItemStack): Rarity {
        val hash = HashedItem.of(item)
        return CACHE.get(hash) {
            val itemStack = it.item
            for (rarity in REGISTERED.values) {
                if (rarity.matches(itemStack)) {
                    return@get Optional.of(rarity)
                }
            }

            return@get Optional.of(getByName(RarityDisplayPlugin.instance.configYml.getString("default-rarity"))!!)
        }.orElse(null)
    }

    @ConfigUpdater
    @JvmStatic
    fun update(plugin: RarityDisplayPlugin) {
        CACHE.invalidateAll()
        REGISTERED.clear()
        for (config in plugin.configYml.getSubsections("rarities")) {
            val rarity = Rarity(
                config.getString("id"),
                config.getStrings("items").map { Items.lookup(it) }.toMutableSet(),
                config.getFormattedString("display")
            )
            REGISTERED[config.getString("id")] = rarity
        }
    }

    fun values(): Set<Rarity> {
        return REGISTERED.values.toSet()
    }
}
