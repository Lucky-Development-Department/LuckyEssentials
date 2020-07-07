package dev.luckynetwork.id.lyrams.objects

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object XItemStack {
    private val ITEMSTACK_MAP: HashMap<String, ItemStack> = HashMap()

    init {
        ITEMSTACK_MAP["COBBLE"] = ItemStack(Material.COBBLESTONE, 1)
        ITEMSTACK_MAP["GRANITE"] = ItemStack(Material.STONE, 1, 1)
        ITEMSTACK_MAP["POLISHEDGRANITE"] = ItemStack(Material.STONE, 1, 2)
        ITEMSTACK_MAP["DIROITE"] = ItemStack(Material.STONE, 1, 3)
        ITEMSTACK_MAP["POLISHEDDIROITE"] = ItemStack(Material.STONE, 1, 4)
        ITEMSTACK_MAP["ANDESITE"] = ItemStack(Material.STONE, 1, 5)
        ITEMSTACK_MAP["POLISHEDANDESITE"] = ItemStack(Material.STONE, 1, 6)
        ITEMSTACK_MAP["ENDSTONE"] = ItemStack(Material.ENDER_STONE, 1)

        ITEMSTACK_MAP["COARSEDIRT"] = ItemStack(Material.DIRT, 1, 1)
        ITEMSTACK_MAP["PODZOL"] = ItemStack(Material.DIRT, 1, 2)

        ITEMSTACK_MAP["OAKWOOD"] = ItemStack(Material.WOOD, 1)
        ITEMSTACK_MAP["SPRUCEWOOD"] = ItemStack(Material.WOOD, 1, 1)
        ITEMSTACK_MAP["BIRCHWOOD"] = ItemStack(Material.WOOD, 1, 2)
        ITEMSTACK_MAP["JUNGLEWOOD"] = ItemStack(Material.WOOD, 1, 3)
        ITEMSTACK_MAP["ACACIAWOOD"] = ItemStack(Material.WOOD, 1, 4)
        ITEMSTACK_MAP["DARKOAKWOOD"] = ItemStack(Material.WOOD, 1, 5)

        ITEMSTACK_MAP["OAKSAPLING"] = ItemStack(Material.SAPLING, 1)
        ITEMSTACK_MAP["SPRUCESAPLING"] = ItemStack(Material.SAPLING, 1, 1)
        ITEMSTACK_MAP["BIRCHSAPLING"] = ItemStack(Material.SAPLING, 1, 2)
        ITEMSTACK_MAP["JUNGLESAPLING"] = ItemStack(Material.SAPLING, 1, 3)
        ITEMSTACK_MAP["ACACIASAPLING"] = ItemStack(Material.SAPLING, 1, 4)
        ITEMSTACK_MAP["DARKOAKSAPLING"] = ItemStack(Material.SAPLING, 1, 5)

        ITEMSTACK_MAP["TRAPDOOR"] = ItemStack(Material.TRAP_DOOR, 1)
        ITEMSTACK_MAP["IRONTRAPDOOR"] = ItemStack(Material.IRON_TRAPDOOR, 1)

        ITEMSTACK_MAP["DOOR"] = ItemStack(Material.WOODEN_DOOR, 1)
        ITEMSTACK_MAP["IRONDOOR"] = ItemStack(Material.IRON_DOOR, 1)
        ITEMSTACK_MAP["SPRUCEDOOR"] = ItemStack(Material.SPRUCE_DOOR_ITEM, 1)
        ITEMSTACK_MAP["BIRCHDOOR"] = ItemStack(Material.BIRCH_DOOR_ITEM, 1)
        ITEMSTACK_MAP["JUNGLEDOOR"] = ItemStack(Material.JUNGLE_DOOR_ITEM, 1)
        ITEMSTACK_MAP["ACACIADOOR"] = ItemStack(Material.ACACIA_DOOR_ITEM, 1)
        ITEMSTACK_MAP["DARKOAKDOOR"] = ItemStack(Material.DARK_OAK_DOOR_ITEM, 1)

        ITEMSTACK_MAP["REDSAND"] = ItemStack(Material.SAND, 1, 1)
        ITEMSTACK_MAP["SOULSAND"] = ItemStack(Material.SOUL_SAND, 1)

        ITEMSTACK_MAP["GOLDINGOT"] = ItemStack(Material.GOLD_INGOT, 1)
        ITEMSTACK_MAP["IRONINGOT"] = ItemStack(Material.IRON_INGOT, 1)

        ITEMSTACK_MAP["GOLDORE"] = ItemStack(Material.GOLD_ORE, 1)
        ITEMSTACK_MAP["IRONORE"] = ItemStack(Material.IRON_ORE, 1)
        ITEMSTACK_MAP["COALORE"] = ItemStack(Material.COAL_ORE, 1)
        ITEMSTACK_MAP["LAPISORE"] = ItemStack(Material.LAPIS_ORE, 1)
        ITEMSTACK_MAP["DIAMONDORE"] = ItemStack(Material.DIAMOND_ORE, 1)
        ITEMSTACK_MAP["REDSTONEORE"] = ItemStack(Material.REDSTONE_ORE, 1)
        ITEMSTACK_MAP["EMERALDORE"] = ItemStack(Material.EMERALD_ORE, 1)
        ITEMSTACK_MAP["QUARTZORE"] = ItemStack(Material.QUARTZ_ORE, 1)

        ITEMSTACK_MAP["GOLDBLOCK"] = ItemStack(Material.GOLD_BLOCK, 1)
        ITEMSTACK_MAP["IRONBLOCK"] = ItemStack(Material.IRON_BLOCK, 1)
        ITEMSTACK_MAP["COALBLOCK"] = ItemStack(Material.COAL_BLOCK, 1)
        ITEMSTACK_MAP["LAPISBLOCK"] = ItemStack(Material.LAPIS_BLOCK, 1)
        ITEMSTACK_MAP["DIAMONDBLOCK"] = ItemStack(Material.DIAMOND_BLOCK, 1)
        ITEMSTACK_MAP["REDSTONEBLOCK"] = ItemStack(Material.REDSTONE_BLOCK, 1)
        ITEMSTACK_MAP["EMERALDBLOCK"] = ItemStack(Material.EMERALD_BLOCK, 1)

        ITEMSTACK_MAP["OAKLOG"] = ItemStack(Material.LOG, 1)
        ITEMSTACK_MAP["SPRUCELOG"] = ItemStack(Material.LOG, 1, 1)
        ITEMSTACK_MAP["BIRCHLOG"] = ItemStack(Material.LOG, 1, 2)
        ITEMSTACK_MAP["JUNGLELOG"] = ItemStack(Material.LOG, 1, 3)
        ITEMSTACK_MAP["ACACIALOG"] = ItemStack(Material.LOG, 1, 4)
        ITEMSTACK_MAP["DARKOAKLOG"] = ItemStack(Material.LOG, 1, 5)

        ITEMSTACK_MAP["OAKLEAVES"] = ItemStack(Material.LEAVES, 1)
        ITEMSTACK_MAP["SPRUCELEAVES"] = ItemStack(Material.LEAVES, 1, 1)
        ITEMSTACK_MAP["BIRCHLEAVES"] = ItemStack(Material.LEAVES, 1, 2)
        ITEMSTACK_MAP["JUNGLELEAVES"] = ItemStack(Material.LEAVES, 1, 3)
        ITEMSTACK_MAP["ACACIALEAVES"] = ItemStack(Material.LEAVES, 1, 4)
        ITEMSTACK_MAP["DARKOAKLEAVES"] = ItemStack(Material.LEAVES, 1, 5)

        ITEMSTACK_MAP["CHISELEDSANDSTONE"] = ItemStack(Material.SANDSTONE, 1, 1)
        ITEMSTACK_MAP["SMOOTHSANDSTONE"] = ItemStack(Material.SANDSTONE, 1, 2)

        ITEMSTACK_MAP["POWEREDRAIL"] = ItemStack(Material.POWERED_RAIL, 1)
        ITEMSTACK_MAP["DETECTORRAIL"] = ItemStack(Material.DETECTOR_RAIL, 1)

        ITEMSTACK_MAP["ORANGEWOOL"] = ItemStack(Material.WOOL, 1, 1)
        ITEMSTACK_MAP["MAGENTAWOOL"] = ItemStack(Material.WOOL, 1, 2)
        ITEMSTACK_MAP["LIGHTBLUEWOOL"] = ItemStack(Material.WOOL, 1, 3)
        ITEMSTACK_MAP["YELLOWWOOL"] = ItemStack(Material.WOOL, 1, 4)
        ITEMSTACK_MAP["LIMEWOOL"] = ItemStack(Material.WOOL, 1, 5)
        ITEMSTACK_MAP["PINKWOOL"] = ItemStack(Material.WOOL, 1, 6)
        ITEMSTACK_MAP["GRAYWOOL"] = ItemStack(Material.WOOL, 1, 7)
        ITEMSTACK_MAP["LIGHTGRAYWOOL"] = ItemStack(Material.WOOL, 1, 8)
        ITEMSTACK_MAP["CYANWOOL"] = ItemStack(Material.WOOL, 1, 9)
        ITEMSTACK_MAP["PURPLEWOOL"] = ItemStack(Material.WOOL, 1, 10)
        ITEMSTACK_MAP["BLUEWOOL"] = ItemStack(Material.WOOL, 1, 11)
        ITEMSTACK_MAP["BROWNWOOL"] = ItemStack(Material.WOOL, 1, 12)
        ITEMSTACK_MAP["GREENWOOL"] = ItemStack(Material.WOOL, 1, 13)
        ITEMSTACK_MAP["REDWOOL"] = ItemStack(Material.WOOL, 1, 14)
        ITEMSTACK_MAP["BLACKWOOL"] = ItemStack(Material.WOOL, 1, 15)

        ITEMSTACK_MAP["ORANGECARPET"] = ItemStack(Material.CARPET, 1, 1)
        ITEMSTACK_MAP["MAGENTACARPET"] = ItemStack(Material.CARPET, 1, 2)
        ITEMSTACK_MAP["LIGHTBLUECARPET"] = ItemStack(Material.CARPET, 1, 3)
        ITEMSTACK_MAP["YELLOWCARPET"] = ItemStack(Material.CARPET, 1, 4)
        ITEMSTACK_MAP["LIMECARPET"] = ItemStack(Material.CARPET, 1, 5)
        ITEMSTACK_MAP["PINKCARPET"] = ItemStack(Material.CARPET, 1, 6)
        ITEMSTACK_MAP["GRAYCARPET"] = ItemStack(Material.CARPET, 1, 7)
        ITEMSTACK_MAP["LIGHTGRAYCARPET"] = ItemStack(Material.CARPET, 1, 8)
        ITEMSTACK_MAP["CYANCARPET"] = ItemStack(Material.CARPET, 1, 9)
        ITEMSTACK_MAP["PURPLECARPET"] = ItemStack(Material.CARPET, 1, 10)
        ITEMSTACK_MAP["BLUECARPET"] = ItemStack(Material.CARPET, 1, 11)
        ITEMSTACK_MAP["BROWNCARPET"] = ItemStack(Material.CARPET, 1, 12)
        ITEMSTACK_MAP["GREENCARPET"] = ItemStack(Material.CARPET, 1, 13)
        ITEMSTACK_MAP["REDCARPET"] = ItemStack(Material.CARPET, 1, 14)
        ITEMSTACK_MAP["BLACKCARPET"] = ItemStack(Material.CARPET, 1, 15)

        ITEMSTACK_MAP["WHITEBANNER"] = ItemStack(Material.BANNER, 1, 15)
        ITEMSTACK_MAP["ORANGEBANNER"] = ItemStack(Material.BANNER, 1, 14)
        ITEMSTACK_MAP["MAGENTABANNER"] = ItemStack(Material.BANNER, 1, 13)
        ITEMSTACK_MAP["LIGHTBLUEBANNER"] = ItemStack(Material.BANNER, 1, 12)
        ITEMSTACK_MAP["YELLOWBANNER"] = ItemStack(Material.BANNER, 1, 11)
        ITEMSTACK_MAP["LIMEBANNER"] = ItemStack(Material.BANNER, 1, 10)
        ITEMSTACK_MAP["PINKBANNER"] = ItemStack(Material.BANNER, 1, 9)
        ITEMSTACK_MAP["GRAYBANNER"] = ItemStack(Material.BANNER, 1, 8)
        ITEMSTACK_MAP["LIGHTGRAYBANNER"] = ItemStack(Material.BANNER, 1, 7)
        ITEMSTACK_MAP["CYANBANNER"] = ItemStack(Material.BANNER, 1, 6)
        ITEMSTACK_MAP["PURPLEBANNER"] = ItemStack(Material.BANNER, 1, 5)
        ITEMSTACK_MAP["BLUEBANNER"] = ItemStack(Material.BANNER, 1, 4)
        ITEMSTACK_MAP["BROWNBANNER"] = ItemStack(Material.BANNER, 1, 3)
        ITEMSTACK_MAP["GREENBANNER"] = ItemStack(Material.BANNER, 1, 2)
        ITEMSTACK_MAP["REDBANNER"] = ItemStack(Material.BANNER, 1, 1)
        ITEMSTACK_MAP["BLACKBANNER"] = ItemStack(Material.BANNER, 1)

        ITEMSTACK_MAP["WHITESTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 0)
        ITEMSTACK_MAP["ORANGESTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 1)
        ITEMSTACK_MAP["MAGENTASTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 2)
        ITEMSTACK_MAP["LIGHTBLUESTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 3)
        ITEMSTACK_MAP["YELLOWSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 4)
        ITEMSTACK_MAP["LIMESTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 5)
        ITEMSTACK_MAP["PINKSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 6)
        ITEMSTACK_MAP["GRAYSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 7)
        ITEMSTACK_MAP["LIGHTGRAYSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 8)
        ITEMSTACK_MAP["CYANSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 9)
        ITEMSTACK_MAP["PURPLESTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 10)
        ITEMSTACK_MAP["BLUESTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 11)
        ITEMSTACK_MAP["BROWNSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 12)
        ITEMSTACK_MAP["GREENSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 13)
        ITEMSTACK_MAP["REDSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 14)
        ITEMSTACK_MAP["BLACKSTAINEDGLASS"] = ItemStack(Material.STAINED_GLASS, 1, 15)

        ITEMSTACK_MAP["GLASSPANE"] = ItemStack(Material.THIN_GLASS, 1)
        ITEMSTACK_MAP["WHITESTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 0)
        ITEMSTACK_MAP["WHITESTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 0)
        ITEMSTACK_MAP["ORANGESTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 1)
        ITEMSTACK_MAP["ORANGESTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 1)
        ITEMSTACK_MAP["MAGENTASTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 2)
        ITEMSTACK_MAP["MAGENTASTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 2)
        ITEMSTACK_MAP["LIGHTBLUESTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 3)
        ITEMSTACK_MAP["LIGHTBLUESTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 3)
        ITEMSTACK_MAP["YELLOWSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 4)
        ITEMSTACK_MAP["YELLOWSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 4)
        ITEMSTACK_MAP["LIMESTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 5)
        ITEMSTACK_MAP["LIMESTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 5)
        ITEMSTACK_MAP["PINKSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 6)
        ITEMSTACK_MAP["PINKSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 6)
        ITEMSTACK_MAP["GRAYSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 7)
        ITEMSTACK_MAP["GRAYSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 7)
        ITEMSTACK_MAP["LIGHTGRAYSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 8)
        ITEMSTACK_MAP["LIGHTGRAYSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 8)
        ITEMSTACK_MAP["CYANSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 9)
        ITEMSTACK_MAP["CYANSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 9)
        ITEMSTACK_MAP["PURPLESTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 10)
        ITEMSTACK_MAP["PURPLESTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 10)
        ITEMSTACK_MAP["BLUESTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 11)
        ITEMSTACK_MAP["BLUESTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 11)
        ITEMSTACK_MAP["BROWNSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 12)
        ITEMSTACK_MAP["BROWNSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 12)
        ITEMSTACK_MAP["GREENSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 13)
        ITEMSTACK_MAP["GREENSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 13)
        ITEMSTACK_MAP["REDSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 14)
        ITEMSTACK_MAP["REDSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 14)
        ITEMSTACK_MAP["BLACKSTAINEDPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 15)
        ITEMSTACK_MAP["BLACKSTAINEDGLASSPANE"] = ItemStack(Material.STAINED_GLASS_PANE, 1, 15)

        ITEMSTACK_MAP["CLAY"] = ItemStack(Material.CLAY)
        ITEMSTACK_MAP["HARDCLAY"] = ItemStack(Material.HARD_CLAY)
        ITEMSTACK_MAP["WHITESTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 0)
        ITEMSTACK_MAP["ORANGESTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 1)
        ITEMSTACK_MAP["MAGENTASTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 2)
        ITEMSTACK_MAP["LIGHTBLUESTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 3)
        ITEMSTACK_MAP["YELLOWSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 4)
        ITEMSTACK_MAP["LIMESTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 5)
        ITEMSTACK_MAP["PINKSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 6)
        ITEMSTACK_MAP["GRAYSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 7)
        ITEMSTACK_MAP["LIGHTGRAYSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 8)
        ITEMSTACK_MAP["CYANSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 9)
        ITEMSTACK_MAP["PURPLESTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 10)
        ITEMSTACK_MAP["BLUESTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 11)
        ITEMSTACK_MAP["BROWNSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 12)
        ITEMSTACK_MAP["GREENSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 13)
        ITEMSTACK_MAP["REDSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 14)
        ITEMSTACK_MAP["BLACKSTAINEDCLAY"] = ItemStack(Material.STAINED_CLAY, 1, 15)

        ITEMSTACK_MAP["DANDELION"] = ItemStack(Material.YELLOW_FLOWER, 1)
        ITEMSTACK_MAP["POPPY"] = ItemStack(Material.RED_ROSE, 1)
        ITEMSTACK_MAP["ORCHID"] = ItemStack(Material.RED_ROSE, 1, 1)
        ITEMSTACK_MAP["BLUEORCHID"] = ItemStack(Material.RED_ROSE, 1, 1)
        ITEMSTACK_MAP["ALLIUM"] = ItemStack(Material.RED_ROSE, 1, 2)
        ITEMSTACK_MAP["AZURE"] = ItemStack(Material.RED_ROSE, 1, 3)
        ITEMSTACK_MAP["AZUREBLUET"] = ItemStack(Material.RED_ROSE, 1, 3)
        ITEMSTACK_MAP["TULIP"] = ItemStack(Material.RED_ROSE, 1, 4)
        ITEMSTACK_MAP["REDTULIP"] = ItemStack(Material.RED_ROSE, 1, 4)
        ITEMSTACK_MAP["ORANGETULIP"] = ItemStack(Material.RED_ROSE, 1, 5)
        ITEMSTACK_MAP["WHITETULIP"] = ItemStack(Material.RED_ROSE, 1, 6)
        ITEMSTACK_MAP["PINKTULIP"] = ItemStack(Material.RED_ROSE, 1, 7)
        ITEMSTACK_MAP["DAISY"] = ItemStack(Material.RED_ROSE, 1, 8)
        ITEMSTACK_MAP["OXYEDAISY"] = ItemStack(Material.RED_ROSE, 1, 8)

        ITEMSTACK_MAP["SLAB"] = ItemStack(Material.STONE_SLAB2, 1)
        ITEMSTACK_MAP["STONESLAB"] = ItemStack(Material.STONE_SLAB2, 1)
        ITEMSTACK_MAP["SANDSTONESLAB"] = ItemStack(Material.STONE_SLAB2, 1, 1)
        ITEMSTACK_MAP["COBBLESLAB"] = ItemStack(Material.STONE_SLAB2, 1, 3)
        ITEMSTACK_MAP["COBBLESTONESLAB"] = ItemStack(Material.STONE_SLAB2, 1, 3)
        ITEMSTACK_MAP["BRICKSLAB"] = ItemStack(Material.STONE_SLAB2, 1, 4)
        ITEMSTACK_MAP["STONEBRICKSLAB"] = ItemStack(Material.STONE_SLAB2, 1, 5)
        ITEMSTACK_MAP["NETHERBRICKSLAB"] = ItemStack(Material.STONE_SLAB2, 1, 6)
        ITEMSTACK_MAP["NETHERSLAB"] = ItemStack(Material.STONE_SLAB2, 1, 6)
        ITEMSTACK_MAP["QUARTZSLAB"] = ItemStack(Material.STONE_SLAB2, 1, 7)

        ITEMSTACK_MAP["OAKSTAIRS"] = ItemStack(Material.WOOD_STAIRS, 1)
        ITEMSTACK_MAP["COBBLESTAIRS"] = ItemStack(Material.COBBLESTONE_STAIRS, 1)
        ITEMSTACK_MAP["BRICKSTAIRS"] = ItemStack(Material.BRICK_STAIRS, 1)
        ITEMSTACK_MAP["STONEBRICKSTAIRS"] = ItemStack(Material.SMOOTH_STAIRS, 1)
        ITEMSTACK_MAP["NETHERBRICKSTAIRS"] = ItemStack(Material.NETHER_BRICK_STAIRS, 1)
        ITEMSTACK_MAP["SPRUCESTAIRS"] = ItemStack(Material.SPRUCE_WOOD_STAIRS, 1)
        ITEMSTACK_MAP["BIRCHSTAIRS"] = ItemStack(Material.BIRCH_WOOD_STAIRS, 1)
        ITEMSTACK_MAP["JUNGLESTAIRS"] = ItemStack(Material.JUNGLE_WOOD_STAIRS, 1)
        ITEMSTACK_MAP["QUARTZSTAIRS"] = ItemStack(Material.QUARTZ_STAIRS, 1)
        ITEMSTACK_MAP["ACACIASTAIRS"] = ItemStack(Material.ACACIA_STAIRS, 1)
        ITEMSTACK_MAP["DARKOAKSTAIRS"] = ItemStack(Material.DARK_OAK_STAIRS, 1)
        ITEMSTACK_MAP["REDSANDSTONESTAIRS"] = ItemStack(Material.RED_SANDSTONE_STAIRS, 1)

        ITEMSTACK_MAP["STONEBUTTON"] = ItemStack(Material.STONE_BUTTON, 1)
        ITEMSTACK_MAP["WOODBUTTON"] = ItemStack(Material.WOOD_BUTTON, 1)

        ITEMSTACK_MAP["STONEPRESUREPLATE"] = ItemStack(Material.STONE_PLATE, 1)
        ITEMSTACK_MAP["STONEPLATE"] = ItemStack(Material.STONE_PLATE, 1)
        ITEMSTACK_MAP["WOODPRESUREPLATE"] = ItemStack(Material.WOOD_PLATE, 1)
        ITEMSTACK_MAP["WOODPLATE"] = ItemStack(Material.WOOD_PLATE, 1)
        ITEMSTACK_MAP["GOLDPRESUREPLATE"] = ItemStack(Material.GOLD_PLATE, 1)
        ITEMSTACK_MAP["GOLDPLATE"] = ItemStack(Material.GOLD_PLATE, 1)
        ITEMSTACK_MAP["IRONPRESUREPLATE"] = ItemStack(Material.IRON_PLATE, 1)
        ITEMSTACK_MAP["IRONPLATE"] = ItemStack(Material.IRON_PLATE, 1)

        ITEMSTACK_MAP["QUARTZBLOCK"] = ItemStack(Material.QUARTZ_BLOCK, 1)
        ITEMSTACK_MAP["CHISLEDQUARTZBLOCK"] = ItemStack(Material.QUARTZ_BLOCK, 1, 1)
        ITEMSTACK_MAP["PILLARQUARTZBLOCK"] = ItemStack(Material.QUARTZ_BLOCK, 1, 2)

        // combat
        ITEMSTACK_MAP["DIAMONDSWORD"] = ItemStack(Material.DIAMOND_SWORD, 1)
        ITEMSTACK_MAP["GOLDENDSWORD"] = ItemStack(Material.GOLD_SWORD, 1)
        ITEMSTACK_MAP["GOLDSWORD"] = ItemStack(Material.GOLD_SWORD, 1)
        ITEMSTACK_MAP["IRONSWORD"] = ItemStack(Material.IRON_SWORD, 1)
        ITEMSTACK_MAP["STONESWORD"] = ItemStack(Material.STONE_SWORD, 1)
        ITEMSTACK_MAP["WOODSWORD"] = ItemStack(Material.WOOD_SWORD, 1)
        ITEMSTACK_MAP["WOODENSWORD"] = ItemStack(Material.WOOD_SWORD, 1)

        ITEMSTACK_MAP["DIAMONDAXE"] = ItemStack(Material.DIAMOND_AXE, 1)
        ITEMSTACK_MAP["GOLDENDAXE"] = ItemStack(Material.GOLD_AXE, 1)
        ITEMSTACK_MAP["GOLDAXE"] = ItemStack(Material.GOLD_AXE, 1)
        ITEMSTACK_MAP["IRONAXE"] = ItemStack(Material.IRON_AXE, 1)
        ITEMSTACK_MAP["STONEAXE"] = ItemStack(Material.STONE_AXE, 1)
        ITEMSTACK_MAP["WOODAXE"] = ItemStack(Material.WOOD_AXE, 1)
        ITEMSTACK_MAP["WOODENAXE"] = ItemStack(Material.WOOD_AXE, 1)

        ITEMSTACK_MAP["DIAMONDPICKAXE"] = ItemStack(Material.DIAMOND_PICKAXE, 1)
        ITEMSTACK_MAP["GOLDENDPICKAXE"] = ItemStack(Material.GOLD_PICKAXE, 1)
        ITEMSTACK_MAP["GOLDPICKAXE"] = ItemStack(Material.GOLD_PICKAXE, 1)
        ITEMSTACK_MAP["IRONPICKAXE"] = ItemStack(Material.IRON_PICKAXE, 1)
        ITEMSTACK_MAP["STONEPICKAXE"] = ItemStack(Material.STONE_PICKAXE, 1)
        ITEMSTACK_MAP["WOODPICKAXE"] = ItemStack(Material.WOOD_PICKAXE, 1)
        ITEMSTACK_MAP["WOODENPICKAXE"] = ItemStack(Material.WOOD_PICKAXE, 1)

        ITEMSTACK_MAP["DIAMONDSHOVEL"] = ItemStack(Material.DIAMOND_SPADE, 1)
        ITEMSTACK_MAP["GOLDENDSHOVEL"] = ItemStack(Material.GOLD_SPADE, 1)
        ITEMSTACK_MAP["GOLDSHOVEL"] = ItemStack(Material.GOLD_SPADE, 1)
        ITEMSTACK_MAP["IRONSHOVEL"] = ItemStack(Material.IRON_SPADE, 1)
        ITEMSTACK_MAP["STONESHOVEL"] = ItemStack(Material.STONE_SPADE, 1)
        ITEMSTACK_MAP["WOODSHOVEL"] = ItemStack(Material.WOOD_SPADE, 1)
        ITEMSTACK_MAP["WOODENSHOVEL"] = ItemStack(Material.WOOD_SPADE, 1)

        ITEMSTACK_MAP["DIAMONDHOE"] = ItemStack(Material.DIAMOND_HOE, 1)
        ITEMSTACK_MAP["GOLDENDHOE"] = ItemStack(Material.GOLD_HOE, 1)
        ITEMSTACK_MAP["GOLDHOE"] = ItemStack(Material.GOLD_HOE, 1)
        ITEMSTACK_MAP["IRONHOE"] = ItemStack(Material.IRON_HOE, 1)
        ITEMSTACK_MAP["STONEHOE"] = ItemStack(Material.STONE_HOE, 1)
        ITEMSTACK_MAP["WOODHOE"] = ItemStack(Material.WOOD_HOE, 1)
        ITEMSTACK_MAP["WOODENHOE"] = ItemStack(Material.WOOD_HOE, 1)

        ITEMSTACK_MAP["LEATHERHELMET"] = ItemStack(Material.LEATHER_HELMET, 1)
        ITEMSTACK_MAP["LEATHERCHESTPLATE"] = ItemStack(Material.LEATHER_CHESTPLATE, 1)
        ITEMSTACK_MAP["LEATHERLEGGINGS"] = ItemStack(Material.LEATHER_LEGGINGS, 1)
        ITEMSTACK_MAP["LEATHERBOOTS"] = ItemStack(Material.LEATHER_BOOTS, 1)

        ITEMSTACK_MAP["GOLDHELMET"] = ItemStack(Material.GOLD_HELMET, 1)
        ITEMSTACK_MAP["GOLDENHELMET"] = ItemStack(Material.GOLD_HELMET, 1)
        ITEMSTACK_MAP["GOLDCHESTPLATE"] = ItemStack(Material.GOLD_CHESTPLATE, 1)
        ITEMSTACK_MAP["GOLDENCHESTPLATE"] = ItemStack(Material.GOLD_CHESTPLATE, 1)
        ITEMSTACK_MAP["GOLDLEGGINGS"] = ItemStack(Material.GOLD_LEGGINGS, 1)
        ITEMSTACK_MAP["GOLDENLEGGINGS"] = ItemStack(Material.GOLD_LEGGINGS, 1)
        ITEMSTACK_MAP["GOLDBOOTS"] = ItemStack(Material.GOLD_BOOTS, 1)
        ITEMSTACK_MAP["GOLDENBOOTS"] = ItemStack(Material.GOLD_BOOTS, 1)

        ITEMSTACK_MAP["IRONHELMET"] = ItemStack(Material.IRON_HELMET, 1)
        ITEMSTACK_MAP["IRONCHESTPLATE"] = ItemStack(Material.IRON_CHESTPLATE, 1)
        ITEMSTACK_MAP["IRONLEGGINGS"] = ItemStack(Material.IRON_LEGGINGS, 1)
        ITEMSTACK_MAP["IRONBOOTS"] = ItemStack(Material.IRON_BOOTS, 1)

        ITEMSTACK_MAP["CHAINMAILHELMET"] = ItemStack(Material.CHAINMAIL_HELMET, 1)
        ITEMSTACK_MAP["CHAINHELMET"] = ItemStack(Material.CHAINMAIL_HELMET, 1)
        ITEMSTACK_MAP["CHAINMAILCHESTPLATE"] = ItemStack(Material.CHAINMAIL_CHESTPLATE, 1)
        ITEMSTACK_MAP["CHAINCHESTPLATE"] = ItemStack(Material.CHAINMAIL_CHESTPLATE, 1)
        ITEMSTACK_MAP["CHAINMAILLEGGINGS"] = ItemStack(Material.CHAINMAIL_LEGGINGS, 1)
        ITEMSTACK_MAP["CHAINLEGGINGS"] = ItemStack(Material.CHAINMAIL_LEGGINGS, 1)
        ITEMSTACK_MAP["CHAINMAILBOOTS"] = ItemStack(Material.CHAINMAIL_BOOTS, 1)
        ITEMSTACK_MAP["CHAINBOOTS"] = ItemStack(Material.CHAINMAIL_BOOTS, 1)

        ITEMSTACK_MAP["DIAMONDHELMET"] = ItemStack(Material.DIAMOND_HELMET, 1)
        ITEMSTACK_MAP["DIAMONDCHESTPLATE"] = ItemStack(Material.DIAMOND_CHESTPLATE, 1)
        ITEMSTACK_MAP["DIAMONDLEGGINGS"] = ItemStack(Material.DIAMOND_LEGGINGS, 1)
        ITEMSTACK_MAP["DIAMONDBOOTS"] = ItemStack(Material.DIAMOND_BOOTS, 1)

        // food stuff
        ITEMSTACK_MAP["GOLDAPPLE"] = ItemStack(Material.GOLDEN_APPLE, 1)
        ITEMSTACK_MAP["GOLDENAPPLE"] = ItemStack(Material.GOLDEN_APPLE, 1)
        ITEMSTACK_MAP["GODAPPLE"] = ItemStack(Material.GOLDEN_APPLE, 1, 1)
        ITEMSTACK_MAP["NOTCHAPPLE"] = ItemStack(Material.GOLDEN_APPLE, 1, 1)
        ITEMSTACK_MAP["RAWBEEF"] = ItemStack(Material.RAW_BEEF, 1)
        ITEMSTACK_MAP["BEEF"] = ItemStack(Material.RAW_BEEF, 1)
        ITEMSTACK_MAP["RAWMEAT"] = ItemStack(Material.RAW_BEEF, 1)
        ITEMSTACK_MAP["MEAT"] = ItemStack(Material.RAW_BEEF, 1)
        ITEMSTACK_MAP["STEAK"] = ItemStack(Material.COOKED_BEEF, 1)
        ITEMSTACK_MAP["COOKEDBEEF"] = ItemStack(Material.COOKED_BEEF, 1)
        ITEMSTACK_MAP["COOKEDMEAT"] = ItemStack(Material.COOKED_BEEF, 1)
        ITEMSTACK_MAP["PORKCHOP"] = ItemStack(Material.PORK, 1)
        ITEMSTACK_MAP["COOKEDPORKCHOP"] = ItemStack(Material.GRILLED_PORK, 1)
        ITEMSTACK_MAP["RAWMUTTON"] = ItemStack(Material.MUTTON, 1)
        ITEMSTACK_MAP["COOKEDMUTTON"] = ItemStack(Material.COOKED_MUTTON, 1)
        ITEMSTACK_MAP["RAWRABBIT"] = ItemStack(Material.RABBIT, 1)
        ITEMSTACK_MAP["COOKEDRABBIT"] = ItemStack(Material.COOKED_RABBIT, 1)
        ITEMSTACK_MAP["FISH"] = ItemStack(Material.RAW_FISH, 1)
        ITEMSTACK_MAP["SALMON"] = ItemStack(Material.RAW_FISH, 1, 1)
        ITEMSTACK_MAP["CLOWNFISH"] = ItemStack(Material.RAW_FISH, 1, 2)
        ITEMSTACK_MAP["PUFFERFISH"] = ItemStack(Material.RAW_FISH, 1, 3)
        ITEMSTACK_MAP["COOKEDFISH"] = ItemStack(Material.COOKED_FISH, 1)
        ITEMSTACK_MAP["COOKEDSALMON"] = ItemStack(Material.COOKED_FISH, 1, 1)
        ITEMSTACK_MAP["BAKEDPOTATO"] = ItemStack(Material.BAKED_POTATO, 1)
        ITEMSTACK_MAP["POISONEDPOTATO"] = ItemStack(Material.POISONOUS_POTATO, 1)
        ITEMSTACK_MAP["POSIONPOTATO"] = ItemStack(Material.POISONOUS_POTATO, 1)
        ITEMSTACK_MAP["SPIDEREYE"] = ItemStack(Material.SPIDER_EYE, 1)

        // bucket
        ITEMSTACK_MAP["WATER"] = ItemStack(Material.WATER_BUCKET, 1)
        ITEMSTACK_MAP["WATERBUCKET"] = ItemStack(Material.WATER_BUCKET, 1)
        ITEMSTACK_MAP["LAVA"] = ItemStack(Material.LAVA_BUCKET, 1)
        ITEMSTACK_MAP["LAVABUCKET"] = ItemStack(Material.LAVA_BUCKET, 1)

        // misc
        ITEMSTACK_MAP["MUSHROOM"] = ItemStack(Material.RED_MUSHROOM, 1)
        ITEMSTACK_MAP["REDMUSHROOM"] = ItemStack(Material.RED_MUSHROOM, 1)
        ITEMSTACK_MAP["BROWNMUSHROOM"] = ItemStack(Material.BROWN_MUSHROOM, 1)
        ITEMSTACK_MAP["MOSSSTONE"] = ItemStack(Material.MOSSY_COBBLESTONE, 1)
        ITEMSTACK_MAP["CRAFTINGTABLE"] = ItemStack(Material.WORKBENCH, 1)
        ITEMSTACK_MAP["PEARL"] = ItemStack(Material.ENDER_PEARL, 1)
        ITEMSTACK_MAP["ENDERPEARL"] = ItemStack(Material.ENDER_PEARL, 1)
        ITEMSTACK_MAP["LILLYPAD"] = ItemStack(Material.WATER_LILY, 1)
        ITEMSTACK_MAP["LILLY"] = ItemStack(Material.WATER_LILY, 1)
        ITEMSTACK_MAP["SNOW"] = ItemStack(Material.SNOW_BLOCK, 1)
        ITEMSTACK_MAP["SNOWBALL"] = ItemStack(Material.SNOW_BALL, 1)
        ITEMSTACK_MAP["CLAYBALL"] = ItemStack(Material.CLAY_BALL, 1)
        ITEMSTACK_MAP["MELON"] = ItemStack(Material.MELON_BLOCK, 1)
        ITEMSTACK_MAP["ENCHANTTABLE"] = ItemStack(Material.ENCHANTMENT_TABLE, 1)
        ITEMSTACK_MAP["ENDPORTAL"] = ItemStack(Material.ENDER_PORTAL_FRAME, 1)
        ITEMSTACK_MAP["ENDERCHEST"] = ItemStack(Material.ENDER_CHEST, 1)
        ITEMSTACK_MAP["TRAPPEDCHEST"] = ItemStack(Material.TRAPPED_CHEST, 1)
        ITEMSTACK_MAP["NETHERSTAR"] = ItemStack(Material.NETHER_STAR, 1)
        ITEMSTACK_MAP["TRIPWIRE"] = ItemStack(Material.TRIPWIRE_HOOK, 1)
        ITEMSTACK_MAP["TRIPWIREHOOK"] = ItemStack(Material.TRIPWIRE_HOOK, 1)
        ITEMSTACK_MAP["STICKYPISTON"] = ItemStack(Material.PISTON_STICKY_BASE, 1)
        ITEMSTACK_MAP["COBWEB"] = ItemStack(Material.WEB, 1)
        ITEMSTACK_MAP["NAMETAG"] = ItemStack(Material.NAME_TAG, 1)
        ITEMSTACK_MAP["FLINTANDSTEEL"] = ItemStack(Material.FLINT_AND_STEEL, 1)
        ITEMSTACK_MAP["FLINTSTEEL"] = ItemStack(Material.FLINT_AND_STEEL, 1)
        ITEMSTACK_MAP["LIGHTER"] = ItemStack(Material.FLINT_AND_STEEL, 1)
        ITEMSTACK_MAP["FISHINGROD"] = ItemStack(Material.FISHING_ROD, 1)
        ITEMSTACK_MAP["ROD"] = ItemStack(Material.FISHING_ROD, 1)
        ITEMSTACK_MAP["WETSPONGE"] = ItemStack(Material.SPONGE, 1, 1)
        ITEMSTACK_MAP["LAPIS"] = ItemStack(Material.INK_SACK, 1, 4)
        ITEMSTACK_MAP["LAPISLAZULI"] = ItemStack(Material.INK_SACK, 1, 4)


        // colorful(gay) bed
        ITEMSTACK_MAP["WHITEBED"] = ItemStack(Material.BED, 1, 0)
        ITEMSTACK_MAP["ORANGEBED"] = ItemStack(Material.BED, 1, 1)
        ITEMSTACK_MAP["MAGENTABED"] = ItemStack(Material.BED, 1, 2)
        ITEMSTACK_MAP["LIGHTBLUEBED"] = ItemStack(Material.BED, 1, 3)
        ITEMSTACK_MAP["YELLOWBED"] = ItemStack(Material.BED, 1, 4)
        ITEMSTACK_MAP["LIMEBED"] = ItemStack(Material.BED, 1, 5)
        ITEMSTACK_MAP["PINKBED"] = ItemStack(Material.BED, 1, 6)
        ITEMSTACK_MAP["GRAYBED"] = ItemStack(Material.BED, 1, 7)
        ITEMSTACK_MAP["LIGHTGRAYBED"] = ItemStack(Material.BED, 1, 8)
        ITEMSTACK_MAP["CYANBED"] = ItemStack(Material.BED, 1, 9)
        ITEMSTACK_MAP["PURPLEBED"] = ItemStack(Material.BED, 1, 10)
        ITEMSTACK_MAP["BLUEDBED"] = ItemStack(Material.BED, 1, 11)
        ITEMSTACK_MAP["BROWNBED"] = ItemStack(Material.BED, 1, 12)
        ITEMSTACK_MAP["GREENBED"] = ItemStack(Material.BED, 1, 13)
        ITEMSTACK_MAP["REDBED"] = ItemStack(Material.BED, 1, 14)
        ITEMSTACK_MAP["BLACKBED"] = ItemStack(Material.BED, 1, 15)

    }

    /**
     * gets an itemStack by the 'human' name
     *
     * @param name = the 'human' item name
     * @param amount = the amount of item
     */
    fun getByName(name: String, amount: Int, damage: Int = 0): ItemStack? {
        var itemStack: ItemStack? = null

        // tries to get it the normal way
        if (Material.getMaterial(name.toUpperCase()) != null)
            itemStack = ItemStack(Material.getMaterial(name.toUpperCase()), amount)

        // tries to get it from the map
        if (itemStack == null)
            itemStack = ITEMSTACK_MAP[name.toUpperCase()]


        // AHA! We found it! Now apply the amount
        if (itemStack != null) {

            if (amount == -1) {
                if (itemStack.maxStackSize > 1)
                    itemStack.amount = itemStack.maxStackSize
                else
                    itemStack.amount = 1
            } else {
                itemStack.amount = amount
            }


            // And apply the durability if needed...
            if (itemStack.durability == 0.toShort())
                itemStack.durability = damage.toShort()

        }

        return itemStack

    }


}