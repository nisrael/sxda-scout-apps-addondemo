/*
 * Copyright (c) 2010-2025 BSI Business Systems Integration AG
 * Copyright (c) 2023-2025 Nils Israel
 *
 * This program is an extension of the original work from the Eclipse Scout Project,
 * available at https://www.eclipse.org/scout/.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package io.sxda.scout.apps.addondemo.server.codemirror;

import io.sxda.scout.apps.addondemo.shared.codemirror.CodeMirrorFormData;
import io.sxda.scout.apps.addondemo.shared.codemirror.ICodeMirrorService;

/**
 * @author nisrael
 */
public class CodeMirrorService implements ICodeMirrorService {

  @Override
  public CodeMirrorFormData load(CodeMirrorFormData input) {
    input.getCodeMirror().setValue("""
      // source: https://www.geeksforgeeks.org/dsa/minimum-swaps-reach-permuted-array-2-positions-left-swaps-allowed/
      //
      // Java program to find minimum\s
      // number of swaps to reach a\s
      // permutation with at most 2 left\s
      // swaps allowed for every element\s
      import java.io.*;
      public class GFG
      {

          /* This function merges two sorted
          arrays and returns inversion\s
          count in the arrays.*/
          static int merge(int arr[], int temp[], int left,
                                      int mid, int right)
          {
              int inv_count = 0;

              int i = left;

              /* i is index for left subarray*/
              int j = mid;

              /* j is index for right subarray*/
              int k = left;

              /* k is index for resultant merged subarray*/
              while ((i <= mid - 1) && (j <= right))\s
              {
                  if (arr[i] <= arr[j])
                  {
                      temp[k++] = arr[i++];
                  }\s
                  else
                  {
                      temp[k++] = arr[j++];
                      inv_count = inv_count + (mid - i);
                  }
              }

              /* Copy the remaining elements\s
              of left subarray (if there
               are any) to temp*/
              while (i <= mid - 1)\s
              {
                  temp[k++] = arr[i++];
              }

              /* Copy the remaining elements\s
              of right subarray (if there
              are any) to temp*/
              while (j <= right)
              {
                  temp[k++] = arr[j++];
              }

              /* Copy back the merged elements
              to original array*/
              for (i = left; i <= right; i++)\s
              {
                  arr[i] = temp[i];
              }

              return inv_count;
          }

          /* An auxiliary recursive function\s
           that sorts the input array and
           returns the number of inversions\s
          in the array. */
          static int _mergeSort(int arr[], int temp[],\s
                                  int left, int right)
          {
              int mid, inv_count = 0;
              if (right > left)\s
              {
                  /* Divide the array into two parts and\s
                  call _mergeSortAndCountInv() for each\s
                  of the parts */
                  mid = (right + left) / 2;

                  /* Inversion count will be sum of inversions\s
                  in left-part, right-part and number of inversions\s
                  in merging */
                  inv_count = _mergeSort(arr, temp, left, mid);
                  inv_count += _mergeSort(arr, temp, mid + 1, right);

                  /* Merge the two parts*/
                  inv_count += merge(arr, temp, left, mid + 1, right);
              }
              return inv_count;
          }


          /* This function sorts the input array and returns the\s
          number of inversions in the array */
          static int mergeSort(int arr[], int array_size)
          {
              int[] temp = new int[array_size];
              return _mergeSort(arr, temp, 0, array_size - 1);
          }

          // method returns minimum number of \s
          // swaps to reach permuted array 'arr'\s
          static int minSwapToReachArr(int arr[], int N)\s
          {
              // loop over all elements to check Invalid\s
              // permutation condition\s
              for (int i = 0; i < N; i++)
              {
                  /* if an element is at distance more than 2\s
                  from its actual position then it is not\s
                  possible to reach permuted array just\s
                  by swapping with 2 positions left elements\s
                  so returning -1 */
                  if ((arr[i] - 1) - i > 2)
                  {
                      return -1;
                  }
              }

              /* If permuted array is not Invalid, then number\s
              of Inversion in array will be our final answer */
              int numOfInversion = mergeSort(arr, N);
              return numOfInversion;
          }

          // Driver code\s
          public static void main(String[] args)\s
          {

              // change below example\s
              int arr[] = {1, 2, 5, 3, 4};
              int N = arr.length;
              int res = minSwapToReachArr(arr, N);
              System.out.println(res == -1 ? "Not Possible\\n" : res);
          }
      }

      // This code contributed by Rajput-Ji
    """);
    return input;
  }
}
