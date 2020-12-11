package org.mcmackety.vqueue.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J-\u0010\u0013\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/mcmackety/vqueue/config/Settings;", "", "limboServers", "", "Lorg/mcmackety/vqueue/config/Server;", "joinQueue", "Lorg/mcmackety/vqueue/config/JoinQueue;", "intraServerQueue", "Lorg/mcmackety/vqueue/config/IntraServerQueue;", "(Ljava/util/List;Lorg/mcmackety/vqueue/config/JoinQueue;Lorg/mcmackety/vqueue/config/IntraServerQueue;)V", "getIntraServerQueue", "()Lorg/mcmackety/vqueue/config/IntraServerQueue;", "getJoinQueue", "()Lorg/mcmackety/vqueue/config/JoinQueue;", "getLimboServers", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "vqueue"})
public final class Settings {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.mcmackety.vqueue.config.Server> limboServers = null;
    @org.jetbrains.annotations.NotNull()
    private final org.mcmackety.vqueue.config.JoinQueue joinQueue = null;
    @org.jetbrains.annotations.NotNull()
    private final org.mcmackety.vqueue.config.IntraServerQueue intraServerQueue = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.mcmackety.vqueue.config.Server> getLimboServers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.mcmackety.vqueue.config.JoinQueue getJoinQueue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.mcmackety.vqueue.config.IntraServerQueue getIntraServerQueue() {
        return null;
    }
    
    public Settings(@org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The limbo servers used when in the queue. Must include at least 1 for plugin to work.")
    java.util.List<org.mcmackety.vqueue.config.Server> limboServers, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "These are the settings for the JoinQueue feature. This allows for players to join a limbo when the hub/main server is full.")
    org.mcmackety.vqueue.config.JoinQueue joinQueue, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "These are the settings for the IntraServerQueue feature. This allows for the admin to rate limit switching servers and queue for full servers.")
    org.mcmackety.vqueue.config.IntraServerQueue intraServerQueue) {
        super();
    }
    
    public Settings() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.mcmackety.vqueue.config.Server> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.mcmackety.vqueue.config.JoinQueue component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.mcmackety.vqueue.config.IntraServerQueue component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.mcmackety.vqueue.config.Settings copy(@org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The limbo servers used when in the queue. Must include at least 1 for plugin to work.")
    java.util.List<org.mcmackety.vqueue.config.Server> limboServers, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "These are the settings for the JoinQueue feature. This allows for players to join a limbo when the hub/main server is full.")
    org.mcmackety.vqueue.config.JoinQueue joinQueue, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "These are the settings for the IntraServerQueue feature. This allows for the admin to rate limit switching servers and queue for full servers.")
    org.mcmackety.vqueue.config.IntraServerQueue intraServerQueue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}