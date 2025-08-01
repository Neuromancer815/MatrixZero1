package com.neuromancer.matrixzero.tile;

import com.neuromancer.matrixzero.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile [10]; //number of tiles

        getTileImage();
    }

    public void getTileImage() {
        try {
            //tile[0] = new Tile();
            //tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0_water.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/1_grass.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
     public void draw(Graphics2D g2) {
        //g2.drawImage(tile[0].image, 0,0, gp.tileSize, gp.tileSize, null); //no, just no
         int column = 0;
         int row = 0;
         int x = 0;
         int y = 0;

         while (column < gp.maxScreenColumn && row < gp.maxScreenRow) {

             g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize,null);
             column++;
             x += gp.tileSize;

             if (column == gp.maxScreenColumn) {
                 column = 0;
                 x = 0;
                 row++;
                 y += gp.tileSize;
             }
         }
     }
}
