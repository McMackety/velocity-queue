# Velocity Queue
This is a relatively simple queue plugin for Velocity. It contains two separate types of queues, a join queue, and an intra-server queue.  

The join queue sends players to a limbo server if the initial server is full, they then are sent to the main server when there is room available.

The intra-server queue queues players when they switch servers, it can be used to prevent a mass amount of people from joining at once.

All messages use the [Minimessage](https://docs.adventure.kyori.net/minimessage.html#format) format, so read that documentation for information on that message format.

Additionally, there are two placeholders available in messages:
* `<playerName>` | This is the name of the player this message is being sent to. (Available in **all** messages.)
* `<index>` | The current position of the player in the queue. (Only available in `oneLessPlayerInQueueMessage` messages.)

# Annotated Config
```yaml
---
settings:
  limboServers:
  - name: "limbo"
    maxPlayers: 100
  joinQueue:
    enabled: true
    joinedQueueMessage: "<green>You have joined the queue!</green>"
    oneLessPlayerInQueueMessage: "<green>Position in Queue:</green> <yellow><bold><index></bold></yellow>"
    lastPlayerInQueueMessage: "<green>You are up next in the queue!</green>"
    joinedServerMessage: "<green>You have now joined the server!</green>"
  intraServerQueue:
    enabled: true
    queuedServers:
    - "s2"
    millisecondsPerPlayer: 5000
    joinedQueueMessage: "<green>You have joined the queue!</green>"
    oneLessPlayerInQueueMessage: "<green>Position in Queue:</green> <yellow><bold><index></bold></yellow>"
    lastPlayerInQueueMessage: "<green>You are up next in the queue!</green>"
    joinedServerMessage: "<green>You have now joined the server!</green>"
  leftQueueMessage: "<green>You have now left the queue!</green>"
  notInQueueMessage: "<green>You are not currently in a queue!</green>"
```
