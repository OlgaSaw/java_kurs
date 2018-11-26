package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main (String[] args) {
		hello("world");
		hello("user");
		hello( "Alexei");

		double l = 5;
    System.out.println("Powierzchnia kwadratu o boku " +l + "= " + area(l));

    double a = 4;
    double b= 6;
    System.out.println("Powierzchnia prostokąta o bokach " + a + " i " + b + " = " +area(a,b));

		Point p1= new Point(0,0);
		Point p2= new Point(3,4);

		System.out.println("Odległość między 2 punktami na płaszczyźnie -static function -o współrzędnych P1(" + p1.x+","+p1.y + ") oraz P2 (" + p2.x+"," + p2.y + ") = " +distance(p1,p2));
		System.out.println("Odległość między 2 punktami na płaszczyźnie -method -o współrzędnych P1(" + p1.x+","+p1.y + ") oraz P2 (" + p2.x+"," + p2.y + ") = " +p1.distance(p2));

	}

	public static void hello (String somebody) {
	  System.out.println("Hello, " + somebody + "!");

  }

  public static double area (double len) {
	  return len*len;
  }

  public static double area (double a, double b) {
	  return a*b;
  }

	public static double distance (Point p1, Point p2){

		double distance = Math.sqrt(Math.pow((p2.x-p1.x), 2.0) + Math.pow((p2.y-p1.y), 2.0));


		return distance;

	}
}