//-----------------------------------------------------------------------------
// Pair.java
// Illustrates Java Generics
//-----------------------------------------------------------------------------

@SuppressWarnings("overrides")
class Pair<T>{
   // fields
   private T first;
   private T second;
   
   // constructor
   Pair(T f, T s){first = f; second = s;} //constructor
   
   // ADT operations: getters and setters
   T getFirst(){return first;}
   T getSecond(){return second;}
   void setFirst(T f){first = f;}
   void setSecond(T s){second = s;}
   
   // override an Object method
   public String toString(){
      return "("+first.toString()+", "+second.toString()+")"; 
   }

   // override another Object method
   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs){
      boolean eq = false;
      Pair<T> R = null;
      
      if(this.getClass()==rhs.getClass()){
         R = (Pair<T>) rhs;
         eq = (this.first.equals(R.first) && this.second.equals(R.second));
      }
      return eq;
   }
   
}
