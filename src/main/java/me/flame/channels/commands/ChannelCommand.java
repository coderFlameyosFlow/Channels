package me.flame.channels.commands;

import lombok.AllArgsConstructor;
import me.flame.channels.SimpleChannels;
import me.flame.channels.channel.Channel;
import me.flame.channels.channel.user.User;
import me.flame.channels.manager.ChannelManager;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;

import java.util.HashSet;

import static revxrsal.commands.util.Strings.colorize;

@Command("channel")
public class ChannelCommand {
    private final ChannelManager channelManager;

    public ChannelCommand(SimpleChannels plugin) {
        this.channelManager = plugin.getChannelManager();
    }

    @Subcommand("switch")
    public void onSwitchCommand(Player player, String name) {

    }

    @Subcommand("resetchannel")
    public void onResetCommand(Player player, String name) {

    }

    @Subcommand("remove")
    public void onRemoveCommand(Player player, String name) {
        var user = User.of(player);
        var channel = channelManager.findChannel(name);
        if (channel == null) {
            player.sendMessage(colorize("&cChannel " + name + " does not exist!"));
            return;
        }
        if (!channel.getCreator().equals(user)) {
            player.sendMessage(colorize("&cChannel " + name + " does not belong to you!"));
            return;
        }
        channelManager.removeChannel(name);
        player.sendMessage(colorize("&aChannel " + name + " remove!"));
    }

    @Subcommand("create")
    public void onCreateCommand(Player player, String name) {
        if (channelManager.findChannel(name) != null) {
            player.sendMessage(colorize("&cChannel " + name + " already exists!"));
            return;
        }
        channelManager.addChannel(new Channel(name, player), name);
        player.sendMessage(colorize("&aChannel " + name + " created!"));
    }
}
