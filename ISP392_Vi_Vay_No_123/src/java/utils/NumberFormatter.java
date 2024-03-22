/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author admin
 */
import java.text.DecimalFormat;

public class NumberFormatter {

    public static String formatTotalAmount(double totalAmount) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(totalAmount);
    }
}
