package csv.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import csv.deserializer.OdPeriodDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonPropertyOrder(value = {"jackson", "odPeriod"})
public class OdNoHst extends Od {

    @JsonDeserialize(using = OdPeriodDeserializer.class)
    private OdPeriod odPeriod;

}