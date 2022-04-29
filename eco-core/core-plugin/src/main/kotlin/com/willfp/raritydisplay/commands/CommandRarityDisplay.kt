package com.willfp.raritydisplay.commands

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.command.impl.PluginCommand
import org.bukkit.command.CommandSender

class CommandRarityDisplay(plugin: EcoPlugin) : PluginCommand(
    plugin,
    "raritydisplay",
    "raritydisplay.command.raritydisplay",
    false
) {
    init {
        this.addSubcommand(CommandReload(plugin))
            .addSubcommand(CommandSetRarity(plugin))
    }

    override fun onExecute(sender: CommandSender, args: List<String>) {
        sender.sendMessage(plugin.langYml.getMessage("invalid-command"))
    }
}
