package server.communication;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;


public class Server {
    private final Javalin server;

    public Server(ServerHandler serverHandler) {
        JavalinThymeleaf.configure(templateEngine());
        this.server = Javalin.create(config -> config.addStaticFiles("templates", Location.CLASSPATH));
        endPoints(serverHandler);
    }

    /**
     * Configures all the server's endpoints.
     * @param serverHandler contains methods to call for a specific endpoint.
     */
    private void endPoints(ServerHandler serverHandler) {
        this.server.post("/game", serverHandler::game);
        this.server.post("/login", serverHandler::userLogin);
        this.server.post("/logout", serverHandler::userLogout);
        this.server.get("", ctx -> ctx.render("index.html"));
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

    private TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        templateEngine.setTemplateResolver(resolver);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

}
