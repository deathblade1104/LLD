package Utils;

import java.util.ArrayList;

public class CustomUtils {
  void swapInIntArray(int[] a, int x, int y) {
    int temp = a[x];
    a[x] = a[y];
    a[y] = temp;
    return;
  }

  <T> void swapInList(ArrayList<T> a, int x, int y) {
    T temp = a.get(x);
    a.set(x, a.get(y));
    a.set(y, temp);
    return;
  }

  void reverseInIntArray(int[] a, int l, int h) {
    while (l < h) {
      swapInIntArray(a, l++, h--);
    }
  }

  <T> void reverseInList(ArrayList<T> a, int l, int h) {
    while (l < h) {
      swapInList(a, l++, h--);
    }
  }

  void printIntArray(int[] a) {
    for (int ele : a) {
      System.out.print(ele + " ");
    }
    System.out.println();
  }

  <T> void printArrayList(ArrayList<T> a) {
    for (T ele : a) {
      System.out.print(ele + " ");
    }
    System.out.println();
  }

  <T extends Comparable<T>> boolean nextPermutation(ArrayList<T> arr) {
    int n = arr.size(), pivot = -1;
    for (int i = n - 2; i >= 0; i--) {
      if (arr.get(i).compareTo(arr.get(i + 1)) < 0) {
        pivot = i;
        break;
      }
    }

    if (pivot == -1) {
      return false;
    }

    int swapIndex = -1;
    for (int i = n - 1; i >= 0; i--) {
      if (arr.get(pivot).compareTo(arr.get(i)) < 0) {
        swapIndex = i;
        break;
      }
    }

    swapInList(arr, swapIndex, pivot);
    reverseInList(arr, pivot + 1, n - 1);
    return true;
  }
}
