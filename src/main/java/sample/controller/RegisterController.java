package sample.controller;

import sample.model.Game;
import sample.model.User;
import sample.view.enums.Message;

public class RegisterController {

    public static Message checkRegisterUsernameAndPassword(String username , String password){
        if(username.length() == 0 || password.length() == 0){
            return Message.EMPTY_FIELD;
        }
        else if(User.getUserByUsername(username) != null){
            return Message.USER_EXIST;
        }
        return Message.SUCCESS;
    }

    public static Message changeUsernameOrPassword(String username , String password){
        if(username.length() == 0 || password.length() == 0){
            return Message.EMPTY_FIELD;
        }
        else if(User.getUserByUsername(username) != null &&
                User.getUserByUsername(username).getUsername().equals(Game.getInstance().getCurrentUser().getUsername())){
            return Message.SUCCESS;
        }
        else if(User.getUserByUsername(username) != null){
            return Message.USER_EXIST;
        }
        return Message.SUCCESS;
    }

    public static void addUser(String username , String password){
        User user = new User(username , password , User.randomProfileImage());
        User.getAllUsers().add(user);
    }

    public static void changeDataOfUser(String username , String password){
        Game.getInstance().getCurrentUser().setUsername(username);
        Game.getInstance().getCurrentUser().setPassword(password);

    }
}
