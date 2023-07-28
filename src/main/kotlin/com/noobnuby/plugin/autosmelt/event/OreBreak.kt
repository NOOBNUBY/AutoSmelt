package com.noobnuby.plugin.autosmelt.event

import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class OreBreak: Listener {
    private val rand = Random()
    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        val block = e.block.type
        val hand: ItemStack = e.player.inventory.itemInMainHand
        var amount: Int = 1

        if(p.gameMode == GameMode.SURVIVAL) {
            when {
                hand.containsEnchantment(Enchantment.SILK_TOUCH) -> return
                block == Material.IRON_ORE || block == Material.DEEPSLATE_IRON_ORE || block == Material.GOLD_ORE || block == Material.DEEPSLATE_GOLD_ORE || block == Material.COPPER_ORE || block == Material.DEEPSLATE_COPPER_ORE -> {
                    if(hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS))
                        amount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1
                    e.isDropItems = false
                    if(block == Material.IRON_ORE || block == Material.DEEPSLATE_IRON_ORE)
                        e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.IRON_INGOT, amount))
                    else if(block == Material.GOLD_ORE || block == Material.DEEPSLATE_GOLD_ORE)
                        e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.GOLD_INGOT, amount))
                    else {
                        if(hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                            amount = rand.nextInt(6, (hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) * 5)
                            e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.COPPER_INGOT, amount))
                            return
                        }
                        amount = rand.nextInt(4) + 1
                        e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.COPPER_INGOT, amount))
                    }
                }
            }
        }
    }
}