package com.noobnuby.plugin.autosmelt.event

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.inventory.ItemStack

class BlockBreak: Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        when {
            block.type == Material.IRON_ORE -> {
                event.isDropItems = false
                event.block.world.dropItemNaturally(event.block.location, ItemStack(Material.IRON_INGOT))
            }
        }
    }
}