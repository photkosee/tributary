# Tributary
> The source code is maintained on GitLab and could be published after 2025 due to UNSW policy. </br>
Please reach out to me to review the code in person.

A Java API to build event-driven systems. <br/>
[Checkout UML diagram of my implementation here](https://drive.google.com/file/d/1RplnTzt38UexT7zDhYWakjefp2hCob3S/view?usp=sharing) <br/>
Using a generic type to support multiple types of events.
Using a JSON structure to represent the different parts of an event. <br/>

## Command Line Interface
| Command | Description | Output |
| ----- | ----- | ----- |
| `create topic <id> <type>` | Creates a new topic in the tributary. `id` is the topic’s identifier. `type` is the type of event that goes through the topic. While this can be any type in Java, for the purposes of the CLI it can either be `Integer` or `String`. | A message showing the id, type, and other relevant information about the topic confirming its creation. |
| `create partition <topic> <id>` | Creates a new partition in the topic with id `topic`.`id` is the partition’s identifier. | A message confirming the partition’s creation. |
| `create consumer group <id> <topic> <rebalancing>` | Creates a new consumer group with the given identifier. `<topic>` is the topic the consumer group is subscribed to. `<rebalancing>` is the consumer group’s initial rebalancing method, one of `Range` or `RoundRobin`. | A message confirming the consumer group’s creation. |
| `create consumer <group> <id>` | Creates a new consumer within a consumer group. | A message confirming the consumer’s creation.|
| `delete consumer <consumer>` | Deletes the consumer with the given id. | A message confirming the consumer’s deletion, and an output of the rebalanced consumer group that the consumer was previously in. |
| `create producer <id> <type> <allocation>` | Creates a new producer which produces events of the given type. `<allocation>` is either `Random` or `Manual`, determining which method of partition selection is used for publishing events. | A message confirming the producer’s creation. |
| `produce event <producer> <topic> <event> <partition>` | Produces a new event from the given producer to the given topic. `<partition>` is an optional parameter used only if the producer publishes events to a manually specified partition | The event id, the id of the partition it is currently in. |
| `consume event <consumer> <partition>` | The given consumer consumes an event from the given partition. Precondition: The consumer is already allocated to the given partition. | The id and contents of the event, showing that the consumer has received the event. |
| `show topic <topic>` | Prints a visual display of the given topic, including all partitions and all of the events currently in each partition. |
| `show consumer group <group>` | Shows all consumers in the consumer group, and which partitions each consumer is receiving events from. |
