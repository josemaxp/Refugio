package model;

/**
 * Class for user
 * @author josem
 */
public class User {

    private String id_user;
    private String password;

    public User(String id_user, String password) {
        this.id_user = id_user;
        this.password = password;
    }

    public User(String id_user) {
        this.id_user = id_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
