package server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "token", unique = true, nullable = false)
    private String token;

    public User(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String password) {
        this.token = password;
    }
}
