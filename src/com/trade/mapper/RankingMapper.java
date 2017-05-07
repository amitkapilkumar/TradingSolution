package com.trade.mapper;

import java.util.ArrayList;
import java.util.List;

import com.trade.model.Ranking;
import com.trade.model.Trade;

public class RankingMapper {
	public static List<Ranking> mapToRankings(List<Trade> trades) {
		List<Ranking> rankings = new ArrayList<>();
		for(Trade trade : trades) {
			rankings.add(new Ranking(trade.getEntity(), trade.getInstructionDate(), 
					trade.getSettlementDate(), trade.getTradeAmount()));
		}
		return rankings;
	}
}
