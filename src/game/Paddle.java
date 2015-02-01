/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Julian
 */
public class Paddle {
  private DoubleProperty x = new SimpleDoubleProperty();
  private Rectangle paddle;
  
  private boolean left=false;
  private boolean right=false;
  
  private double deltaX;

  public Paddle(double width, double height,double deltaX, double startPositionX, double startPositionY) 
  {
      paddle = new Rectangle(startPositionX, startPositionY, width, height);
      x.set(startPositionX);
      paddle.xProperty().bind(x);
      this.deltaX=deltaX;
      //deltaX = 10;
  }

  public void setSpeedX(double deltaX)
  {
    this.deltaX = deltaX;
  }

  public void setLeft(boolean left)
  {
    this.left = left;
  }

  public void setRight(boolean right)
  {
    this.right = right;
  }
  
  public void update(double tslf) 
  {
    if(right && x.getValue()+paddle.getWidth()+deltaX*tslf < 800)
    {
      x.set(x.getValue()+deltaX*tslf);
    }
    if(left && x.get()-deltaX*tslf > 0)
    {
      x.set(x.getValue()-deltaX*tslf);
    }
  }

  public Rectangle getPaddle() 
  {
    return paddle;
  }
}
