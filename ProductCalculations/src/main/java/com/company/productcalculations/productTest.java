/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.productcalculations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author mothi
 */
public class productTest {
    
@Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20}, {15, 10, 12}, {18, 15, 20}};

        Product product = new Product(products, sales);

        assertEquals(153, product.GetTotalProductsSold());
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20}, {15, 10, 12}, {18, 15, 20}};

        Product product = new Product(products, sales);

        assertEquals(17.0, product.GetAverageProductSold(), 0.01);
    }
}