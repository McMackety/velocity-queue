# Velocity Queue
This is a relatively simple queue plugin for Velocity. It contains two separate types of queues, a join queue, and an intra-server queue.  

The join queue sends players to a limbo server if the initial server is full, they then are sent to the main server when there is room available.

The intra-server queue queues players when they switch servers, it can be used to prevent a mass amount of people from joining at once.

All messages use the [Minimessage](https://docs.adventure.kyori.net/minimessage.html#format) format, so read that documentation for information on that message format.

Additionally, there are two placeholders available in messages:
* `{playerName}` | This is the name of the player this message is being sent to. (Available in **all** messages.)
* `{index}` | The current position of the player in the queue. (Only available in `oneLessPlayerInQueueMessage` messages.)

# Annotated Config
```yaml
---
settings:
  limboServers: # This is a yaml list of limbo servers, this is used by the joinQueue feature.
  - name: "limbo" # The name of the limbo server as registered in velocity.
    maxPlayers: 100 # The maximum number of players that can join the limbo server.
  joinQueue: # Queues players when the lobby server becomes full.
    enabled: true # Enables the feature.
    joinedQueueMessage: "<yellow>Hello <blue>{playerName}</yellow>!1" # This is the message sent when a player joins the queue.
    oneLessPlayerInQueueMessage: "<yellow>Hello <blue>{index}</yellow>!2" # Sent when a player leaves the queue.
    lastPlayerInQueueMessage: "<yellow>Hello <blue>{playerName}</yellow>!3" # Sent when a player is the next in the queue.
    joinedServerMessage: "<yellow>Hello <blue>{playerName}</yellow>!4" # Sent when the player is sent to the server from the queue.
  intraServerQueue: # Queue players as they join another server in the velocity network.
    enabled: true # Enables the feature.
    queuedServers: ["test"] # A list of the servers that will be queued when someone joins. (Use names in velocity config.)
    joinedQueueMessage: "<yellow>Hello <blue>{playerName}</yellow>!1" # This is the message sent when a player joins the queue.
    oneLessPlayerInQueueMessage: "<yellow>Hello <blue>{index}</yellow>!2" # Sent when a player leaves the queue.
    lastPlayerInQueueMessage: "<yellow>Hello <blue>{playerName}</yellow>!3" # Sent when a player is the next in the queue.
    joinedServerMessage: "<yellow>Hello <blue>{playerName}</yellow>!4" # Sent when the player is sent to the server from the queue.
```
