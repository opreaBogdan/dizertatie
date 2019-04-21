package disertation.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PredictionJSON {

    @JsonProperty("time")
    public List<Double> real;
    @JsonProperty("predicted")
    public List<Double> predicted;

    public PredictionJSON() {
    }

    public void setTime(List<Double> real){
        this.real = real;
    }

    public List<Double> getReal() {
        return this.real;
    }

    public void setPredicted(List<Double> predicted) {
        this.predicted = predicted;
    }

    public List<Double> getPredicted() {
        return this.predicted;
    }
}
