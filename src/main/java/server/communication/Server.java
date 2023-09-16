package server.communication;

import io.javalin.Javalin;

public class Server {
    private final Javalin server;

    public Server(ServerHandler serverHandler) {
        this.server = Javalin.create();
    }

    /**
     * Starts the server.
     * @param port The port to run the server on.
     */
    public void start(int port) {
        this.server.start(port);
    }

    /**
     * Stop the server.
     */
    public void stop() {
        this.server.stop();
    }
}
