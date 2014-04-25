/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.itineramobile.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Moeda {

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("R$ ###,###,##0.00", REAL);

    public static String mascaraDinheiro(String valor, DecimalFormat moeda) {
        if (!valor.equals("")) {
            return moeda.format(desmascaraDinheiro(valor));
        } else {
            return "R$ 0,00";
        }
    }

    public static String mascaraDinheiro(double valor, DecimalFormat moeda) {
        return moeda.format(valor);
    }

    public static String mascaraDinheiro(BigDecimal valor, DecimalFormat moeda) {
        return moeda.format(valor);
    }

    public static String mascararDinheiro(BigDecimal valor) {
        //Formato de moeda atual do sistema
        return NumberFormat.getCurrencyInstance().format(valor.doubleValue());
    }

    public static double converterDoubleDoisDecimais(BigDecimal precoBigDecimal) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoBigDecimal);
        String[] part = string.split("[,]");
        String string2 = part[0] + "." + part[1];
        double preco = Double.parseDouble(string2);
        return preco;
    }

    public static Double desmascaraDinheiro(String valor) {
        try {
            valor = valor.replace("R$", "");
            valor = valor.replace(" ", "");
            valor = valor.replace(".", "");
            valor = valor.replace(",", ".");
            return (Double.valueOf(valor));
        } catch (NumberFormatException ex) {
            return new Double("0");
        }
    }
}