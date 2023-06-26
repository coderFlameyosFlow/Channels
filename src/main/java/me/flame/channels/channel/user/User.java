package me.flame.channels.channel.user;

import lombok.Getter;
import lombok.Setter;

import me.flame.channels.channel.Channel;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Getter
public class User {
    private final Player player;
    @Setter
    private @Nullable Channel currentChannel;

    private User(Player player) {
        this.player = player;
        this.currentChannel = null;
    }

    public void joinChannel(Channel channel) {
        this.currentChannel = channel;
        channel.getAudience().add(this);
    }

    public void leaveChannel(Channel channel) {
        this.currentChannel = null;
        channel.getAudience().remove(this);
    }

    public void switchChannel(Channel channel) {
        if (this.currentChannel == channel) return;
        if (this.currentChannel != null)
            this.leaveChannel(this.currentChannel);
        this.currentChannel = channel;
        channel.getAudience().add(this);
    }

    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    public boolean equals(User user) {
        if (user == null) return false;
        if (this.player == null || user.getPlayer() == null) return false;
        if (this.player.equals(user.getPlayer())) return true;
        return Objects.equals(this.player.getUniqueId(), user.getPlayer().getUniqueId())
                && Objects.equals(this.player.getName(), user.getPlayer().getName());
    }

    public static User of(Player player) {
        return new User(player);
    }
}
