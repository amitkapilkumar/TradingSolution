package com.trade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.trade.builder.BuyVOBuilder;
import com.trade.builder.RankingBuilder;
import com.trade.builder.SellVOBuilder;
import com.trade.builder.TradeBuilder;
import com.trade.model.BuyVO;
import com.trade.model.Ranking;
import com.trade.model.SellVO;
import com.trade.model.Trade;
import com.trade.parser.DataParser;
import com.trade.parser.DataParserImpl;
import com.trade.service.TradeService;

import junit.framework.Assert;


public class TradeManagerTest {
	private DataParser dataParser;
	private TradeService tradeService;
	
	private TradeManager tradeManager;
	
	private List<String> list;
	private List<Trade> expectedList;
	private List<SellVO> sellVOs;
	private List<BuyVO> buyVOs;
	private List<Ranking> topBuyers;
	private List<Ranking> topSellers;
	
	@Before
	public void setup() {
		dataParser = mock(DataParserImpl.class);
		tradeService = mock(TradeService.class);
		tradeManager = new TradeManager();
		tradeManager.setDataParser(dataParser);
		tradeManager.setTradingService(tradeService);
		
		list = new ArrayList<String>() {{
			add("foo, B, 0.50, SGP, 01 Jan 2016, 02 Jan 2016, 200, 100.25");
			add("bar, S, 0.22, AED, 05 Jan 2016, 07 Jan 2016, 450, 150.5");
			add("bar, S, 0.24, AED, 04 Jan 2016, 07 Jan 2016, 250, 160.5");
			add("tbay, S, 0.63, SAR, 01 Feb 2016, 02 Feb 2016, 700, 421.24");
			add("haxon, B, 0.72, GBP, 23 Feb 2016, 27 Feb 2016, 900, 567.58");
			add("dripkart, B, 0.30, INR, 09 March 2016, 11 March 2016, 1000, 142.27");
			add("munez-corp, S, 0.780, AED, 23 March 2016, 25 March 2016, 350, 680.39");
		}};
		
		try {
			expectedList = new ArrayList<Trade>() {{
				add(new TradeBuilder().withEntity("foo").withSetBuy(true).withAgreedFX(0.50).withCurrencySymbol("SGP")
						.withInstructionDate("01 Jan 2016").withSettlementDate("02 Jan 2016").withUnits(200)
						.withPricePerUnit(100.25).build());
				add(new TradeBuilder().withEntity("bar").withSetBuy(false).withAgreedFX(0.22).withCurrencySymbol("AED")
						.withInstructionDate("05 Jan 2016").withSettlementDate("07 Jan 2016").withUnits(450)
						.withPricePerUnit(150.5).build());
				add(new TradeBuilder().withEntity("bar").withSetBuy(false).withAgreedFX(0.24).withCurrencySymbol("AED")
						.withInstructionDate("04 Jan 2016").withSettlementDate("07 Jan 2016").withUnits(250)
						.withPricePerUnit(160.5).build());
				add(new TradeBuilder().withEntity("tbay").withSetBuy(false).withAgreedFX(0.63).withCurrencySymbol("SAR")
						.withInstructionDate("01 Feb 2016").withSettlementDate("02 Feb 2016").withUnits(700)
						.withPricePerUnit(421.24).build());
				add(new TradeBuilder().withEntity("haxon").withSetBuy(true).withAgreedFX(0.72).withCurrencySymbol("GBP")
						.withInstructionDate("23 Feb 2016").withSettlementDate("27 Feb 2016").withUnits(900)
						.withPricePerUnit(567.58).build());
				add(new TradeBuilder().withEntity("dripkart").withSetBuy(true).withAgreedFX(0.30).withCurrencySymbol("INR")
						.withInstructionDate("09 March 2016").withSettlementDate("11 March 2016").withUnits(1000)
						.withPricePerUnit(142.27).build());
				add(new TradeBuilder().withEntity("munez-corp").withSetBuy(false).withAgreedFX(0.78).withCurrencySymbol("AED")
						.withInstructionDate("23 March 2016").withSettlementDate("25 March 2016").withUnits(350)
						.withPricePerUnit(680.39).build());
			}};
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
			sellVOs = new ArrayList<SellVO>() {{
				add(new SellVOBuilder().withDate(sdf.parse("27 Mar 2016")).withAmount(185746.47).build());
				add(new SellVOBuilder().withDate(sdf.parse("02 Feb 2016")).withAmount(185766.84000000003).build());
				add(new SellVOBuilder().withDate(sdf.parse("07 Jan 2016")).withAmount(24529.5).build());
			}};
			
			buyVOs = new ArrayList<BuyVO>() {{
				add(new BuyVOBuilder().withDate(sdf.parse("04 Jan 2016")).withAmount(10025.0).build());
				add(new BuyVOBuilder().withDate(sdf.parse("29 Feb 2016")).withAmount(367791.84).build());
				add(new BuyVOBuilder().withDate(sdf.parse("11 Mar 2016")).withAmount(42681.00000000001).build());
			}};
			
			topBuyers = new ArrayList<Ranking>() {{
				add(new RankingBuilder().withEntity("haxon").withAmount(367791.84)
						.withInstructionDate(sdf.parse("23 Feb 2016")).withSettlementDate(sdf.parse("29 Feb 2016")).build());
				add(new RankingBuilder().withEntity("dripkart").withAmount(42681.00000000001)
						.withInstructionDate(sdf.parse("09 Mar 2016")).withSettlementDate(sdf.parse("11 Mar 2016")).build());
				add(new RankingBuilder().withEntity("foo").withAmount(10025.0)
						.withInstructionDate(sdf.parse("01 Jan 2016")).withSettlementDate(sdf.parse("04 Jan 2016")).build());
			}};
			
			topSellers = new ArrayList<Ranking>() {{
				add(new RankingBuilder().withEntity("tbay").withAmount(185766.84000000003)
						.withInstructionDate(sdf.parse("01 Feb 2016")).withSettlementDate(sdf.parse("02 Feb 2016")).build());
				add(new RankingBuilder().withEntity("munez-corp").withAmount(185746.47)
						.withInstructionDate(sdf.parse("23 Mar 2016")).withSettlementDate(sdf.parse("27 Mar 2016")).build());
				add(new RankingBuilder().withEntity("bar").withAmount(14899.5)
						.withInstructionDate(sdf.parse("05 Jan 2016")).withSettlementDate(sdf.parse("07 Jan 2016")).build());
				add(new RankingBuilder().withEntity("bar").withAmount(9629.999999999998)
						.withInstructionDate(sdf.parse("04 Jan 2016")).withSettlementDate(sdf.parse("07 Jan 2016")).build());
			}};
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testProcessDataCall() {
		try {
			when(dataParser.parse(list)).thenReturn(expectedList);
			when(tradeService.processTrades(expectedList)).thenReturn(expectedList);
			List<Trade> actualList = tradeManager.processTradeData(list);
			
			verify(dataParser).parse(list);
			verify(tradeService).processTrades(expectedList);
			
			Assert.assertEquals(actualList.size(), expectedList.size());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetSettledAmountforEverydayIncoming() {
		try {
			when(tradeService.processEverydayIncoming(expectedList)).thenReturn(sellVOs);
			List<SellVO> actualList = tradeManager.getSettledAmountforEverydayIncoming(expectedList);
			
			Assert.assertEquals(actualList.size(), sellVOs.size());
			Assert.assertEquals(actualList.get(0).getAmount(), sellVOs.get(0).getAmount());
			Assert.assertEquals(actualList.get(1).getAmount(), sellVOs.get(1).getAmount());
			Assert.assertEquals(actualList.get(2).getAmount(), sellVOs.get(2).getAmount());
		} catch (ParseException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetSettledAmountforEverydayOutgoing() {
		try {
			when(tradeService.processEverydayOutgoing(expectedList)).thenReturn(buyVOs);
			List<BuyVO> actualList = tradeManager.getSettledAmountforEverydayOutgoing(expectedList);
			
			Assert.assertEquals(actualList.size(), buyVOs.size());
			Assert.assertEquals(actualList.get(0).getAmount(), buyVOs.get(0).getAmount());
			Assert.assertEquals(actualList.get(1).getAmount(), buyVOs.get(1).getAmount());
			Assert.assertEquals(actualList.get(2).getAmount(), buyVOs.get(2).getAmount());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTopBuyersRanking() {
		when(tradeService.getTopBuyCalls(expectedList)).thenReturn(topBuyers);
		List<Ranking> actualRanking = tradeManager.getTopBuyCalls(expectedList);
		
		Assert.assertEquals(actualRanking.size(), topBuyers.size());
		Assert.assertEquals(actualRanking.get(0).getEntity(), topBuyers.get(0).getEntity());
		Assert.assertEquals(actualRanking.get(1).getEntity(), topBuyers.get(1).getEntity());
		Assert.assertEquals(actualRanking.get(2).getEntity(), topBuyers.get(2).getEntity());
	}
	
	@Test
	public void testTopSellersRanking() {
		when(tradeService.getTopSellCalls(expectedList)).thenReturn(topSellers);
		List<Ranking> actualRanking = tradeManager.getTopSellCalls(expectedList);
		
		Assert.assertEquals(actualRanking.size(), topSellers.size());
		Assert.assertEquals(actualRanking.get(0).getEntity(), topSellers.get(0).getEntity());
		Assert.assertEquals(actualRanking.get(1).getEntity(), topSellers.get(1).getEntity());
		Assert.assertEquals(actualRanking.get(2).getEntity(), topSellers.get(2).getEntity());
		Assert.assertEquals(actualRanking.get(3).getEntity(), topSellers.get(3).getEntity());
	}

}
