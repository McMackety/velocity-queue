package org.mcmackety.vqueue.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0003\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r\u00a8\u0006\u001c"}, d2 = {"Lorg/mcmackety/vqueue/config/JoinQueue;", "", "enabled", "", "joinedQueueMessage", "", "oneLessPlayerInQueueMessage", "lastPlayerInQueueMessage", "joinedServerMessage", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEnabled", "()Z", "getJoinedQueueMessage", "()Ljava/lang/String;", "getJoinedServerMessage", "getLastPlayerInQueueMessage", "getOneLessPlayerInQueueMessage", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "vqueue"})
public final class JoinQueue {
    private final boolean enabled = false;
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
    
    public JoinQueue(@com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "Whether the join queue is enabled.")
    boolean enabled, @org.jetbrains.annotations.NotNull()
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
    
    public JoinQueue() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
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
    public final org.mcmackety.vqueue.config.JoinQueue copy(@com.fasterxml.jackson.annotation.JsonPropertyDescription(value = "Whether the join queue is enabled.")
    boolean enabled, @org.jetbrains.annotations.NotNull()
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