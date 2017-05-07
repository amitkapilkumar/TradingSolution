package com.trade.parser;

import java.text.ParseException;
import java.util.List;

import com.trade.model.Trade;

public interface DataParser {
	List<Trade> parse(List<String> input) throws ParseException;
}
