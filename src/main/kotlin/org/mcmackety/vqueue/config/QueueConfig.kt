package org.mcmackety.vqueue.config

import com.fasterxml.jackson.annotation.JsonPropertyDescription

data class QueueConfig(
    val settings: Settings = Settings()
)

data class Settings(
    @JsonPropertyDescription("The limbo servers used when in the queue. Must include at least 1 for plugin to work.")
    val limboServers: List<Server> = listOf(Server()),
    @JsonPropertyDescription("These are the settings for the JoinQueue feature. This allows for players to join a limbo when the hub/main server is full.")
    val joinQueue: JoinQueue = JoinQueue(),
    @JsonPropertyDescription("These are the settings for the IntraServerQueue feature. This allows for the admin to rate limit switching servers and queue for full servers.")
    val intraServerQueue: IntraServerQueue = IntraServerQueue(),
    @JsonPropertyDescription("The message sent when you left a queue.")
    val leftQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when you joined the server.")
    val notInQueueMessage: String = "",
    @JsonPropertyDescription("The backend for the queue.")
    val queueBackend: String = "local"
)

data class Server(
    @JsonPropertyDescription("The name of the registered server as defined in the Velocity config.")
    val name: String = "limbo",
    @JsonPropertyDescription("The maximum amount of players that can join this limbo.")
    val maxPlayers: Int = 100
)

data class JoinQueue(
    @JsonPropertyDescription("Whether the join queue is enabled.")
    val enabled: Boolean = true,
    @JsonPropertyDescription("The message sent when you join the queue.")
    val joinedQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when a player leaves the queue.")
    val oneLessPlayerInQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when you are the last player in the queue.")
    val lastPlayerInQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when you joined the server.")
    val joinedServerMessage: String = ""
)

data class IntraServerQueue(
    @JsonPropertyDescription("Enables the intra-server queue.")
    val enabled: Boolean = true,
    @JsonPropertyDescription("The servers which have the queue enabled.")
    val queuedServers: List<String> = listOf("test"),
    @JsonPropertyDescription("Milliseconds between each player joining the server.")
    val millisecondsPerPlayer: Int = 1000,
    @JsonPropertyDescription("The message sent when you join the queue.")
    val joinedQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when a player leaves the queue.")
    val oneLessPlayerInQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when you are the last player in the queue.")
    val lastPlayerInQueueMessage: String = "",
    @JsonPropertyDescription("The message sent when you joined the server.")
    val joinedServerMessage: String = ""
)