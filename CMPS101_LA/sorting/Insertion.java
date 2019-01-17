/*

INSERTION-SORT (A)
1  for j <- 2 to length[A]
2       do key <- A[j]
3         Insert A[j] into the sorted sequence A[1 . . j - 1].
4        i <- j - 1
5        while i > 0 and A[i] > key
6           do A[i + 1] <- A[i]
7              i <- i - 1
8        A[i + 1] <- key

Runtime: O(n^2)
*/

// Java program for implementation of Insertion Sort 
class InsertionSort {
    /* Function to sort array using insertion sort */
    void sort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /*
             * Move elements of arr[0..i-1], that are greater than key, to one position
             * ahead of their current position
             */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    // Driver method
    public static void main(String args[]) {
        int arr[] = { 12, 11, 13, 5, 6 };

        InsertionSort ob = new InsertionSort();
        ob.sort(arr);

        printArray(arr);
    }
} /* This code is contributed by Rajat Mishra. */
