//-----------------------------------------------------------------------------
// PairTest.java
// Test the Pair ADT
//-----------------------------------------------------------------------------

class PairTest{
   public static void main(String[] args){
      Pair<String> ps = new Pair<String>("happy", "sad");
      Pair<Double> pd = new Pair<Double>(2.5, 5.7);
      
      System.out.println(ps);
      System.out.println(pd);
      ps.setFirst("very");
      pd.setSecond(-3.4);
      System.out.println(ps);
      System.out.println(pd);

      Pair<String> ps2 = new Pair<String>("one", "two");
      Pair<Pair<String> > pps = new Pair<Pair<String> >(ps, ps2);
      System.out.println(pps);
      
      Pair<String> ps3 = new Pair<String>("very", "sad");
      System.out.println(ps.equals(pd));
      System.out.println(ps.equals(ps2));
      System.out.println(ps.equals(ps3));
      System.out.println(ps.equals(pps));
   }
}
