package com.willfp.raritydisplay

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.command.impl.PluginCommand
import com.willfp.eco.core.config.base.ConfigYml
import com.willfp.eco.core.display.DisplayModule
import com.willfp.raritydisplay.commands.CommandRarityDisplay
import com.willfp.raritydisplay.display.RarityDisplay
import org.bukkit.event.Listener

class RarityDisplayPlugin : EcoPlugin(0, 14623, "&#11998e", false) {
    private val configYml = RemovelessConfigYml(this)

    init {
        instance = this
    }

    override fun loadPluginCommands(): List<PluginCommand> {
        return listOf(
            CommandRarityDisplay(this)
        )
    }

    override fun loadListeners(): List<Listener> {
        return listOf(

        )
    }

    override fun createDisplayModule(): DisplayModule {
        return RarityDisplay(this)
    }

    override fun getConfigYml(): ConfigYml {
        return configYml
    }

    companion object {
        /**
         * Instance of the plugin.
         */
        lateinit var instance: RarityDisplayPlugin
            private set
    }
}

class RemovelessConfigYml(plugin: RarityDisplayPlugin) : ConfigYml(plugin, false)