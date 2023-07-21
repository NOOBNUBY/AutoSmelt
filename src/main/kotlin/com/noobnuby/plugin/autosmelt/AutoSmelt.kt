package com.noobnuby.plugin.autosmelt
import com.noobnuby.plugin.autosmelt.event.OreBreak
import org.bukkit.plugin.java.JavaPlugin

class AutoSmelt: JavaPlugin() {
    override fun onEnable() {
        logger.info("Enable Plugin")
        server.pluginManager.registerEvents(OreBreak(), this)
    }
}