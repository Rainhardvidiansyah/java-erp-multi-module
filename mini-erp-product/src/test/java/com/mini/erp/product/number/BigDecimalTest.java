package com.mini.erp.product.number;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class BigDecimalTest {


    @Test
    void BigDecimalTestPrint(){

        BigDecimal bigDecimal = new BigDecimal("1000000.00");
        System.out.println(bigDecimal);
    }


    @Test
    void numberFormatTest(){
        BigDecimal bigDecimal = new BigDecimal("1000000.00");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("id", "ID"));
        String formattedPrice = numberFormat.format(bigDecimal);

        System.out.println(formattedPrice + ",-");
    }

    @Test
    void sumBigDecimal(){
        BigDecimal a = new BigDecimal("1000000.00");
        BigDecimal b = new BigDecimal("2000000.00");
        BigDecimal c = new BigDecimal("2");
        BigDecimal sum = a.add(b);
        BigDecimal multiply = a.multiply(b);
        BigDecimal divide = a.divide(c, 1);


        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("id", "ID"));

        String result = numberFormat.format(sum);
        String resultMultiply = numberFormat.format(multiply);
        String resultDividing = numberFormat.format(divide);

        System.out.println("FROM SUM: " + result);
        System.out.println("FROM MULTIPLY: " + resultMultiply);
        System.out.println("FROM DIVIDING: " + resultDividing);
    }
}
