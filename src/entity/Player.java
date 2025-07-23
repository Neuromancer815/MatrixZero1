package entity;

import com.neuromancer.matrixzero.GamePanel;
import com.neuromancer.matrixzero.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/crus_right_2.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed ||
            keyH.leftPressed || keyH.rightPressed) {

            int dx = 0;
            int dy = 0;

            if (keyH.upPressed && !keyH.downPressed) {
                dy = -speed;
            } else if (keyH.downPressed && !keyH.upPressed) {
                dy = speed;
            } else if (keyH.upPressed && keyH.downPressed) {
                // Resolve conflict based on last key
                dy = keyH.lastDirectionPressed.equals("up") ? -speed :
                        keyH.lastDirectionPressed.equals("down") ? speed : 0;
            }

            if (keyH.leftPressed && !keyH.rightPressed) {
                dx = -speed;
            } else if (keyH.rightPressed && !keyH.leftPressed) {
                dx = speed;
            } else if (keyH.leftPressed && keyH.rightPressed) {
                // Resolve conflict based on last key
                dx = keyH.lastDirectionPressed.equals("left") ? -speed :
                        keyH.lastDirectionPressed.equals("right") ? speed : 0;
            }

// Normalize diagonal movement
            if (dx != 0 || dy != 0) {
                double length = Math.sqrt(dx * dx + dy * dy);
                dx = (int)(dx / length * speed);
                dy = (int)(dy / length * speed);

                // Set animation direction
                direction = keyH.lastDirectionPressed;
            }

            x += dx;
            y += dy;

            spriteCounter++;
            if (spriteCounter > 7) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
//      g2.setBackground(Color.white);                //test rectangle code
//      g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;

            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;

            case "left":
                if (keyH.upPressed) {
                    image = (spriteNum == 1) ? up1 : up2;
                } else if (keyH.downPressed) {
                    image = (spriteNum == 1) ? down1 : down2;
                } else {
                    image = (spriteNum == 1) ? left1 : left2;
                }
                break;

            case "right":
                if (keyH.upPressed) {
                    image = (spriteNum == 1) ? up1 : up2;
                } else if (keyH.downPressed) {
                    image = (spriteNum == 1) ? down1 : down2;
                } else {
                    image = (spriteNum == 1) ? right1 : right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
