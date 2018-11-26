package ru.stqa.pft.sandbox;

import java.awt.*;

public class Point {

  public double x;
  public double y;


  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2) {
    double distance = Math.sqrt(Math.pow(this.x - p2.x, 2.0) + Math.pow(this.y - p2.y, 2.0));
    return distance;

  }

}



