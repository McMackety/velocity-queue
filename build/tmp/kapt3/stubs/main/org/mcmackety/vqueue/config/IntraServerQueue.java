package org.mcmackety.vqueue.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b\u0012\b\b\u0003\u0010\t\u001a\u00020\u0006\u0012\b\b\u0003\u0010\n\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0003\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003JU\u0010 \u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u00062\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u00062\b\b\u0003\u0010\f\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\bH\u00d6\u0001J\t\u0010$\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006%"}, d2 = {"Lorg/mcmackety/vqueue/config/IntraServerQueue;", "", "enabled", "", "queuedServers", "", "", "millisecondsPerPlayer", "", "joinedQueueMessage", "oneLessPlayerInQueueMessage", "lastPlayerInQueueMessage", "joinedServerMessage", "(ZLjava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEnabled", "()Z", "getJoinedQueueMessage", "()Ljava/lang/String;", "getJoinedServerMessage", "getLastPlayerInQueueMessage", "getMillisecondsPerPlayer", "()I", "getOneLessPlayerInQueueMessage", "getQueuedServers", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "vqueue"})
public final class IntraServerQueue {
    private final boolean enabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> queuedServers = null;
    private final int millisecondsPerPlayer = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String joinedQueueMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String oneLessPlayerInQueueMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String lastPlayerInQueueMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String joinedServerMessage = null;
    
    public final boolean getEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getQueuedServers() {
        return null;
    }
    
    public final int getMillisecondsPerPlayer() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJoinedQueueMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOneLessPlayerInQueueMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastPlayerInQueueMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJoinedServerMessage() {
        return null;
    }
    
    public IntraServerQueue(@com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "Enables the intra-server queue.")
    boolean enabled, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The servers which have the queue enabled.")
    java.util.List<java.lang.String> queuedServers, @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "Milliseconds between each player joining the server.")
    int millisecondsPerPlayer, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when you join the queue.")
    java.lang.String joinedQueueMessage, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when a player leaves the queue.")
    java.lang.String oneLessPlayerInQueueMessage, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when you are the last player in the queue.")
    java.lang.String lastPlayerInQueueMessage, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when you joined the server.")
    java.lang.String joinedServerMessage) {
        super();
    }
    
    public IntraServerQueue() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.mcmackety.vqueue.config.IntraServerQueue copy(@com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "Enables the intra-server queue.")
    boolean enabled, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The servers which have the queue enabled.")
    java.util.List<java.lang.String> queuedServers, @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "Milliseconds between each player joining the server.")
    int millisecondsPerPlayer, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when you join the queue.")
    java.lang.String joinedQueueMessage, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when a player leaves the queue.")
    java.lang.String oneLessPlayerInQueueMessage, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when you are the last player in the queue.")
    java.lang.String lastPlayerInQueueMessage, @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "The message sent when you joined the server.")
    java.lang.String joinedServerMessage) {
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