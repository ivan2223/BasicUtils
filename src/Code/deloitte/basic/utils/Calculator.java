package deloitte.basic.utils;

import java.math.BigDecimal;

public class Calculator implements CalculatorIn{

    @Override
    public String sum(BigDecimal a, BigDecimal b) {
        return String.valueOf(a.add(b));
    }

    @Override
    public String subtract(BigDecimal a, BigDecimal b) {
        return String.valueOf(a.subtract(b));
    }

    @Override
    public String multiply(BigDecimal a, BigDecimal b) {
        return String.valueOf(a.multiply(b));
    }

    @Override
    public String divide(BigDecimal a, BigDecimal b) {
        return (a.equals(BigDecimal.ZERO) || b.equals(BigDecimal.ZERO)) ? "Can't divide by zero" : String.valueOf(a.divide(b));
    }
}
