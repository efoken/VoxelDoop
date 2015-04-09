package com.thevoxelbox.voxeldoop;

import com.thevoxelbox.voxeldoop.configuration.ConfigurationGetter;
import com.thevoxelbox.voxeldoop.configuration.ConfigurationSetter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractTool implements ITool {
    private String toolName = "Default Tool Name (Yell at the developer if you see this)";
    private Material toolMaterial = Material.AIR;

    public void updatePlayersOfBlockChange(final Block changedBlock) {
        final int horizon = (Bukkit.getViewDistance() + 1) * 16;
        final int horSqr = (int) Math.pow(horizon, 2);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (changedBlock.getLocation().getWorld().equals(player.getWorld())
                    && changedBlock.getLocation().distanceSquared(player.getLocation()) < horSqr) {
                player.sendBlockChange(changedBlock.getLocation(), changedBlock.getType(), changedBlock.getData());
            }
        }
    }

    public final String getName() {
        return this.toolName;
    }

    public final void setName(final String newName) {
        this.toolName = newName;
    }

    @ConfigurationGetter(value = "tool-id")
    public final int getToolMaterialId() {
        return this.toolMaterial.getId();
    }

    @ConfigurationSetter(value = "tool-id")
    public final void setToolMiaterial(final int newToolMatId) {
        final Material toolMat = Material.getMaterial(newToolMatId);
        if (toolMat != null && !toolMat.isBlock()) {
            this.toolMaterial = toolMat;
        } else throw new IllegalArgumentException("Invalid tool id");
    }

    public final Material getToolMaterial() {
        return this.toolMaterial;
    }

    public final void setToolMaterial(final Material newToolMat) {
        this.toolMaterial = newToolMat;
    }

    public abstract void onRangedUse(Block targetBlock, BlockFace face, ItemStack itemUsed, Player player, Action action);

    public abstract void onUse(Block targetBlock, BlockFace face, ItemStack itemUsed, Player player, Action action);
}
