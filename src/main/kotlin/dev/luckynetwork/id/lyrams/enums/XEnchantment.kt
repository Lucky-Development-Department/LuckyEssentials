package dev.luckynetwork.id.lyrams.enums

import org.bukkit.enchantments.Enchantment

@Suppress("unused", "SpellCheckingInspection")
enum class XEnchantment(private val enchantment: Enchantment) {
    PROT(Enchantment.PROTECTION_ENVIRONMENTAL),
    PROTECTION(Enchantment.PROTECTION_ENVIRONMENTAL),
    FIREPROT(Enchantment.PROTECTION_FIRE),
    FIREPROTECTION(Enchantment.PROTECTION_FIRE),
    FIRE_PROTECTION(Enchantment.PROTECTION_FIRE),
    FEATHERFALLING(Enchantment.PROTECTION_FALL),
    FEATHER_FALLING(Enchantment.PROTECTION_FALL),
    FEATHERFALL(Enchantment.PROTECTION_FALL),
    FF(Enchantment.PROTECTION_FALL),
    BLASTPROT(Enchantment.PROTECTION_EXPLOSIONS),
    BLASTPROTECTION(Enchantment.PROTECTION_EXPLOSIONS),
    BLAST_PROTECTION(Enchantment.PROTECTION_EXPLOSIONS),
    PROJPROT(Enchantment.PROTECTION_PROJECTILE),
    PROJECTILEPROT(Enchantment.PROTECTION_PROJECTILE),
    PROJECTILEPROTECTION(Enchantment.PROTECTION_PROJECTILE),
    PROJECTILE_PROTECTION(Enchantment.PROTECTION_PROJECTILE),

    AQUAINFINITY(Enchantment.WATER_WORKER),
    AQUA_INFINITY(Enchantment.WATER_WORKER),
    AQUAAFFINITY(Enchantment.WATER_WORKER),
    AQUA_AFFINITY(Enchantment.WATER_WORKER),
    DEPTHSTRIDER(Enchantment.DEPTH_STRIDER),
    DEPTH_STRIDER(Enchantment.DEPTH_STRIDER),
    DS(Enchantment.DEPTH_STRIDER),

    SHARP(Enchantment.DAMAGE_ALL),
    SHARPNESS(Enchantment.DAMAGE_ALL),
    SMITE(Enchantment.DAMAGE_UNDEAD),
    BOA(Enchantment.DAMAGE_ARTHROPODS),
    BANE(Enchantment.DAMAGE_ARTHROPODS),
    BANEOFATHROPODS(Enchantment.DAMAGE_ARTHROPODS),
    BANE_OF_ATHROPODS(Enchantment.DAMAGE_ARTHROPODS),
    KB(Enchantment.KNOCKBACK),
    FA(Enchantment.FIRE_ASPECT),
    FIRE(Enchantment.FIRE_ASPECT),
    FIREASPECT(Enchantment.FIRE_ASPECT),
    FIRE_ASPECT(Enchantment.FIRE_ASPECT),
    LOOTING(Enchantment.LOOT_BONUS_MOBS),

    EFFICIENCY(Enchantment.DIG_SPEED),
    EFF(Enchantment.DIG_SPEED),
    SILKTOUCH(Enchantment.SILK_TOUCH),
    SILK_TOUCH(Enchantment.SILK_TOUCH),
    SILK(Enchantment.SILK_TOUCH),
    UNBREAKING(Enchantment.DURABILITY),
    UNBREAK(Enchantment.DURABILITY),
    FORTUNE(Enchantment.LOOT_BONUS_BLOCKS),

    POWER(Enchantment.ARROW_DAMAGE),
    POW(Enchantment.ARROW_DAMAGE),
    PUNCH(Enchantment.ARROW_KNOCKBACK),
    FLAME(Enchantment.ARROW_FIRE),
    INFINITY(Enchantment.ARROW_INFINITE),
    INF(Enchantment.ARROW_INFINITE),

    LOTS(Enchantment.LUCK),
    LUCKOFTHESEA(Enchantment.LUCK),
    LUCK_OF_THE_SEA(Enchantment.LUCK),
    LURE(Enchantment.LURE);

    companion object {
        /**
         * gets an enchantment by the 'human' name
         *
         * @param name = the 'human' item name
         */
        fun getByName(name: String): Enchantment {
            var enchantment: Enchantment? = null

            // tries to get it the normal way
            if (Enchantment.getByName(name.toUpperCase()) != null) {
                enchantment = Enchantment.getByName(name.toUpperCase())
            }

            // tries to get it from the map
            if (enchantment == null) {
                try {
                    enchantment = valueOf(name.toUpperCase()).enchantment
                } catch (ignored: Exception) {
                }
            }

            if (enchantment == null) {
                enchantment = getNewerEnchantments(name)
            }

            return enchantment ?: Enchantment.LUCK
        }

        /**
         * tries to get newer enchantments...
         */
        private fun getNewerEnchantments(name: String): Enchantment? {
            var enchantment: Enchantment? = null

            try {
                enchantment = Enchantment.getByName(name)
            } catch (_: Exception) {
            }

            if (enchantment != null)
                return enchantment

            when (name.toUpperCase()) {
                "FROSTWALKER",
                "FROST" -> return Enchantment.FROST_WALKER

                "MENDING" -> return Enchantment.MENDING

                "BINDINGCURSE",
                "BINDING",
                "BIND" -> return Enchantment.BINDING_CURSE

                "VANISHINGCURSE",
                "VANISHING",
                "VANISH" -> return Enchantment.VANISHING_CURSE

                "SWEEPINGEDGE",
                "SWEEPEDGE",
                "SWEEP" -> return Enchantment.SWEEPING_EDGE

                else -> return null
            }
        }

    }
}