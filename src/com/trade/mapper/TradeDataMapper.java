package com.trade.mapper;

import java.text.ParseException;

import com.trade.model.Trade;

public interface TradeDataMapper {
	Trade mapDataToTradeObject(String[] tokens) throws ParseException;
}
