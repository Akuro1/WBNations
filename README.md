# WBNations

**WBNations** is a Minecraft plugin designed to enhance the multiplayer experience by introducing a country management system. This plugin allows players to create countries, invite other players, and communicate within their country. The plugin is primarily in Spanish, making it ideal for Spanish-speaking communities. And btw, the plugin has a lot of bugs, feel free to fix it.

## Features

- **Country Creation**: Players can create their own countries with customizable names and colors.
- **Invitation System**: Country owners can invite other players to join their country.
- **Country Chat**: A specialized chat system that allows country members to communicate with each other within a 60-block range.
- **Country Menu**: Country owners can access a menu to view country members, sent invitations, and disband the country.
- **Placeholder Support**: Integration with PlaceholderAPI to display country information using `%wbn_country%`.

## Commands

- `/wbn crear <Country Name> <Color>`: Create a new country with the specified name and color.
- `/wbn invitar <Player Name>`: Invite a player to your country.
- `/wbn salir`: Leave your current country.
- `/wbn desbandar`: Disband your country (only available to country owners).
- `/wbn chat`: Toggle country chat mode to communicate with country members.

## Installation

1. **Compile the Plugin**: You will need to compile the plugin because I didn't uploaded it to Spigot, or any other platform.
2. **Install on Server**: Place the downloaded JAR file into the `plugins` folder of your Spigot server.
3. **Restart Server**: Restart your server to load the plugin.

## Dependencies

- **Spigot**: Ensure you are running a Spigot server version 1.21.1.
- **PlaceholderAPI**: Required for placeholder functionality.

## Compilation

To compile the WBNations plugin from source, follow these steps:

### Prerequisites

- **Java Development Kit (JDK)**: JDK 8 or higher.
- **Maven**: Apache Maven for building the project.

### Steps

1. **Clone the Repository**: Clone the WBNations repository to your local machine.
   ```bash
   git clone https://github.com/yourusername/WBNations.git
2. **Navigate to the Project Directory:**
   ```bash
   cd WBNations
3. **Build the Project:** Use Maven to compile and package the plugin.
   ```bash
   mvn clean package
4. **Install the plugin:** After a succesfull compilation now you can upload the plugin from the /target directory to your plugins folder.
