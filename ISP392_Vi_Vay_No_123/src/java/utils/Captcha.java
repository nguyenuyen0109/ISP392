/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author lvhn1
 */
public class Captcha {
    
    private static final String HCAPTCHA_SECRET_KEY = "ES_66b603ebcdef481fb622b756ac44b3e2";
    
    /*public boolean verifyCaptcha(String captchaResponse) {
        try {
            String url = "https://hcaptcha.com/siteverify";
            String params = "secret=" + URLEncoder.encode(HCAPTCHA_SECRET_KEY, "UTF-8")
                    + "&response=" + URLEncoder.encode(captchaResponse, "UTF-8");

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(params.getBytes());
                os.flush();
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                return response.toString().contains("\"success\":true");
            }
        } catch (IOException e) {
            return false;
        }
    }*/
    
    public boolean verifyCaptcha(String captchaGenerate ,String captchaResponse) {
        return captchaGenerate.equalsIgnoreCase(captchaResponse);
    }
    
    private final Font[] fonts = {
        new Font("Arial", Font.BOLD, 30),
        new Font("Times New Roman", Font.PLAIN, 30),
        new Font("Courier New", Font.ITALIC, 30), // Add more fonts as needed
    };

    public String generateCaptchaText() {
        int length = 6;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captchaText.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captchaText.toString();
    }

    public BufferedImage generateCaptchaImage(String text) {
        int width = 200;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        Random random = new Random();

        for (int i = 0; i < text.length(); i++) {
            g.setFont(getRandomFont());
            g.setColor(getRandomColor());
            int x = 20 + i * 30;
            int y = 40;
            g.drawString(String.valueOf(text.charAt(i)), x, y);
        }

        for (int i = 0; i < 5; i++) {
            g.setColor(getRandomColor());
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        return image;
    }

    public Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public Font getRandomFont() {
        Random random = new Random();
        return fonts[random.nextInt(fonts.length)];
    }
    
}
