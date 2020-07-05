package dev.luckynetwork.id.lyrams.objects

import org.bukkit.enchantments.Enchantment

object IEnchantment {

    fun getEnchantment(name: String): Enchantment {

        if (Enchantment.getByName(name.toUpperCase()) != null)
            return Enchantment.getByName(name.toUpperCase())

        when {
            name.equals("power", true) -> return Enchantment.ARROW_DAMAGE
            name.equals("flame", true) -> return Enchantment.ARROW_FIRE
            name.equals("infinity", true) -> return Enchantment.ARROW_INFINITE
            name.equals("punch", true) -> return Enchantment.ARROW_KNOCKBACK
            name.equals("sharpness", true) -> return Enchantment.DAMAGE_ALL
            name.equals("efficiency", true) -> return Enchantment.DIG_SPEED
            name.equals("unbreaking", true) -> return Enchantment.DURABILITY
            name.equals("fireaspect", true) -> return Enchantment.FIRE_ASPECT
            name.equals("fortune", true) -> return Enchantment.LOOT_BONUS_BLOCKS
            name.equals("looting", true) -> return Enchantment.LOOT_BONUS_MOBS
            name.equals("protection", true) || name.equals("prot", true) -> return Enchantment.PROTECTION_ENVIRONMENTAL
            name.equals("blastprotection", true) || name.equals("blastprot", true) -> return Enchantment.PROTECTION_EXPLOSIONS
            name.equals("featherfalling", true) || name.equals("featherfall", true) -> return Enchantment.PROTECTION_FALL
            name.equals("fireprotection", true) || name.equals("fireprot", true) -> return Enchantment.PROTECTION_FIRE
            name.equals("projectileprotection", true) || name.equals("projectileprot", true) -> return Enchantment.PROTECTION_PROJECTILE
            name.equals("silktouch", true) -> return Enchantment.SILK_TOUCH
            else -> return Enchantment.LUCK

        }

    }

}