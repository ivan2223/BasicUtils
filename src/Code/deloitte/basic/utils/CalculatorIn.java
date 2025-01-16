package deloitte.basic.utils;

import java.math.BigDecimal;

public interface CalculatorIn {

    String sum(BigDecimal a, BigDecimal b);
    String subtract(BigDecimal a, BigDecimal b);
    String multiply(BigDecimal a, BigDecimal b);
    String divide(BigDecimal a, BigDecimal b);

}
