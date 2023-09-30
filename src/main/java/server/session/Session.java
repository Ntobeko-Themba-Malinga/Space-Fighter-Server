package server.session;


import io.javalin.http.Context;
import server.model.User;

public class Session {
    /**
     * Adds a user to the Session when they log in.
     * @param context Javalin Context class object
     * @param user The user to add to the session
     * @param token Their unique token for their session.
     */
    public static void login(Context context, User user, String token) {
        if (!context.sessionAttributeMap().containsValue(user.getUsername()))
            context.sessionAttribute(token, user.getUsername());
    }

    /**
     * Gets a user from the session
     * @param ctx Javalin Context class object.
     * @param token The user unique token.
     * @return if user is logged in their username is returned else null.
     */
    public static String getSessionUsername(Context ctx, String token) {
        System.out.println(ctx.sessionAttributeMap().keySet());
        System.out.println((String) ctx.sessionAttributeMap().get(token));
        if (ctx.sessionAttributeMap().containsKey(token)) {
            return (String) ctx.sessionAttributeMap().get(token);
        }
        return null;
    }

    /**
     * Removes a user from the session.
     * @param context Javalin Context class object
     * @param token The user unique token.
     */
    public static void logout(Context context, String token) {
        context.sessionAttributeMap().remove(token);
    }
}
