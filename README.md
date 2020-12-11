# Velocity Queue Plugin
This is a relatively simple queue plugin for Velocity. It contains two seperate types of queues, a join queue and a intra-server queue.  

The join queue sends players to a limbo server if the initial server is full, they then are sent to the main server when there is room available.

The intra-server queue queues players when they switch servers, it can be used to prevent mass amount of people joining at once.

All messages use the [Minimessage](https://docs.adventure.kyori.net/minimessage.html#format) format, so read that documentation for information on that message format. Additionally, any `oneLessPlayerInQueueMessage` messages have the `<index></index>` value which contains the number of players in-front of the player. For example, if there are 3 people in-front of your `Hello <index></index>` will output as `Hello 3`.

# Annotated Config
```yaml
---
settings:
  limboServers: # This is a yaml list of limbo servers, this is used by the joinQueue feature.
  - name: "limbo" # The name of the limbo server as registered in velocity.
    maxPlayers: 100 # The maximum number of players that can join the limbo server.
  joinQueue: # Queues players when the lobby server becomes full.
    enabled: true # Enables the feature.
    joinedQueueMessage: "<yellow>Hello <blue>World</yellow>!1" # This is the message sent when a player joins the queue.
    oneLessPlayerInQueueMessage: "<yellow>Hello <blue><index></index></yellow>!2" # Sent when a player leaves the queue.
    lastPlayerInQueueMessage: "<yellow>Hello <blue>World</yellow>!3" # Sent when a player is the next in the queue.
    joinedServerMessage: "<yellow>Hello <blue>World</yellow>!4" # Sent when the player is sent to the server from the queue.
  intraServerQueue: # Queue players as they join another server in the velocity network.
    enabled: true # Enables the feature.
    queuedServers: ["test"] # A list of the servers that will be queued when someone joins. (Use names in velocity config.)
    joinedQueueMessage: "<yellow>Hello <blue>World</yellow>!1" # This is the message sent when a player joins the queue.
    oneLessPlayerInQueueMessage: "<yellow>Hello <blue><index></index></yellow>!2" # Sent when a player leaves the queue.
    lastPlayerInQueueMessage: "<yellow>Hello <blue>World</yellow>!3" # Sent when a player is the next in the queue.
    joinedServerMessage: "<yellow>Hello <blue>World</yellow>!4" # Sent when the player is sent to the server from the queue.
```
