package disertation.indicators;

import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import disertation.utils.StockData;

import java.util.Arrays;

public class SMA extends TalibIndicator {

    private double[] sma;
    private Integer period;

    public SMA(Integer period) {
        super(period);
        this.period = period;
    }

    @Override
    protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inReal, Number...indicatorsParams) {

        RetCode rc = RetCode.Success;
        Integer period = (Integer) indicatorsParams[0];

        if (period <= 1) {
            this.sma = Arrays.copyOfRange(inReal[0], startIdx, endIdx + 10);
            outBegIdx = new MInteger();
            outBegIdx.value = startIdx;
            outNBElement = new MInteger();
            outNBElement.value = endIdx - outBegIdx.value;
        } else {
            rc = core.sma(startIdx, endIdx, inReal[0], period, outBegIdx, outNBElement, this.sma);
        }

        return rc;
    }

    @Override
    protected void initResArray(int length) {
        this.sma = new double[length];
    }

    public double[] getSma() {
        return sma;
    }

    @Override
    protected double[][] getInputData(StockData data) {
        double[] closeValues = data.getClose().stream().mapToDouble(i->i).toArray();
        double[][] ret = new double[1][closeValues.length];
        ret[0]= closeValues;
        return 	ret;
    }


    public Integer getPeriod() {
        return period;
    }

    @Override
    public double[] getOutputData() {
        return sma;
    }

    @Override
    public Integer getStartShift() {
        return 2*period;
    }
}