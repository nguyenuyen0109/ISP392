/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import model.Account;
import model.Capcha;

/**
 *
 * @author MINIMONIE
 */
public class CaptchaDAO {
     DBContext db;

    public CaptchaDAO(){
        db = DBContext.getInstance();
    }

     public String generateCaptcha(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }
    
     
    public static void main(String[] args) {
        CaptchaDAO u= new CaptchaDAO();
        String capcha = u.generateCaptcha();
        System.out.println(capcha);
    }
         
     }

