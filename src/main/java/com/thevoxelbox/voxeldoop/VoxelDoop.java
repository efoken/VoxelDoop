package com.thevoxelbox.voxeldoop;

import com.thevoxelbox.voxeldoop.tools.*;
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

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();
        if (commandName.equalsIgnoreCase("godsays")) {
            if (args.length < 1) {
                return false;
            }
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[GOD] " + argsToString(args).toUpperCase());
            return true;
        }
        if (commandName.equalsIgnoreCase("evilsays")) {
            if (args.length < 1) {
                return false;
            }
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "[EVIL] " + argsToString(args));
            return true;
        }
        if (commandName.equalsIgnoreCase("scisays")) {
            if (args.length < 1) {
                return false;
            }
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "[SCIENCE] " + argsToString(args));
            return true;
        }
        if (commandName.equalsIgnoreCase("phisays")) {
            if (args.length < 1) {
                return false;
            }
            Bukkit.broadcastMessage(ChatColor.AQUA + "[PHILOSOPHY] " + argsToString(args));
            return true;
        }
        if (commandName.equalsIgnoreCase("ignsays")) {
            if (args.length < 1) {
                return false;
            }
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[IGNORANCE] " + argsToString(args).toLowerCase());
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

    private String argsToString(String[] args) {
        String message = "";
        for (String str : args) {
            message = (message + str + " ");
        }
        return message;
    }
}
