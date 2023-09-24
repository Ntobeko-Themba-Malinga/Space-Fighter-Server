package server.session;


import io.javalin.http.Context;
import server.model.User;

public class Session {
    public static void login(Context context, User user, String token) {
        context.sessionAttribute(token, user.getUsername());
    }

    public static void logout(Context context, String token) {
        context.sessionAttributeMap().remove(token);
    }
}
