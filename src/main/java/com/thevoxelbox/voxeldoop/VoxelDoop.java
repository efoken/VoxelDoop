package com.thevoxelbox.voxeldoop;

import com.thevoxelbox.voxeldoop.tools.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class VoxelDoop extends JavaPlugin {
    private final ToolManager toolManager;
    private final DoopListener listener;

    public VoxelDoop() {
        this.toolManager = new ToolManager(this);
        this.listener = new DoopListener(this);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this.listener, this);
        this.toolManager.registerTool(new Jackhammer());
        this.toolManager.registerTool(new PaintBrush());
        this.toolManager.registerTool(new DoopStick());
        this.toolManager.registerTool(new Hammer());
        this.toolManager.registerTool(new Pliers());
        this.toolManager.registerTool(new DataSpanner());
        this.toolManager.registerTool(new Watch());
        this.toolManager.registerTool(new Shovel());
        this.toolManager.registerTool(new Chainsaw());
    }

    @Override
    public void onDisable() {
        // Nothing to do.
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();
        if (commandName.equalsIgnoreCase("godsays") && args.length >= 1) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[GOD] " + argsToMessage(args).toUpperCase());
            return true;
        } else if (commandName.equalsIgnoreCase("evilsays") && args.length >= 1) {
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "[EVIL] " + argsToMessage(args));
            return true;
        } else if (commandName.equalsIgnoreCase("scisays") && args.length >= 1) {
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "[SCIENCE] " + argsToMessage(args));
            return true;
        } else if (commandName.equalsIgnoreCase("phisays") && args.length >= 1) {
            Bukkit.broadcastMessage(ChatColor.AQUA + "[PHILOSOPHY] " + argsToMessage(args));
            return true;
        } else if (commandName.equalsIgnoreCase("ignsays") && args.length >= 1) {
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[IGNORANCE] " + argsToMessage(args).toLowerCase());
            return true;
        }
        return false;
    }

    /**
     * Gets the tool manager that handles all tool use and registration.
     *
     * @return the tool manager
     */
    public ToolManager getToolManager() {
        return toolManager;
    }

    private String argsToMessage(String[] args) {
        return StringUtils.join(args, " ");
    }
}
