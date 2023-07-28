package com.noobnuby.plugin.autosmelt.event

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.inventory.ItemStack

class OreBreak: Listener {
    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        val hand: ItemStack = e.player.inventory.itemInMainHand

        if(hand.containsEnchantment(Enchantment.SILK_TOUCH))
            return

        if(p.gameMode == GameMode.SURVIVAL) {
            val block = e.block.type
            when {
                block == Material.IRON_ORE || block == Material.DEEPSLATE_IRON_ORE -> {
                    e.isDropItems = false
                    e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.IRON_INGOT))

                }
                block == Material.GOLD_ORE || block == Material.DEEPSLATE_GOLD_ORE -> {
                    e.isDropItems = false
                    e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.GOLD_INGOT))
                }
                block == Material.COPPER_ORE || block == Material.DEEPSLATE_COPPER_ORE -> {
                    e.isDropItems = false
                    e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.COPPER_INGOT))
                }
            }
        }
    }
}