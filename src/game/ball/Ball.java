/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.ball;

import game.Paddle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

/**
 *
 * @author Julian
 */
public abstract class Ball 
{
    protected DoubleProperty x = new SimpleDoubleProperty();
    protected DoubleProperty y = new SimpleDoubleProperty();
    protected Circle ball;
    protected double deltaX;
    protected double deltaY;
    
    public Ball(double radius, double centerX, double centerY,Paddle player) {
        ball = new Circle(centerX, centerY, radius);
//        x.bind(ball.centerXProperty());
//        y.bind(ball.centerYProperty());
        x.set(centerX);
        y.set(centerY);
        ball.centerXProperty().bind(x);
        ball.centerYProperty().bind(y);
        deltaX = 300;
        deltaY = -300;
    }

  public void setDeltaX(double deltaX)
  {
    this.deltaX = deltaX;
  }

  public void setDeltaY(double deltaY)
  {
    this.deltaY = deltaY;
  }
    
    
  public void update(double tslf) 
  {
    collidePlayer();
    x.set(x.get()+deltaX*tslf);
    y.set(y.get()+deltaY*tslf);
  }
  protected void collidePlayer()
  {
    
  }
  
  public Circle getBall() {return ball;}

  public double getDeltaX()
  {
    return deltaX;
  }

  public double getDeltaY()
  {
    return deltaY;
  }
    
    
}
