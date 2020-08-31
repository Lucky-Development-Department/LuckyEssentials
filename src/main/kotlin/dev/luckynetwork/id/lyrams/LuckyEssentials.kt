package dev.luckynetwork.id.lyrams

import dev.luckynetwork.id.lyrams.commands.LuckyEssentialsCMD
import dev.luckynetwork.id.lyrams.commands.features.essentials.*
import dev.luckynetwork.id.lyrams.commands.features.trolls.*
import dev.luckynetwork.id.lyrams.listeners.PlayerListeners
import dev.luckynetwork.id.lyrams.listeners.trolls.TrollPlayerListeners
import dev.luckynetwork.id.lyrams.objects.Config
import dev.luckynetwork.id.lyrams.objects.Slots
import dev.luckynetwork.id.lyrams.objects.Whitelist
import dev.luckynetwork.id.lyrams.utils.PluginsTabCompleter
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class LuckyEssentials : JavaPlugin() {

    companion object {

        lateinit var instance: LuckyEssentials
        var isChatLocked: Boolean = false

    }


    override fun onEnable() {

        if (Bukkit.getPluginManager().getPlugin("LuckyInjector") == null && Bukkit.getPluginManager().getPlugin("KtLoader") == null)
            Bukkit.getLogger().warning("LuckyInjector or KtLoader not found! Plugin might not load!")

        instance = this

        Config.init(this)

        registerCommands()
        registerListeners()

        Bukkit.getScheduler().runTaskLater(this, {
            Whitelist.reload()
            Slots.convert()
            Slots.reload()
        }, 1L)

    }

    override fun onDisable() {

        Bukkit.getScheduler().cancelTasks(this)

    }

    private fun registerCommands() {

        getCommand("luckyessentials").executor = LuckyEssentialsCMD()
        getCommand("luckyessentials").tabCompleter = PluginsTabCompleter()

        getCommand("chatlock").executor = ChatLockCMD()
        getCommand("clear").executor = ClearCMD()
        getCommand("editsign").executor = EditSignCMD()
        getCommand("enchant").executor = EnchantCMD()
        getCommand("ewhitelist").executor = EWhitelistCMD()
        getCommand("explode").executor = ExplodeCMD()
        getCommand("fakeplace").executor = FakePlaceCMD()
        getCommand("feed").executor = FeedCMD()
        getCommand("fix").executor = FixCMD()
        getCommand("fly").executor = FlyCMD()
        getCommand("gamemode").executor = GamemodeCMD()
        getCommand("getpos").executor = GetPosCMD()
        getCommand("give").executor = GiveCMD()
        getCommand("god").executor = GodCMD()
        getCommand("heal").executor = HealCMD()
        getCommand("invsee").executor = InvseeCMD()
        getCommand("kickall").executor = KickAllCMD()
        getCommand("more").executor = MoreCMD()
        getCommand("nobreak").executor = NoBreakCMD()
        getCommand("nodamage").executor = NoDamageCMD()
        getCommand("nohit").executor = NoHitCMD()
        getCommand("nointeract").executor = NoInteractCMD()
        getCommand("nopickup").executor = NoPickupCMD()
        getCommand("noplace").executor = NoPlaceCMD()
        getCommand("onetap").executor = OneTapCMD()
		getCommand("fakeop").executor = FakeOpCMD()
        getCommand("playerinfo").executor = PlayerInfoCMD()
        getCommand("slots").executor = SlotsCMD()
        getCommand("smite").executor = SmiteCMD()
        getCommand("speed").executor = SpeedCMD()
        getCommand("sudo").executor = SudoCMD()
        getCommand("teleportall").executor = TeleportAllCMD()
        getCommand("teleport").executor = TeleportCMD()
        getCommand("top").executor = TopCMD()
        getCommand("troll").executor = TrollCMD()

    }

    private fun registerListeners() {

        val pluginManager = server.pluginManager

        pluginManager.registerEvents(PlayerListeners(), this)
        pluginManager.registerEvents(TrollPlayerListeners(), this)

    }

}