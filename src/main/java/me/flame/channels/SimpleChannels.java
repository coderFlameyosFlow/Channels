package me.flame.channels;

import lombok.Getter;
import me.flame.channels.listener.ChatListener;
import me.flame.channels.manager.ChannelManager;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitBrigadier;
import revxrsal.commands.bukkit.BukkitCommandHandler;

@Getter
public final class SimpleChannels extends JavaPlugin {
    private ChannelManager channelManager;

    @Override
    public void onEnable() {
        channelManager = new ChannelManager();
        // Plugin startup logic
        BukkitCommandHandler handler = BukkitCommandHandler.create(this);
        handler.register(new ChannelManager());
        handler.getBrigadier().ifPresent(BukkitBrigadier::register);

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
