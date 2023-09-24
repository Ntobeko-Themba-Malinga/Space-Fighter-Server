package server.communication;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


public class Server {
    private final Javalin server;

    public Server(ServerHandler serverHandler) {
        this.server = Javalin.create(config -> config.addStaticFiles("templates", Location.CLASSPATH));

        this.server.post("/login", serverHandler::userLogin);
        this.server.get("", ctx -> ctx.render("templates/index.html"));
        this.server.post("", serverHandler::userRegister);
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
