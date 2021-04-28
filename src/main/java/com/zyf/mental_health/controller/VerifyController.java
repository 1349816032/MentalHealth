package com.zyf.mental_health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping(path = "/verify")
public class VerifyController {

    @GetMapping(path = "/verifyImage/{randomNum}")
    public void verifyCode(HttpServletRequest request,
                           HttpServletResponse response,
                           HttpSession httpSession,
                           @PathVariable String randomNum) throws IOException {
        request.setCharacterEncoding("UTF-8");

        BufferedImage bufferedImage = new BufferedImage(80, 25, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.fillRect(0, 0, 80, 25);

        // this verify rand
        char[] ch = "1234567890".toCharArray();
        Random random = new Random();
        int index;
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            index = random.nextInt(ch.length);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            Font font = new Font("宋体", Font.BOLD, 20);
            g.setFont(font);

            g.drawString(String.valueOf(ch[index]), i*20 + 2, 23);
            stringBuffer.append(ch[index]);
        }

        // add noise
        int area = (int) (0.02*80*25);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(80);
            int y = random.nextInt(25);
            bufferedImage.setRGB(x, y, random.nextInt(255));
        }

        // Set up interference lines
        for (int i = 0; i < 4; i++) {
            // set this line start and end
            int x_start = random.nextInt(80);
            int y_start = random.nextInt(25);
            int x_end = random.nextInt(80);
            int y_end = random.nextInt(25);

            g.setColor(interLine(1, 255));
            g.drawLine(x_start, y_start, x_end, y_end);
        }

        httpSession.setAttribute("verifyCode", stringBuffer.toString());

        ImageIO.write(bufferedImage, "JPG", response.getOutputStream());
    }

    private Color interLine(int low, int high) {
        if (low > 255) low = 255;
        if (low < 0) low =0;
        if (high > 255) high = 255;
        if (high < 0) high = 0;

        int interval;
        if (high > low) {
            interval = high - low;
        } else {
            interval = low - high;
        }

        int r = low + (int) (Math.random() * interval);
        int g = low + (int) (Math.random() * interval);
        int b = low + (int) (Math.random() * interval);

        return new Color(r, g, b);
    }
}
