/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.company.products;

/**
 *
 * @author mothi
 */
public class Products implements IProduct {
    private int[][] salesData;

    public Productsint[][] salesData) {
        this.salesData = salesData;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[] row : salesData) {
            for (int sale : row) {
                total += sale;
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold();
        int count = 0;
        for (int[] row : salesData) {
            count += row.length;
        }
        return (double) total / count;
    }

    @Override
    public String GetMostPopularProduct() {
       if (salesData.length == 0) return "None";
        int cols = salesData[0].length;
        int[] productSums = new int[cols];
        for (int[] row : salesData) {
            for (int j = 0; j < cols; j++) {
                productSums[j] += row[j];
            }
        }
        int maxSum = -1;
        int maxIndex = -1;
        for (int i = 0; i < cols; i++) {
            if (productSums[i] > maxSum) {
                maxSum = productSums[i];
                maxIndex = i;
            }
        }
        return "Product " + (maxIndex + 1);
    }
}   
