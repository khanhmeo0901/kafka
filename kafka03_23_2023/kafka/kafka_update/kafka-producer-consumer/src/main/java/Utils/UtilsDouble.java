package Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UtilsDouble {
    public static String removeEFromNumber(double number) {
        if (String.valueOf(number).contains("E-")) {
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(25);
            return decimalFormat.format(number);
        }
        else if (String.valueOf(number).contains("E") && !String.valueOf(number).contains("-")) {
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
            return String.valueOf(bigDecimal.longValueExact());
        }
        return String.valueOf(number);
    }

    public static void main(String[] args) {

        System.out.println(  removeEFromNumber(124145E-6));
    }
}
