package server;

import server.communication.Server;
import server.communication.ServerHandler;
import server.model.IUserRepository;
import server.model.UserRepository;
import world.IWorld;

public class Main {
    public static void main(String[] args) {
        IWorld world = null;
//        IUserRepository userRepository = new UserRepository();
        ServerHandler serverHandler = new ServerHandler(world, null);
        Server server = new Server(serverHandler);
        server.start(5000);
    }
}
