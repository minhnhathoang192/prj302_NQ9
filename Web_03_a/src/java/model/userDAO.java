/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author NQ9
 */
public class userDAO {
    ArrayList<userDTO> list = new ArrayList<>();
    
    public userDAO(){
        list.add(new userDTO("admin", "admin", "Hoang Minh Nhat"));
        list.add(new userDTO("admin2", "admin2", "Nguyen Ngoc Truong"));
    }
    
    public userDTO findByID(String id){
        for (userDTO dTO : list) {
            if(dTO.getUserName().equalsIgnoreCase(id)){
                return dTO;
            }
        }
        return null;
    }
    
    public userDTO login(String userName, String passWord){
        userDTO user= findByID(userName);
        if(user!=null&&user.getPassWord().equals(passWord)){
            return user;
        }
        return null;
    }
}
