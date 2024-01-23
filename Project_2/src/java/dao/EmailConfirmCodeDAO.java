/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.util.Random;

/**
 *
 * @author MINIMONIE
 */
public class EmailConfirmCodeDAO {

    DBContext db;

    public EmailConfirmCodeDAO() {
        db = DBContext.getInstance();
    }

    public String generateRandomSixDigit() {
        Random random = new Random();
    String characters = "0123456789";
    StringBuilder token = new StringBuilder();
    for (int i = 0;i< 6; i++) {
           token.append(characters.charAt(random.nextInt(characters.length())));
    }
    return token.toString ();
}

}
