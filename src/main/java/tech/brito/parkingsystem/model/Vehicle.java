package tech.brito.parkingsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

import static tech.brito.parkingsystem.core.Constants.*;

@Getter
@Setter
@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String color;

    @NotBlank
    private String model;

    @NotBlank
    private String license;

    @NotNull
    @Column(name = "entry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT_UTC)
    private OffsetDateTime entryDate;

    @Column(name = "exit_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT_UTC)
    private OffsetDateTime exitDate;

    @Column(name = "bill_total")
    private BigDecimal billTotal;

    public void calculateBill() {
        var minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);

        if (minutes <= ONE_HOUR) {
            billTotal = ONE_HOUR_VALUE;
        } else if (minutes <= ONE_DAY) {
            calculateBillByHours(minutes);
        } else {
            calculateBillByDays(minutes);
        }
    }

    private void calculateBillByHours(Long minutes) {
        billTotal = ONE_HOUR_VALUE;
        int hours = (int) (minutes / ONE_HOUR);
        for (int i = 0; i < hours; i++) {
            billTotal = billTotal.add(ADDITIONAL_PER_HOUR_VALUE);
        }

        billTotal.setScale(2);
    }

    private void calculateBillByDays(Long minutes) {
        billTotal = DAY_VALUE;
        int days = (int) (minutes / ONE_DAY);
        for (int i = 1; i < days; i++) {
            billTotal = billTotal.add(DAY_VALUE);
        }

        billTotal.setScale(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass() || Objects.isNull(id)) {
            return false;
        }

        var vehicle = (Vehicle) o;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
