package org.mcmackety.vqueue;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0012R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/mcmackety/vqueue/Queue;", "", "()V", "players", "", "Lorg/mcmackety/vqueue/QueuePlayer;", "add", "", "uuid", "broadcastIndexMessage", "inQueue", "", "nextInQueue", "next", "placeInQueue", "", "remove", "removeUUID", "Ljava/util/UUID;", "vqueue"})
public final class Queue {
    private final java.util.List<org.mcmackety.vqueue.QueuePlayer> players = null;
    
    public final void add(@org.jetbrains.annotations.NotNull()
    org.mcmackety.vqueue.QueuePlayer uuid) {
    }
    
    public final void remove(@org.jetbrains.annotations.NotNull()
    org.mcmackety.vqueue.QueuePlayer uuid) {
    }
    
    public final void removeUUID(@org.jetbrains.annotations.NotNull()
    java.util.UUID uuid) {
    }
    
    public final int placeInQueue(@org.jetbrains.annotations.NotNull()
    org.mcmackety.vqueue.QueuePlayer uuid) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.mcmackety.vqueue.QueuePlayer next() {
        return null;
    }
    
    public final void broadcastIndexMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String inQueue, @org.jetbrains.annotations.NotNull()
    java.lang.String nextInQueue) {
    }
    
    public Queue() {
        super();
    }
}