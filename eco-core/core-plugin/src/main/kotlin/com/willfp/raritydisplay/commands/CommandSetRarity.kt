package com.willfp.raritydisplay.commands

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.command.impl.Subcommand
import com.willfp.raritydisplay.rarity.Rarities
import org.apache.commons.lang.StringUtils
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil

class CommandSetRarity(plugin: EcoPlugin) : Subcommand(
    plugin,
    "setrarity",
    "raritydisplay.command.setrarity",
    true
) {
    override fun onExecute(sender: CommandSender, args: List<String>) {
        if (args.isEmpty()) {
            sender.sendMessage(this.plugin.langYml.getMessage("rarity-required"))
            return
        }
        val rarity = Rarities.getByName(args[0])
        if (rarity == null) {
            sender.sendMessage(this.plugin.langYml.getMessage("rarity-invalid"))
            return
        }
        val item = (sender as Player).inventory.itemInMainHand
        if (item == null || item.type == Material.AIR) {
            sender.sendMessage(this.plugin.langYml.getMessage("item-required"))
            return
        }
        Rarities.setForItem(item, rarity)
        sender.sendMessage(this.plugin.langYml.getMessage("rarity-set")
            .replace("rarity", rarity.display))
    }

    override fun tabComplete(sender: CommandSender, args: MutableList<String>): MutableList<String> {
        val completions = mutableListOf<String>()
        if (args.size == 1) {
            completions.addAll(StringUtil.copyPartialMatches(args[0],
                Rarities.values().map { it.display }, completions))
        }
        return completions
    }
}