package disertation.utils;

import disertation.controller.PredictionJSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prediction {

    private final static int PATH_NUMBER = 1000;

    public static PredictionJSON predict(StockData stockData) {
        Prediction p = new Prediction();

        List<Double> time = new ArrayList<>();
        List<Double> prediction = new ArrayList<>();
        List<Double> open = stockData.getOpen();
        List<Double> high = stockData.getHigh();
        List<Double> low = stockData.getLow();
        List<Double> close = stockData.getClose();

        int k = 0;
        for (; k < stockData.getOpen().size(); k++) {
            time.add((double) k);
            prediction.add((open.get(k) + high.get(k) + low.get(k) + close.get(k))/4);
        }

        double volatility = p.computeVolatility(open);
        Random r = new Random();
        double stockPrice = 0f;
        double return_rate = (open.get(open.size() - 1) - open.get(open.size() - 10))/ open.get(open.size() - 10);
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < PATH_NUMBER; i++) {
                double sigma = r.nextDouble();
                stockPrice += computeStockPrice(open.get(open.size() - 1), j/249f, sigma, volatility, return_rate);
            }

            stockPrice /= PATH_NUMBER;
            time.add((double) (j + k));
            prediction.add(stockPrice);
        }

        PredictionJSON result = new PredictionJSON();
        result.setTime(time);
        result.setPredicted(prediction);

        return result;
    }

    private static double computeStockPrice(double startValue, double i, double sigma, double volatility, double return_rate) {
        return startValue * Math.pow(Math.E, (return_rate - Math.pow(volatility, 2) / 2)* i + volatility * sigma * Math.sqrt(i));
    }

    private double computeVolatility(List<Double> series) {
        double result = 0;

        int n = series.size();

        double uMed = 0f;
        for (int i = 1; i < series.size(); i++) {
            double x = series.get(i) / series.get(i - 1);
            uMed += Math.log(x);
        }
        uMed /= n;

        double R = 0f;
        for (int i = 1; i < series.size(); i++) {
            double x = series.get(i) / series.get(i - 1);
            R = R + Math.pow(Math.log(x) - uMed, 2);
        }

        result = Math.sqrt((249f/n) * 1f/(n + 1) * R);
        return result;
    }
}
