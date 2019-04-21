package disertation.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
	public final static LinkedHashMap<String, StockData> STOCK_DATA = new LinkedHashMap<>();
	static {
		STOCK_DATA.put("Apple", new StockData("AAPL"));
		STOCK_DATA.put("Amazon", new StockData("AMZN"));
		STOCK_DATA.put("AMD", new StockData("AMD"));
		STOCK_DATA.put("AT & T", new StockData("T"));
		STOCK_DATA.put("Alibaba Group Holding", new StockData("BABA"));
		STOCK_DATA.put("The Boeing Company", new StockData("BA"));
		STOCK_DATA.put("Bank of America", new StockData("BAC"));
		STOCK_DATA.put("Berry Petroleum", new StockData("BRY"));
		STOCK_DATA.put("Citigroup", new StockData("C"));
		STOCK_DATA.put("Constellation Pharmaceuticals", new StockData("CNST"));
		STOCK_DATA.put("DOMO", new StockData("DOMO"));
		STOCK_DATA.put("Endava", new StockData("DAVA"));
		STOCK_DATA.put("The Walt Disney Company", new StockData("DIS"));
		STOCK_DATA.put("Delta Airlines", new StockData("DAL"));
		STOCK_DATA.put("Eventbrite", new StockData("EB"));
		STOCK_DATA.put("Ford Motor", new StockData("F"));
		STOCK_DATA.put("Facebook", new StockData("FB"));
		STOCK_DATA.put("Fitbit", new StockData("FIT"));
		STOCK_DATA.put("General Electric", new StockData("GE"));
		STOCK_DATA.put("General Motors", new StockData("GM"));
		STOCK_DATA.put("Google", new StockData("GOOG"));
		STOCK_DATA.put("Hyatt Hotels", new StockData("H"));
		STOCK_DATA.put("The Home Depot", new StockData("HD"));
		STOCK_DATA.put("Intel", new StockData("INTC"));
		STOCK_DATA.put("IBM", new StockData("IBM"));
		STOCK_DATA.put("JP Morgan", new StockData("JPM"));
		STOCK_DATA.put("Johnson & Johnson", new StockData("JNJ"));
		STOCK_DATA.put("JetBlue", new StockData("JBLU"));
		STOCK_DATA.put("The Coca-Cola Companny", new StockData("KO"));
		STOCK_DATA.put("Lockhead Martin", new StockData("LMT"));
		STOCK_DATA.put("Macy’s", new StockData("M"));
		STOCK_DATA.put("Microsoft", new StockData("MSFT"));
		STOCK_DATA.put("Netflix", new StockData("NFLX"));
		STOCK_DATA.put("NVidia", new StockData("NVDA"));
		STOCK_DATA.put("Nokia", new StockData("NOK"));
		STOCK_DATA.put("Oracle", new StockData("ORCL"));
		STOCK_DATA.put("Pandora Media", new StockData("P"));
		STOCK_DATA.put("Tesla", new StockData("TSL"));
		STOCK_DATA.put("Visa", new StockData("V"));
		STOCK_DATA.put("Verizon", new StockData("VZ"));
		STOCK_DATA.put("Exxon Mobile", new StockData("XOM"));
		STOCK_DATA.put("Xilinx", new StockData("XLNX"));
		STOCK_DATA.put("Yelp", new StockData("YELP"));
		STOCK_DATA.put("ZenDesk", new StockData("ZEN"));
	}
}
