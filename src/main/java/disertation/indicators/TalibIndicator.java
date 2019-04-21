package disertation.indicators; /**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares,
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios.
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * <p>
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * <p>
 * This file is part of Premium Markets.
 * <p>
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import disertation.utils.StockData;

public abstract class TalibIndicator {

    protected MInteger outBegIdx = new MInteger();
    protected MInteger outNBElement = new MInteger();
    protected Core core = new Core();

    private Number[] indicatorParams;


    protected TalibIndicator(Number... indicatorParams) {
        this.indicatorParams = indicatorParams;
    }

    public void calculateIndicator(StockData data, int start, int end) {

        RetCode rc = RetCode.InternalError;
        Integer startIdx = start == -1 ? 0 : start;
        Integer endIdx = end == -1 ? data.getOpen().size() : end;
        initResArray(endIdx - startIdx + 1);

        double[][] inData = getInputData(data);
        rc = talibCall(startIdx, endIdx, inData, indicatorParams);
    }

    protected abstract double[][] getInputData(StockData data);

    protected abstract void initResArray(int length);

    public abstract Integer getStartShift();

    protected abstract RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams);

    public MInteger getOutBegIdx() {
        return outBegIdx;
    }

    public MInteger getOutNBElement() {
        return outNBElement;
    }

    public abstract double[] getOutputData();
}