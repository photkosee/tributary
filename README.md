# Tributary
> Note that the code here is not the complete version of the project. The complete code is maintained on GitLab and could be published after 2025 due to UNSW policy. <br/>
Please contact me to review the complete version of the code in person.

[Checkout UML diagram of my implementation](https://drive.google.com/file/d/1RplnTzt38UexT7zDhYWakjefp2hCob3S/view?usp=sharing) <br/>

## Table of Contents
1 [Overview](#overview) </br>
2 [Engineering Requirements](#engineering-requirements) <br/>
3 [Command Line Interface](#command-line-interface) </br>

## Overview
Event-Driven Architecture makes up much of the backbone of modern software. With a move towards decentralized microservice systems, there is a need for scalable asynchronous communication between software components. 

This is **a Java API** that another engineer could use to build an event-driven system using the principles discussed in lectures.

This library is based on a heavily simplified version of the event streaming infrastructure Apache Kafka.

## Engineering Requirements
The fundamental premise on which Event-Driven Architecture rests is the ability of producer and consumer entities in the system to share data asynchronously via a stream-like channel. 

However, our library will allow more complex interactions than a single channel. 

A **Tributary Cluster** contains a series of **topics**. A topic contains events that are logically grouped. For example, a cluster could contain two topics: one for image-related events and one for video-related events. You can think of them like a table in a database or a folder in a file system. 


Within each topic is a series of partitions - a collection of a sequence of messages which can be appended to.


A unit of data within a Tributary is a **message**, record, or event. For example, to update their profile, a user may send a message to Partition 1 in Topic A, which will be appended to Partition 1 in Topic A. Each partition is a queue where messages are appended at the end of the partition. Each message has an optional key to indicate which partition it should be appended to. 

A topic can be related to “user profiles,” each message relates to requesting an update to a specific profile. However, considering there can be many such requests at a given time, the system divides the incoming requests into multiple partitions. There can be multiple **consumers** consuming messages at the same time (concurrently). However, each partition is handled by only one consumer. Multiple consumers will allow us to utilize the underlying hardware with multiple cores effectively. 

In the context of the library you are building, topics are parameterized on a generic type; all event payloads within that topic must be of the specified type.

### Message Lifecycle: A Simple Example
Let us take the example of a user updating their profile. This results in an event generated by the **producer** for the topic “user profiles” with the updated profile information. This event is now delivered to the Tributary, which assigns the event to one of the partitions. The producer indicates whether the message is randomly allocated to a partition or provides a key specifying which partition to append the message to. 

A consumer processes one or more partitions by sequentially processing (consuming) events in the allocated partitions. 

## Command Line Interface
| Command | Description | Output |
| ----- | ----- | ----- |
| `create topic <id> <type>` | Creates a new topic in the tributary. `id` is the topic’s identifier. `type` is the type of event that goes through the topic. While this can be any type in Java, for the CLI, it can either be `Integer` or `String`. | A message showing the id, type, and other relevant information about the topic confirming its creation. |
| `create partition <topic> <id>` | Creates a new partition in the topic with id `topic`. `id` is the partition’s identifier. | A message confirming the partition’s creation. |
| `create consumer group <id> <topic> <rebalancing>` | Creates a new consumer group with the given identifier. `<topic>` is the topic the consumer group is subscribed to. `<rebalancing>` is the consumer group’s initial rebalancing method, one of `Range` or `RoundRobin`. | A message confirming the consumer group’s creation. |
| `create consumer <group> <id>` | Creates a new consumer within a consumer group. | A message confirming the consumer’s creation.|
| `delete consumer <consumer>` | Deletes the consumer with the given id. | A message confirming the consumer’s deletion and an output of the rebalanced consumer group that the consumer was previously in. |
| `create producer <id> <type> <allocation>` | Creates a new producer who produces events of the given type. `<allocation>` is either `Random` or `Manual`, determining which method of partition selection is used for publishing events. | A message confirming the producer’s creation. |
| `produce event <producer> <topic> <event> <partition>` | Produces a new event from the given producer to the given topic. `<partition>` is an optional parameter used only if the producer publishes events to a manually specified partition | The event id, the id of the partition it is currently in. |
| `consume event <consumer> <partition>` | The given consumer consumes an event from the partition. Precondition: The consumer is already allocated to the given partition. | The id and contents of the event, showing that the consumer has received the event. |
| `show topic <topic>` | Prints a visual display of the given topic, including all partitions and events currently in each partition. |
| `show consumer group <group>` | Shows all consumers in the consumer group and which partitions each consumer receives events from. |

![consumer-group-kafka](https://github.com/PhotKosee/event/assets/114990364/fc9cb3e8-211a-4b03-b6db-aeedd22ec9d4)

