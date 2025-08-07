package com.akuro.wbnations.data;

import org.bukkit.ChatColor;
import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private ChatColor color;
    private String owner;
    private List<String> members;

    public Country(String name, ChatColor color, String owner) {
        this.name = name;
        this.color = color;
        this.owner = owner;
        this.members = new ArrayList<>();
        this.members.add(owner);
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String member) {
        members.add(member);
    }

    public void removeMember(String member) {
        members.remove(member);
    }
}
