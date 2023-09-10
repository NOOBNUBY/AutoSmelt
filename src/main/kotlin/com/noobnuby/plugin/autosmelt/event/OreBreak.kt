package com.noobnuby.plugin.autosmelt.event

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack


class OreBreak: Listener {
    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        val block = e.block.type
        val hand: ItemStack = e.player.inventory.itemInMainHand

        if(p.gameMode != GameMode.SURVIVAL) return

        when {
            hand.containsEnchantment(Enchantment.SILK_TOUCH) -> return
            block == Material.IRON_ORE || block == Material.DEEPSLATE_IRON_ORE || block == Material.GOLD_ORE || block == Material.DEEPSLATE_GOLD_ORE || block == Material.COPPER_ORE || block == Material.DEEPSLATE_COPPER_ORE -> {
                e.isDropItems = false

                for (i in e.block.getDrops(hand)) {
                    if (block.name.endsWith("IRON_ORE")) i.type = Material.IRON_INGOT
                    if (block.name.endsWith("GOLD_ORE")) i.type = Material.GOLD_INGOT
                    if (block.name.endsWith("COPPER_ORE")) i.type = Material.COPPER_INGOT

                    e.block.world.dropItemNaturally(e.block.location, i)
                }
            }
        }
    }
}