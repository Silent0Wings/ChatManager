# ChatManager

## Overview
Project Report .[here](https://github.com/Silent0Wings/ChatManager/blob/f6a85b6ad62ed80b9f4e67fc1c055a2dd66efdc4/Github.pdf).

**ChatManager** is a Java-based application designed as a chat server and client system. The project includes components for managing client connections, sending and receiving messages, and ensuring consistent communication across the network.

## Features
- **Chat Server**: Manage multiple client connections and route messages.
- **Chat Client**: Send and receive messages through the server.
- **Message Handling**: Efficient message routing between clients.

## Project Structure

- **Server.java**: Handles incoming client connections and message routing.
- **Client.java**: Manages client-server communication, including sending and receiving messages.
- **MessageHandler.java**: Ensures that messages are correctly delivered to the intended recipients.

## How to Use
1. **Chat Server**: Use the `Server` class to manage connections and route messages between clients.
2. **Chat Client**: Implement the `Client` class to connect to the server and manage message exchanges.
3. **Message Handling**: Utilize the `MessageHandler` class for efficient and accurate message delivery.

## Dependencies
- Java Development Kit (JDK) 8 or higher.

## Contributing
Feel free to fork the repository and submit pull requests. Contributions are welcome to enhance the functionality and efficiency of the ChatManager project.
