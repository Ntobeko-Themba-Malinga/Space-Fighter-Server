package server;

import server.communication.Server;
import server.communication.ServerHandler;
import world.IWorld;

public class Main {
    public static void main(String[] args) {
        IWorld world = null;
        ServerHandler serverHandler = new ServerHandler(world);
        Server server = new Server(serverHandler);
        server.start(5000);
    }
}
