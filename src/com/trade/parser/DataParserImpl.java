package com.trade.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.trade.mapper.TradeDataMapper;
import com.trade.mapper.TradeDataMapperImpl;
import com.trade.model.Trade;

import static com.trade.util.TradeConstants.COMMA;

public class DataParserImpl implements DataParser {

	@Override
	public List<Trade> parse(List<String> input) throws ParseException {
		List<Trade> trades = new ArrayList<>();
		for(String s : input) {
			trades.add(parseInput(s));
		}
		return trades;
	}

	private Trade parseInput(String str) throws ParseException {
		String[] tokens = str.split(COMMA);
		TradeDataMapper dataMapper = new TradeDataMapperImpl();
		return dataMapper.mapDataToTradeObject(tokens);
	}
}
