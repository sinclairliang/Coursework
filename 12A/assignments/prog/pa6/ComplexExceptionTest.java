class ComplexExceptionTest {
   public static void main (String args[]) {
      String str = "div exception test ";
      String str2 = "FAILED";
      try {
         Complex a = new Complex(356, 5.65);
         Complex b = a.div(Complex.ZERO);
      } catch (ArithmeticException a) {
         str2 = "PASSED";
      } catch (Exception e) {
      }
      System.out.println(str + str2);

      str = "recip exception test ";
      str2 = "FAILED";
      try {
         Complex a = Complex.ZERO;
         Complex b = a.div(Complex.ZERO);
      } catch (ArithmeticException a) {
         str2 = "PASSED";
      } catch (Exception e) {
      }
      System.out.println(str + str2);
   }
}
