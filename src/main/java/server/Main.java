package server;

import server.communication.Server;
import server.communication.ServerHandler;
import server.model.IUserRepository;
import server.model.UserRepository;
import world.IWorld;
import world.World;
import world.maze.IMaze;
import world.maze.SimpleMaze;
import world.objects.Position;

public class Main {
    public static void main(String[] args) {
        Position topLeftCorner = new Position(-200, 200);
        Position bottomRightCorner = new Position(200, -200);
        IMaze maze = new SimpleMaze();
        maze.generateMaze(topLeftCorner, bottomRightCorner);
        IWorld world = new World(maze, topLeftCorner, bottomRightCorner);
        IUserRepository userRepository = new UserRepository();
        ServerHandler serverHandler = new ServerHandler(world, userRepository);
        Server server = new Server(serverHandler);
        server.start(5000);
    }
}
