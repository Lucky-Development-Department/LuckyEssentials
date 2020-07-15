# LuckyEssentials

Lucky Essentials is a plugin mainly created to replace EssentialsX's essential features in minigame servers such as /gmc, /fly, /tp, /tphere, /top, etc and also replace some useful plugins in minigame servers such as MYSlots and EasyWhitelist.

## Commands
  
- [Clear](#Clear-Inventory-Command)  
- [EditSign](#Edit-Sign-Command)  
- [Enchant](#Enchant-Command)  
- [EWhitelist](#EWhitelist-Command)  
- [Explode](#Explode-Command)  
- [Feed](#Feed-Command)  
- [Fix](#Fix-Command)  
- [Fly](#Fly-Command)  
- [Gamemode](#Gamemode-Command)  
- [Getpos](#Getpos-Command)  
- [Give](#Give-Command)  
- [God](#God-Command)  
- [Heal](#Heal-Command)  
- [Invsee](#Invsee-Command)  
- [Kickall](#Kickall-Command)  
- [More](#More-Command)  
- [Slots](#Slots-Command)  
- [Smite](#Smite-Command)  
- [Speed](#Speed-Command)  
- [Sudo](#Sudo-Command)  
- [Teleport](#Teleport-Command)  
- [Top](#Top-Command)

## Permissions

### Permission Basics

All permission nodes have a similar patteren, which is `luckyessentials.<command>.[others]`. So for any permissions that aren't documented here, will follow that permission node patteren.

The `.others` suffix is used if the command sender is performing a command that is effecting other players. 
Example: `luckyessentials.gamemode.creative.others`


#### Clear Inventory Command
Used to clear inventory of self or others

Usage:

    /clear
    /clear [<type>]
    /clear [<otherPlayer>] [<type>]
    
    Types: 
      "**" -> Clears inventory and armor slot
      "<ITEMSTACK> -> Clears a specific item in inventory
    
    Example:
      /clear **
      /clear LyraMS **
      /clear stone
      
Permission Node(s):

    luckyessentials.clear

#### Edit Sign Command
Used to edit a sign block that the player is looking at

Usage:

    /editsign set <line> <text>
    /editsign clear
    /editsign clear <line>
    
    Example:
      /editsign set 1 &e&lLuckyNetwork
      /editsign set 2 Hello
      /editsign clear
      /editsign clear 4
      
Permission Node(s):

    luckyessentials.editsign
    
#### Enchant Command
Used to enchant the item that the player is holding

Usage:

    /enchant [otherPlayer] <enchantment>
    /enchant [otherPlayer] <enchantment:level>
    /enchant [otherPlayer] <enchantment:level>,<enchantment:level>
    
    Example:
      /enchant sharpness:2
      /enchant sharpness:2,unbreaking:3
      /enchant LyraMS sharpness:2
      /enchant LyraMS sharpness:2,unbreaking:3
      
Permission Node(s):

    luckyessentials.enchant
    luckyessentials.enchant.others
    
#### EWhitelist Command
Used to whitelist a player based on their username

Usage:

    /ewl add <name>
    /ewl remove <name>
    /ewl check <name>
    /ewl list 
    /ewl clear
    /ewl toggle/on/off
    /ewl reload
        
    Example:
      /ewl add LyraMS
      /ewl remove FacedApollo
      /ewl list
      /ewl on
      
Permission Node(s):

    luckyessentials.ewhitelist
    
#### Explode Command
Used to explode something

Usage:

    /explode [target]
    /explode [target] -nodamage
    /explode [target] -power=<int>
    /explode [target] -nodamage -power=<int>
        
    Example:
      /explode
      /explode LyraMS
      /explode LyraMS -power=10
      
Permission Node(s):

    luckyessentials.explode
    luckyessentials.explode.others
    
#### Feed Command
Used to feed a player

Usage:

    /feed [target]
        
    Example:
      /feed
      /feed LyraMS
      
Permission Node(s):

    luckyessentials.feed
    luckyessentials.feed.others
    
#### Fix Command
Used to fix an item

Usage:

    /fix [target]
        
    Example:
      /fix
      /fix all
      /fix LyraMS
      /fix LyraMS all
      
Permission Node(s):

    luckyessentials.fix
    luckyessentials.fix.others
  
#### Fly Command
Used to enable or disable flight for a player

Usage:

    /fly [target]
        
    Example:
      /fly
      /fly LyraMS
      
Permission Node(s):

    luckyessentials.fly
    luckyessentials.fly.others
    luckyessentials.keepfly
    
#### Gamemode Command
Used to change a player's gamemode

Usage:

    /gamemode <gamemode> [target]
    /gm<gamemode> [target]
        
    Example:
      /gmc
      /gmc LyraMS
      /gamemode creative
      /gamemode creative LyraMS
      
Permission Node(s):

    luckyessentials.gamemode
    luckyessentials.gamemode.survival
    luckyessentials.gamemode.creative
    luckyessentials.gamemode.adventure
    luckyessentials.gamemode.spectator
    luckyessentials.gamemode.survival.others
    luckyessentials.gamemode.creative.others
    luckyessentials.gamemode.adventure.others
    luckyessentials.gamemode.spectator.others
  
#### Getpos Command
Used to get the position of a player

Usage:

    /getpos [target]
        
    Example:
      /getpos
      /getpos LyraMS
      
Permission Node(s):

    luckyessentials.getpos
    luckyessentials.getpos.others
    
#### Give Command
Used to get the position of a player

Usage:

    /give <target> <item>[:damage] [amount]
    /i <item>[:damage] [amount]
        
    Example:
      /give LyraMS stone
      /give LyraMS stone:1
      /give LyraMS snowball 64
      /i stone
      /i stone:1
      /i snowball 64
      
Permission Node(s):

    luckyessentials.give 
    luckyessentials.give.others
  
#### God Command
Used to get the position of a player

Usage:

    /god [target]
        
    Example:
      /god 
      /god LyraMS
      
Permission Node(s):

    luckyessentials.god 
    luckyessentials.god.others
  
#### Heal Command
Used to heal a player

Usage:

    /heal [target]
        
    Example:
      /heal 
      /heal LyraMS
      
Permission Node(s):

    luckyessentials.heal
    luckyessentials.heal.others
  
#### Invsee Command
Used to see other player's inventory

Usage:

    /invsee <target> [armorContents = true/false]
        
    Example:
      /invsee LyraMS
      /invsee LyraMS true
      
Permission Node(s):

    luckyessentials.invsee
    luckyessentials.invsee.modify
  
#### Kickall Command
Used to kick all players

Usage:

    /kickall [reason]
        
    Example:
      /kickall
      /kickall Maintainence!
      
Permission Node(s):

    luckyessentials.kickall
    luckyessentials.kickall.exempt
  
#### More Command
Used to get 64x of the item in hand

Usage:

    /more
        
    Example:
      /more
      
Permission Node(s):

    luckyessentials.more
  
#### Slots Command
Used to modify the server slot.
Players with the `luckyessentials.join_full` can join the server regardless of the server slot.

Usage:

    /slots set <int>
    /slots check/info
    /slots toggle/on/off
    /slots reload
        
    Example:
      /slots set 80
      /slots info
      /slots off
      /slots reload
      
Permission Node(s):

    luckyessentials.slots
    luckyessentials.join_full

    
#### Smite Command
Used to smite something or someone

Usage:

    /smite [target]
        
    Example:
      /smite
      /smite LyraMS
      
Permission Node(s):

    luckyessentials.smite
    luckyessentials.smite.others
    
#### Speed Command
Used to modify a player's speed

Usage:

    /speed <speed> [type] [target]
        
    Example:
      /speed 10
      /speed 10 fly
      /speed 10 walk LyraMS
      
Permission Node(s):

    luckyessentials.speed
    luckyessentials.speed.others
    
#### Sudo Command
Used to sudo other player

Usage:

    /sudo <target> <command>
    /sudo <target> c:<chatMessage>
        
    Example:
      /sudo LyraMS gmc
      /sudo FacedApollo c:Lucky Rank Discount 50%!
      
Permission Node(s):

    luckyessentials.sudo
    
#### Teleport Command
Used to teleport...

Usage:

    /tp <target>
    /tp <target> <target2>
    /tppos [world] <x> <y> <z> [yaw] [pitch]
    /tppos [target] [world] <x> <y> <z> [yaw] [pitch]
    /tphere [target]
        
    Example:
      /tp LyraMS
      /tp LyraMS FacedApollo
      /tppos spawn 0 64 0
      /tphere FacedApollo
      
Permission Node(s):

    luckyessentials.teleport
    luckyessentials.teleport.others
    luckyessentials.teleport.potition
    luckyessentials.teleport.potition.others
    luckyessentials.teleport.here
    
#### Top Command
Used to teleport to the top block in current position

Usage:

    /top
        
    Example:
      /top
      
Permission Node(s):

    luckyessentials.top