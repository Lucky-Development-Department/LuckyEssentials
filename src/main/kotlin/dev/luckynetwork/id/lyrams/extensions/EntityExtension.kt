package dev.luckynetwork.id.lyrams.extensions

import dev.luckynetwork.id.lyrams.LuckyEssentials
import org.bukkit.entity.Entity
import org.bukkit.metadata.FixedMetadataValue

/** applies a metadata to [Entity] easily */
internal fun Entity.applyMetadata(metadata: String, value: Any?) =
    this.setMetadata(metadata, FixedMetadataValue(LuckyEssentials.instance, value))

/** removes a metadata from [Entity] easily */
internal fun Entity.removeMetadata(metadata: String) =
    this.removeMetadata(metadata, LuckyEssentials.instance)
