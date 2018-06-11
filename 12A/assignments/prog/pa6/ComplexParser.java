//-----------------------------------------------------------------------------
// ComplexParser.java
// 
// You may use the static function parseComplex() below in pa6 to write the
// Complex constructor from a String argument.  you can add it directly to
// your Complex class as a private static function and call it from within
// the constructor.  It returns a double array of length 2 having the real
// part of its argument in index [0] and imaginary part in index [1]. 
//
// parseComplex() will accept input strings of the following types.
// "3.5+4.2i"
// "-4 - 7i"
// "8"
// "-9.3i"
// "1+i"
// "i"
//
// parseComplex() will reject input strings of the following types, throwing 
// a NumberFormatException.
// "3+4 i"
// "2i+1"
// "- 4-7i"
// "5+-6i"
//
//-----------------------------------------------------------------------------


class ComplexParser{

   public static void main(String[] args){
      String str =  "3.5+4.2i";
      double[] C = parseComplex(str);
      System.out.println("Re("+str+") = "+C[0]);  // real part
      System.out.println("Im("+str+") = "+C[1]);  // imaginary part
   }
   
   // parseComplex()
   // Returns a double[] of length 2 containing (real, imaginary) parts
   // of a complex number represented by string argument str.  Throws a
   // NumberFormatException if str cannot be parsed as a complex number.
   static double[] parseComplex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      return part;
   }
   
}

