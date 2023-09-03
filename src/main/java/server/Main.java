package server;

import server.communication.Server;
import server.communication.ServerHandler;
import world.World;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        ServerHandler serverHandler = new ServerHandler(world);
        Server server = new Server(serverHandler);
        server.start(5000);
    }
}
