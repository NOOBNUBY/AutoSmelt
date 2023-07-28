package com.noobnuby.plugin.autosmelt.event

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class OreBreak: Listener {
    val rand = Random()
    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        val block = e.block.type
        val hand: ItemStack = e.player.inventory.itemInMainHand
        var Amount: Int = 1

        //TODO : 광물 드롭 안되는거 수정
        if(p.gameMode == GameMode.SURVIVAL) {
            when {
                hand.containsEnchantment(Enchantment.SILK_TOUCH) -> return
                block != Material.COPPER_ORE || block != Material.DEEPSLATE_COPPER_ORE && hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) -> Amount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1
                block == Material.IRON_ORE || block == Material.DEEPSLATE_IRON_ORE -> {
                    e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.IRON_INGOT, Amount))
                }
                block == Material.GOLD_ORE || block == Material.DEEPSLATE_GOLD_ORE -> {
                    e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.GOLD_INGOT, Amount))
                }
                block == Material.COPPER_ORE || block == Material.DEEPSLATE_COPPER_ORE -> {
                    if(hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                        Amount = rand.nextInt(6,hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1 * 5) + 1
                        e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.COPPER_INGOT, Amount))
                        return
                    }
                    Amount = rand.nextInt(4) + 1
                    e.block.world.dropItemNaturally(e.block.location, ItemStack(Material.COPPER_INGOT, Amount))
                }
            }
        }
    }
}