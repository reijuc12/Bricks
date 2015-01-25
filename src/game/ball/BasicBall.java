/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ball;

import game.Paddle;

/**
 *
 * @author Christian
 */
public class BasicBall extends Ball
{
  public BasicBall(double radius, double centerX, double centerY,Paddle player)
  {
    super(radius, centerX, centerY,player);
  }
}
