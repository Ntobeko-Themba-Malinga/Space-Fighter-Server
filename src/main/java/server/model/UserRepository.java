package server.model;

import jakarta.persistence.*;


public class UserRepository implements IUserRepository {
    private final EntityManager entityManager;

    public UserRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("player_pu");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public User register(String username, String password) {
        try {
            if (username.trim() == "") throw new Exception();

            User user = new User(username, password);
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(user);
            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUser(String username, String password) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = '" + username + "' AND u.password = '" + password + "'");

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User updateUser(User user, String newPassword) {
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public void close() {
        this.entityManager.close();
    }
}
