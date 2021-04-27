package com.matrix.freshmarket.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class RangeSlider {
    @JsonProperty("min")
     String min;
    @JsonProperty("max")
      String max;


    public void setMin(String min) {
        this.min = min;
    }

    public void setMax(String max) {
        this.max = max;
    }


    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }
}
