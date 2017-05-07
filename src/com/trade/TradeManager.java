package com.trade;

import java.text.ParseException;
import java.util.List;

import com.trade.model.BuyVO;
import com.trade.model.Ranking;
import com.trade.model.SellVO;
import com.trade.model.Trade;
import com.trade.parser.DataParser;
import com.trade.parser.DataParserImpl;
import com.trade.service.TradeService;

public class TradeManager {
	private DataParser dataParser;
	private TradeService tradeService;
	
	public TradeManager() {
		dataParser = new DataParserImpl();
		tradeService = new TradeService();
	}
	
	public List<Trade> processTradeData(List<String> inputList) throws ParseException {
		return tradeService.processTrades(dataParser.parse(inputList));
	}
	
	public List<SellVO> getSettledAmountforEverydayIncoming(List<Trade> trades) throws ParseException {
		return tradeService.processEverydayIncoming(trades);
	}
		
	public List<BuyVO> getSettledAmountforEverydayOutgoing(List<Trade> trades) throws ParseException {
		return tradeService.processEverydayOutgoing(trades);
	}
	
	public List<Ranking> getTopBuyCalls(List<Trade> trades) {
		return tradeService.getTopBuyCalls(trades);
	}
	
	public List<Ranking> getTopSellCalls(List<Trade> trades) {
		return tradeService.getTopSellCalls(trades);
	}

	public DataParser getDataParser() {
		return dataParser;
	}
	
	public void setDataParser(DataParser dataParser) {
		this.dataParser = dataParser;
	}
	
	public TradeService getTradingService() {
		return tradeService;
	}
	
	public void setTradingService(TradeService tradeService) {
		this.tradeService = tradeService;
	}
	
}
