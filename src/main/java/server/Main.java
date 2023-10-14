package server;

import server.communication.Server;
import server.communication.ServerHandler;
import server.model.IUserRepository;
import server.model.UserRepository;
import world.IWorld;
import world.World;
import world.maze.SimpleMaze;
import world.objects.Position;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        org.h2.tools.Server ser = org.h2.tools.Server.createTcpServer("-tcpPort", "8082");
        ser.start();
        IWorld world = new World(new SimpleMaze(), new Position(-200, 200), new Position(200, -200));
        IUserRepository userRepository = new UserRepository();
        ServerHandler serverHandler = new ServerHandler(world, userRepository);
        Server server = new Server(serverHandler);
        server.start(5000);
    }
}
