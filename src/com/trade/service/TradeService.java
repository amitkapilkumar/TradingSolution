package com.trade.service;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.trade.mapper.RankingMapper;
import com.trade.model.BuyVO;
import com.trade.model.Ranking;
import com.trade.model.SellVO;
import com.trade.model.Trade;
import com.trade.util.RuleEngine;
import com.trade.util.TradeUtility;

public class TradeService {

	public List<Trade> processTrades(List<Trade> trades) throws ParseException {
		for(Trade trade : trades) {
			RuleEngine.checkAndMakePermitableDateForTrade(trade);
		}
		return trades;
	}

	public List<SellVO> processEverydayIncoming(List<Trade> trades) throws ParseException {
		return TradeUtility.getSellVOs(trades);
	}

	public List<BuyVO> processEverydayOutgoing(List<Trade> trades) throws ParseException {
		return TradeUtility.getBuyVOs(trades);
	}

	public List<Ranking> getTopBuyCalls(List<Trade> trades) {
		trades = trades.stream().filter(trade -> trade.isBuy()).collect(Collectors.toList());
		return makeAndsortRanking(trades);
	}

	public List<Ranking> getTopSellCalls(List<Trade> trades) {
		trades = trades.stream().filter(trade -> !trade.isBuy()).collect(Collectors.toList());
		return makeAndsortRanking(trades);
	}
	
	private List<Ranking> makeAndsortRanking(List<Trade> trades) {
		List<Ranking> rankings = RankingMapper.mapToRankings(trades);
		Collections.sort(rankings, new Comparator<Ranking>() {
			@Override
			public int compare(Ranking r1, Ranking r2) {
				return Double.compare(r2.getAmount(), r1.getAmount());
			}
		});
		return rankings;
	}
}