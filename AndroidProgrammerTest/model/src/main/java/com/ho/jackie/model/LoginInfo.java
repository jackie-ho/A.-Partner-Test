package com.ho.jackie.model;

/**
 * Created by JH on 9/8/16.
 */
public class LoginInfo {

    public String username;
    public String password;

    public LoginInfo(String name, String pw){
        username = name;
        password = pw;
    }
    public void clear(){
        username = "";
        password = "";
    }
}
