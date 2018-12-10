import java.util.Arrays;

import javax.lang.model.util.ElementScanner6;

public class BinarySearch
{
    public static int rank(int key, int[] a)
    {
        // assuming the array is already sorted;
        int low = 0;
        int high = a.length - 1;
        while(low <= high)
        {
            int mid = low + (high - low)/2;
            if (key < a[mid]) 
            {
                high = mid - 1;
            }
            else if (key > a[mid]) 
            {
                low = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty())
        {
            int key = StdIn.readInt();
            if(rank(key, whitelist) < 0)
            {
                System.out.println(key);
            }
        }
    }
}