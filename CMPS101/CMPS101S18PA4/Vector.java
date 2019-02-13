// Vector.java
// A class that implements the Vector ADT.
//
// For this assignment, you must complete this code skeleton.
// You may not change the function prototypes.
// You are expected to fill in the functions to make them work
// as expected, and you can add as much as you need or want.
// We recommend implementing the Vector ADT using x and y coordinates.

// Notes:
// Angles are always in radians, not degrees.

import java.lang.Math;

class Vector {

  // Fields
  float x;
  float y;
  // --------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------

  // The default constructor should create a new Vector with no magnitude.
  public Vector() {
    x = 0;
    y = 0;
  }

  // This constructor takes an x and a y coordinate for the Vector.
  public Vector(float x, float y) {
    this.x = x;
    this.y = y;
  }

  // This "constructor" takes an angle and a magnitude for the Vector.
  // It is not a traditional constructor because only one function can have
  // the signature Vector(float, float).
  public static Vector polarVector(float angle, float magnitude) {
    // double radians = angle*Math.PI/180;
    // System.out.println("x_radians = "+radians);
    float x = magnitude * (float) Math.cos(angle);
    // (radians*180)/(float)Math.PI
    // double y_radians = angle*Math.PI/180;
    // System.out.println("y_radians = "+radians);
    float y = magnitude * (float) Math.sin(angle);
    return new Vector(x, y);
  }

  // Access functions

  /**
   * getX Returns the x coordinate of the Vector.
   */
  public float getX() {
    return this.x;
  }

  /**
   * getY Returns the y coordinate of the Vector.
   */
  public float getY() {
    return this.y;
  }

  /**
   * getAngle Returns the angle of the Vector.
   */
  public float getAngle() {
    float radians = (float) Math.atan(this.y / this.x);
    float result = (radians * 180) / (float) Math.PI;
    // if (this.x < 0)
    // {
    // return result+180;
    // }
    // else if (this.x > 0 && this.y < 0)
    // {
    // return result+360;
    // }
    return radians;
  }

  /**
   * getMagnitude Returns the magnitude of the Vector.
   */
  public float getMagnitude() {
    return (float) Math.sqrt((this.x * this.x) + (this.y * this.y));

  }

  /**
   * add Returns the sum of this Vector with the given Vector.
   */
  public Vector add(Vector other) {
    float result_x = this.x + other.x;
    float result_y = this.y + other.y;
    return new Vector(result_x, result_y);
  }

  /**
   * subtract Returns the difference between this Vector and the given Vector.
   */
  public Vector subtract(Vector other) {
    float result_x = this.x - other.x;
    float result_y = this.y - other.y;
    return new Vector(result_x, result_y);
  }

  /**
   * dotProduct Returns the dot product of this Vector and the given Vector.
   */
  public float dotProduct(Vector other) {
    return (this.x * other.x) + (this.y * other.y);
  }

  /**
   * scalarMultiply Returns this Vector scaled by the given scalar.
   */
  public Vector scalarMultiply(float scalar) {
    float result_x = this.x * scalar;
    float result_y = this.y * scalar;
    return new Vector(result_x, result_y);
  }

  /**
   * normalize Returns the normalized version of this Vector, a Vector with the
   * same angle with magnitude 1.
   */
  public Vector normalize() {
    float mag = this.getMagnitude();
    return new Vector(this.x / mag, this.y / mag);
  }

  // Manipulation functions
  // None. Vectors are immutable.

  public static void print(Vector z) {
    if (z.y < 0) {
      System.out.print("<" + z.x + ", " + z.y + ">");
    } else {
      System.out.print("<" + z.x + ", " + z.y + ">");
    }
  }
}
