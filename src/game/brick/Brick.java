/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.brick;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Julian
 */
public class Brick {
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private Rectangle brick;
    private int hits = 1;
    
    public Brick(int hits, double width, double height, double startX, double startY) {
        this.hits = hits;
        brick = new Rectangle(width, height);
        x.set(startX);
        y.set(startY);
        brick.xProperty().bind(x);
        brick.yProperty().bind(y);
    }
    
    public Rectangle getBrick() {return brick;}
}
