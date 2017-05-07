package com.trade.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public interface TradeConstants {
	String AED = "AED";
	String SAR = "SAR";
	SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
	String BUY = "B";
	String SELL = "S";
	String COMMA = ",";
}
