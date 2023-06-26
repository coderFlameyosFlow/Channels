package me.flame.channels.channel;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import me.flame.channels.channel.user.User;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static revxrsal.commands.util.Strings.colorize;

@Getter
@EqualsAndHashCode
public class Channel {
    private final String name;
    private final @NotNull Set<User> audience;
    private final User creator;

    public Channel(String name, @NotNull Set<User> audience, Player creator) {
        this.name = name;
        this.audience = audience;
        this.creator = User.of(creator);
    }

    public Channel(String name, Player creator) {
        this(name, new HashSet<>(50), creator);
    }

    public synchronized void sendMessage(String message) {
        var channelMessage = String.format(colorize("&9[%s]: %s"), name, message);
        creator.sendMessage(colorize("&9CREATOR [" + name + "]: " + message));
        for (User user : audience) {
            user.sendMessage(channelMessage);
        }
    }
}
