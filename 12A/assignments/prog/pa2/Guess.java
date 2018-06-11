//-----------------------------------------------------------
// Guess.java
// Sinclair Liang
// wliang13
// pa2
// A program that let the user to guess a random int from 1 to 10
//-----------------------------------------------------------

import java.util.Scanner;

class Guess
{
   public static void main( String[] args )
   {
      int guess_time = 0;
      int hidden_number = (int)(Math.random() * (10 - 0) + 1); // generate random numbers
      //int hidden_number = 9;      
      Scanner sc = new Scanner(System.in);
      System.out.println("I'm thinking of an integer in the range 1 to 10. You"
      +"have three guesses.");
      do
      {
         if (guess_time == 0);
         {
            System.out.print("\nEnter your first guess: ");
            int guess = sc.nextInt();
            if (guess == hidden_number)
            {
               System.out.println("You win!");
               break;
            }
            else if (guess < hidden_number)
            {
               System.out.println("Your guess is too low.");
            }
            else if (guess > hidden_number)
            {
               System.out.println("Your guess is too high.");   
            }
         }
         if (guess_time == 1);
         {
            System.out.print("\nEnter your second guess: ");
            int guess = sc.nextInt();
            if (guess == hidden_number)
            {
               System.out.println("You win!");
               break;
            }
            else if (guess < hidden_number)
            {
               System.out.println("Your guess is too low.");
            }
            else if (guess > hidden_number)
            {
               System.out.println("Your guess is too high.");               
            }
         }
         if (guess_time == 2);
         {
            System.out.print("\nEnter your third guess: ");
            int guess = sc.nextInt();
            if (guess == hidden_number)
            {
               System.out.println("You win!");
               break;
            }
            else if (guess < hidden_number)
            {
               System.out.println("Your guess is too low.");
               System.out.println("\nYou lose. The number was " + hidden_number+ ".");
               break;
            }
            else if (guess > hidden_number)
            {
               System.out.println("Your guess is too high.");
               System.out.println("\nYou lose. The number was " + hidden_number+ ".");
               break;             
            }

         }
         guess_time++;
      }
      while (guess_time < 3);
   }
}
