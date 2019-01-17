/*
procedure selection sort 
   list  : array of items
   n     : size of list

   for i = 1 to n - 1
   (set current element as minimum)
   min = i    
  
   (check the element to be minimum )

   for j = i+1 to n 
      if list[j] < list[min] then
         min = j;
      end if
   end for

   (swap the minimum element with the current element)
   if indexMin != i  then
      swap list[min] and list[i]
   end if
end for
 
end procedure


Runtime: O(n^2)
*/

// Java program for implementation of Selection Sort 
class SelectionSort {
    void sort(int arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    // Prints the array
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code to test above
    public static void main(String args[]) {
        SelectionSort ob = new SelectionSort();
        int arr[] = { 64, 25, 12, 22, 11 };
        ob.sort(arr);
        System.out.println("Sorted array");
        ob.printArray(arr);
    }
}
/* This code is contributed by Rajat Mishra */