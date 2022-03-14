package com.willfp.raritydisplay

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.command.impl.PluginCommand
import com.willfp.eco.core.display.DisplayModule
import com.willfp.raritydisplay.commands.CommandRarityDisplay
import com.willfp.raritydisplay.display.RarityDisplay
import org.bukkit.event.Listener

class RarityDisplayPlugin : EcoPlugin(623, 14623, "&#11998e", false) {
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

    companion object {
        /**
         * Instance of the plugin.
         */
        lateinit var instance: RarityDisplayPlugin
            private set
    }
}