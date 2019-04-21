package disertation.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyStockJSON {


    @JsonProperty("Meta Data")
    public double[] real;
    @JsonProperty("predicted")
    public double[] predicted;

    public DailyStockJSON() {
    }

    public void setReal(double[] real){
        this.real = real;
    }

    public double[] getReal() {
        return this.real;
    }

    public void setPredicted(double[] predicted) {
        this.predicted = predicted;
    }

    public double[] getPredicted() {
        return this.predicted;
    }
}
