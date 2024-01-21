/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.Random;

/**
 *
 * @author MINIMONIE
 */
public class CaptchaDAO {
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

