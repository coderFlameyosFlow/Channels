package me.flame.channels.listener;

import lombok.AllArgsConstructor;
import me.flame.channels.SimpleChannels;
import me.flame.channels.channel.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@AllArgsConstructor
public class ChatListener implements Listener {
    private final SimpleChannels plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        var player = e.getPlayer();
        var message = e.getMessage();
        User user = User.of(player);
        if (user.getCurrentChannel() != null) {
            user.getCurrentChannel().sendMessage(message);
        }
    }
}
