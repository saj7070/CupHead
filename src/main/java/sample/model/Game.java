package sample.model;

public class Game {
    private static Game game;

    private User currentUser;
    private String hardshipLevel;

    private Game() {

    }

    public static Game getInstance(){
        if(game == null){
            game =  new Game();
            return game;
        }
        return game;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setHardshipLevel(String hardshipLevel) {
        this.hardshipLevel = hardshipLevel;
    }

    public String getHardshipLevel() {
        return hardshipLevel;
    }
}
