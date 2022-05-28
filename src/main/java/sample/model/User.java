package sample.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private int Score;
    private String image;

    public User(String username, String password , String image) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.Score = 0;

    }

    public static User getUserByUsername(String username) {
        if (User.getAllUsers().size() == 0) {
            return null;
        }
        for (User user : User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public static void deleteAccount(User user) {
        User.getAllUsers().remove(user);
    }

    public static String randomProfileImage() {
        Random random = new Random();
        int number = random.nextInt(4) + 1;
        String url = "/assets/images/p" + number + ".png";
        return url;

    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return Score;
    }


}
