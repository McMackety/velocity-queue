package org.mcmackety.vqueue;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001eH\u0007J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020 H\u0007J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\"H\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00110\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/mcmackety/vqueue/QueuePlugin;", "", "proxyServer", "Lcom/velocitypowered/api/proxy/ProxyServer;", "logger", "Lorg/slf4j/Logger;", "dataDirectory", "Ljava/nio/file/Path;", "(Lcom/velocitypowered/api/proxy/ProxyServer;Lorg/slf4j/Logger;Ljava/nio/file/Path;)V", "joinQueue", "Lorg/mcmackety/vqueue/Queue;", "playerToQueue", "", "Lcom/velocitypowered/api/proxy/Player;", "playerToServerToSwitch", "Lcom/velocitypowered/api/proxy/server/RegisteredServer;", "serverToMaxPlayers", "", "serverToQueue", "initQueuedServer", "", "server", "isQueuedServer", "", "resultServer", "Lcom/velocitypowered/api/event/player/ServerPreConnectEvent$ServerResult;", "onPlayerChooseInitialServer", "event", "Lcom/velocitypowered/api/event/player/PlayerChooseInitialServerEvent;", "onPlayerLeave", "Lcom/velocitypowered/api/event/connection/DisconnectEvent;", "onPlayerSwitchServer", "Lcom/velocitypowered/api/event/player/ServerPreConnectEvent;", "onProxyInitialization", "Lcom/velocitypowered/api/event/proxy/ProxyInitializeEvent;", "Companion", "vqueue"})
@com.velocitypowered.api.plugin.Plugin(id = "velocityqueue", version = "1.0.0-SNAPSHOT", name = "Velocity Queue", authors = {"McMackety"})
public final class QueuePlugin {
    private java.util.Map<com.velocitypowered.api.proxy.server.RegisteredServer, java.lang.Integer> serverToMaxPlayers;
    private org.mcmackety.vqueue.Queue joinQueue;
    private java.util.Map<com.velocitypowered.api.proxy.server.RegisteredServer, org.mcmackety.vqueue.Queue> serverToQueue;
    private java.util.Map<com.velocitypowered.api.proxy.Player, com.velocitypowered.api.proxy.server.RegisteredServer> playerToServerToSwitch;
    private java.util.Map<com.velocitypowered.api.proxy.Player, org.mcmackety.vqueue.Queue> playerToQueue;
    private static com.velocitypowered.api.proxy.ProxyServer proxyServer;
    private static org.slf4j.Logger logger;
    private static org.mcmackety.vqueue.QueuePlugin instance;
    private static org.mcmackety.vqueue.config.QueueConfig config;
    @org.jetbrains.annotations.NotNull()
    public static final org.mcmackety.vqueue.QueuePlugin.Companion Companion = null;
    
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    @com.velocitypowered.api.event.Subscribe()
    public final void onProxyInitialization(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.event.proxy.ProxyInitializeEvent event) {
    }
    
    @com.velocitypowered.api.event.Subscribe()
    public final void onPlayerChooseInitialServer(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent event) {
    }
    
    @com.velocitypowered.api.event.Subscribe()
    public final void onPlayerSwitchServer(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.event.player.ServerPreConnectEvent event) {
    }
    
    @com.velocitypowered.api.event.Subscribe()
    public final void onPlayerLeave(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.event.connection.DisconnectEvent event) {
    }
    
    private final void initQueuedServer(com.velocitypowered.api.proxy.server.RegisteredServer server) {
    }
    
    private final boolean isQueuedServer(com.velocitypowered.api.event.player.ServerPreConnectEvent.ServerResult resultServer) {
        return false;
    }
    
    @com.google.inject.Inject()
    public QueuePlugin(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.ProxyServer proxyServer, @org.jetbrains.annotations.NotNull()
    org.slf4j.Logger logger, @org.jetbrains.annotations.NotNull()
    @com.velocitypowered.api.plugin.annotation.DataDirectory()
    java.nio.file.Path dataDirectory) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u0016@BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2 = {"Lorg/mcmackety/vqueue/QueuePlugin$Companion;", "", "()V", "<set-?>", "Lorg/mcmackety/vqueue/config/QueueConfig;", "config", "getConfig", "()Lorg/mcmackety/vqueue/config/QueueConfig;", "setConfig", "(Lorg/mcmackety/vqueue/config/QueueConfig;)V", "Lorg/mcmackety/vqueue/QueuePlugin;", "instance", "getInstance", "()Lorg/mcmackety/vqueue/QueuePlugin;", "setInstance", "(Lorg/mcmackety/vqueue/QueuePlugin;)V", "Lorg/slf4j/Logger;", "logger", "getLogger", "()Lorg/slf4j/Logger;", "setLogger", "(Lorg/slf4j/Logger;)V", "Lcom/velocitypowered/api/proxy/ProxyServer;", "proxyServer", "getProxyServer", "()Lcom/velocitypowered/api/proxy/ProxyServer;", "setProxyServer", "(Lcom/velocitypowered/api/proxy/ProxyServer;)V", "vqueue"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.velocitypowered.api.proxy.ProxyServer getProxyServer() {
            return null;
        }
        
        private final void setProxyServer(com.velocitypowered.api.proxy.ProxyServer p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.slf4j.Logger getLogger() {
            return null;
        }
        
        private final void setLogger(org.slf4j.Logger p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.mcmackety.vqueue.QueuePlugin getInstance() {
            return null;
        }
        
        private final void setInstance(org.mcmackety.vqueue.QueuePlugin p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.mcmackety.vqueue.config.QueueConfig getConfig() {
            return null;
        }
        
        private final void setConfig(org.mcmackety.vqueue.config.QueueConfig p0) {
        }
        
        private Companion() {
            super();
        }
    }
}