package sample.controller;

import sample.model.Game;
import sample.model.User;
import sample.view.enums.Message;

public class LoginController {

    public static Message checkLoginUsernameAndPassword(String username , String password){
        if(username.length() == 0 ||password.length() == 0 ){
            return Message.EMPTY_FIELD;
        }
        else if (User.getUserByUsername(username) == null){
            return Message.USER_NOT_EXIST;
        }
        else if(!checkPassword(username , password)){
            return Message.INCORRECT_DATA;
        }
        return Message.SUCCESS;

    }

    public static boolean checkPassword( String username, String password){
        User user = User.getUserByUsername(username);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public static void changeCurrentUser(User user) {
        Game.getInstance().setCurrentUser(user);
    }
}
