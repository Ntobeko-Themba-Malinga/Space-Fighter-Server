package server.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class UserRepository implements IUserRepository {
    private final EntityManager entityManager;

    public UserRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("player_pu");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public User register(String username, String password) {
        try {
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
        return null;
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
