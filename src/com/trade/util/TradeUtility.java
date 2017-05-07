package com.trade.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.trade.model.BuyVO;
import com.trade.model.SellVO;
import com.trade.model.Trade;

import static com.trade.util.TradeConstants.DATE_FORMATTER;

public class TradeUtility {
	public static List<SellVO> getSellVOs(List<Trade> trades) throws ParseException {
		List<Trade> sellTrades = trades.stream().filter(trade -> !trade.isBuy()).collect(Collectors.toList());
		
		Map<String, Double> map = groupAmountByDates(sellTrades);
		
		List<SellVO> sellVOs = new ArrayList<>();
		for(Entry<String, Double> entry : map.entrySet()) {
			SellVO sellVO = new SellVO();
			sellVO.setDate(DATE_FORMATTER.parse(entry.getKey()));
			sellVO.setAmount(entry.getValue());
			sellVOs.add(sellVO);
		}
		return sellVOs;
	}
	
	public static List<BuyVO> getBuyVOs(List<Trade> trades) throws ParseException {
		List<Trade> buyTrades = trades.stream().filter(trade -> trade.isBuy()).collect(Collectors.toList());
	
		Map<String, Double> map = groupAmountByDates(buyTrades);
		
		List<BuyVO> buyVOs = new ArrayList<>();
		for(Entry<String, Double> entry : map.entrySet()) {
			BuyVO buyVO = new BuyVO();
			buyVO.setDate(DATE_FORMATTER.parse(entry.getKey()));
			buyVO.setAmount(entry.getValue());
			buyVOs.add(buyVO);
		}
		return buyVOs;
	}

	private static Map<String, Double> groupAmountByDates(List<Trade> buyTrades) {
		Map<String, Double> map = new HashMap<>();
		for(Trade trade : buyTrades) {
			String date = DATE_FORMATTER.format(trade.getSettlementDate());
			if(map.containsKey(date)) {
				map.put(date, trade.getTradeAmount() + map.get(date));
			}
			else {
				map.put(date, trade.getTradeAmount());
			}
		}
		return map;
	}
}
