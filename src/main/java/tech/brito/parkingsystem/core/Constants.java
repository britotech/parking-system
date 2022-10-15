package tech.brito.parkingsystem.core;

import java.math.BigDecimal;

public class Constants {

    public static final String DATE_TIME_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final int ONE_HOUR = 60;
    public static final int ONE_DAY = 24 * ONE_HOUR;
    public static final BigDecimal ONE_HOUR_VALUE = new BigDecimal(5.00);
    public static final BigDecimal ADDITIONAL_PER_HOUR_VALUE = new BigDecimal(2.00);
    public static final BigDecimal DAY_VALUE = new BigDecimal(20.00);

    public static final String MSG_VEHICLE_NOT_FOUND = "vehicle not found.";
}
