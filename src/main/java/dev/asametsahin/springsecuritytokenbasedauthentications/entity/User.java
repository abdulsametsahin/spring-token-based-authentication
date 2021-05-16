package dev.asametsahin.springsecuritytokenbasedauthentications.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String apiToken;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        createdAt = now;
        updatedAt = now;
        apiToken = UUID.randomUUID().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String api_token) {
        this.apiToken = api_token;
    }
}
