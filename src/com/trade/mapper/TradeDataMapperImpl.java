package com.trade.mapper;

import static com.trade.util.TradeConstants.BUY;
import static com.trade.util.TradeConstants.DATE_FORMATTER;

import java.text.ParseException;

import com.trade.model.Trade;

public class TradeDataMapperImpl implements TradeDataMapper {
	
	public Trade mapDataToTradeObject(String[] tokens) throws ParseException {
		Trade trade = new Trade();
		trade.setEntity(tokens[0].trim());
		trade.setBuy(BUY.equals(tokens[1].trim()));
		trade.setAgreedFX(Double.parseDouble(tokens[2].trim()));
		trade.setCurrencySymbol(tokens[3].trim());
		trade.setInstructionDate(DATE_FORMATTER.parse(tokens[4].trim()));
		trade.setSettlementDate(DATE_FORMATTER.parse(tokens[5].trim()));
		trade.setUnits(Long.parseLong(tokens[6].trim()));
		trade.setPricerPerUnit(Double.parseDouble(tokens[7].trim()));
		return trade;
	}
	
}