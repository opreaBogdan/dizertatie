package disertation.utils;

import disertation.controller.PredictionJSON;
import com.tictactec.ta.lib.Core;
import disertation.indicators.SMA;
import org.apache.commons.lang3.ArrayUtils;
import org.omg.CORBA.DoubleSeqHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    public static PredictionJSON sma(StockData stockData, int windowSize) {
        SMA sma = new SMA(windowSize);

        sma.calculateIndicator(stockData, 0, stockData.getClose().size() - 1);

        List<Double> time = new ArrayList<>();
        for (int i = 0; i < sma.getOutputData().length; i++)
            time.add((double) i);

        PredictionJSON result = new PredictionJSON();
        result.setTime(time);
        Double[] doubleArray = ArrayUtils.toObject(sma.getOutputData());
        List<Double> resultList = new ArrayList(Arrays.asList(doubleArray));
        for (int i = 0; i < windowSize; i++)
            resultList.add(0, 0.0);
        result.setPredicted(resultList);

        return result;
    }

}
