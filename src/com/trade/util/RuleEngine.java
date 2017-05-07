package com.trade.util;

import static com.trade.util.TradeConstants.AED;
import static com.trade.util.TradeConstants.SAR;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trade.model.Trade;

public class RuleEngine {
	
	private final static Map<String, List<Integer>> differedCurrenciesWorkingDays;
	private final static List<Integer> defaultWorkingDays;
	
	static {
		differedCurrenciesWorkingDays = new HashMap<String, List<Integer>>() {{
			put(AED, Arrays.asList(Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY));
			put(SAR, Arrays.asList(Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY));
		}};
		
		defaultWorkingDays = Arrays.asList(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY);
	}
	
	public static boolean isTradePermittedOnSettlementDate(Trade trade) throws ParseException {
		if(trade == null || trade.getSettlementDate() == null) {
			return false;
		}
		
		int day_of_week = getDayOfWeek(trade);
		
		if(differedCurrenciesWorkingDays.containsKey(trade.getCurrencySymbol())) {
			List<Integer> list = differedCurrenciesWorkingDays.get(trade.getCurrencySymbol());
			if(list.contains(day_of_week)) {
				return true;
			}
		}
		else {
			if(defaultWorkingDays.contains(day_of_week)) {
				return true;
			}
		}
		return false;
	}
	
	public static void checkAndMakePermitableDateForTrade(Trade trade) throws ParseException {
		if(isTradePermittedOnSettlementDate(trade)) {
			return;
		}
		
		int day_of_week = getDayOfWeek(trade);
		switch(day_of_week) {
			case 6 : 
				incrementSettlementDateForTrade(trade, 2); // current is Friday, making it to Sunday
				break;
			case 1 : 
				incrementSettlementDateForTrade(trade, 1); // current is Sunday, making it to Monday
				break;
			case 7 :
				if(differedCurrenciesWorkingDays.containsKey(trade.getCurrencySymbol())) {
					incrementSettlementDateForTrade(trade, 1); // current is Saturday, making it to Sunday
				}
				else {
					incrementSettlementDateForTrade(trade, 2); // current is Saturday, making it to Monday
				}
				break;
		}
	}
	
	private static int getDayOfWeek(Trade trade) throws ParseException {
		Date settlementDate = trade.getSettlementDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(settlementDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	private static void incrementSettlementDateForTrade(Trade trade, int i) {
		Date settlementDate = trade.getSettlementDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(settlementDate);
		calendar.add(Calendar.DATE, i);
		trade.setSettlementDate(calendar.getTime());
	}
}