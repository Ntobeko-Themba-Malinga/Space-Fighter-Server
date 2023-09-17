package server.model;

public interface IUserRepository {

    /**
     * Registers a new user.
     * @param username The new user's username.
     * @param password The new user's password.
     * @return instance of the new User or null if not created successfully
     */
    public User register(String username, String password);

    /**
     * Retrieves an existing user from the database.
     * @param username The username of the user to retrieve.
     * @param password The hashed password of the user to retrieve.
     * @return instance of the user retrieved from the database or null if user is not found.
     */
    public User getUser(String username, String password);

    /**
     * Updates a user's password.
     * @param user The instance of the user to update its password.
     * @param newPassword The new hashed password.
     * @return instance of the updated User.
     */
    public User updateUser(User user, String newPassword);

    /**
     * Deletes a user from the database.
     * @param user Instance of the user to delete from the database.
     * @return true if successfully deleted else false.
     */
    public boolean deleteUser(User user);

    public void close();
}
