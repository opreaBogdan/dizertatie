package disertation.utils;

import java.util.ArrayList;
import java.util.List;

public class StockData {
    private String symbol;
    private List<Double> open;
    private List<Double> high;
    private List<Double> low;
    private List<Double> close;
    private double volatility;

    StockData (String symbol) {
        this.symbol = symbol;
        this.open = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Double> getOpen() {
        return open;
    }

    public void setOpen(List<Double> open) {
        this.open = open;
    }

    public List<Double> getHigh() {
        return high;
    }

    public void setHigh(List<Double> high) {
        this.high = high;
    }

    public List<Double> getLow() {
        return low;
    }

    public void setLow(List<Double> low) {
        this.low = low;
    }

    public List<Double> getClose() {
        return close;
    }

    public void setClose(List<Double> close) {
        this.close = close;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }
}
