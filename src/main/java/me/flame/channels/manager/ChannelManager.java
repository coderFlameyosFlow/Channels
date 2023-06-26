package me.flame.channels.manager;

import me.flame.channels.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelManager {
    private final ConcurrentMap<String, Channel> channels;

    public ChannelManager() {
        channels = new ConcurrentHashMap<>(500);
    }

    public Channel findChannel(String name) {
        return channels.get(name);
    }

    public void addChannel(Channel channel, String name) {
        channels.put(name, channel);
    }

    public void removeChannel(String name) {
        channels.remove(name);
    }
}
