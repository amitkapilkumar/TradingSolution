package com.trade.service;

import static org.junit.Assert.*;

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

import junit.framework.Assert;

public class TradeServiceTest {
	
	private List<Trade> trades;
	private List<SellVO> sellVOs;
	private List<BuyVO> buyVOs;
	private List<Ranking> topBuyers;
	private List<Ranking> topSellers;
	
	private TradeService tradeService;
	
	@Before
	public void setup() {
		tradeService = new TradeService();
		try {
			trades = new ArrayList<Trade>() {{
				add(new TradeBuilder().withEntity("foo").withSetBuy(true).withAgreedFX(0.50).withCurrencySymbol("SGP")
						.withInstructionDate("01 Jan 2016").withSettlementDate("04 Jan 2016").withUnits(200)
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
						.withInstructionDate("23 Feb 2016").withSettlementDate("29 Feb 2016").withUnits(900)
						.withPricePerUnit(567.58).build());
				add(new TradeBuilder().withEntity("dripkart").withSetBuy(true).withAgreedFX(0.30).withCurrencySymbol("INR")
						.withInstructionDate("09 March 2016").withSettlementDate("11 March 2016").withUnits(1000)
						.withPricePerUnit(142.27).build());
				add(new TradeBuilder().withEntity("munez-corp").withSetBuy(false).withAgreedFX(0.78).withCurrencySymbol("AED")
						.withInstructionDate("23 March 2016").withSettlementDate("27 March 2016").withUnits(350)
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testProcessTrades() {
		try {
			List<Trade> actual = tradeService.processTrades(trades);
			
			Assert.assertEquals(trades.size(), actual.size());
			Assert.assertEquals(trades.get(0), actual.get(0));
			Assert.assertEquals(trades.get(1), actual.get(1));
			Assert.assertEquals(trades.get(2), actual.get(2));
			Assert.assertEquals(trades.get(3), actual.get(3));
			Assert.assertEquals(trades.get(4), actual.get(4));
			Assert.assertEquals(trades.get(5), actual.get(5));
			Assert.assertEquals(trades.get(6), actual.get(6));
			
			Assert.assertEquals(trades.get(0).getSettlementDate().compareTo(actual.get(0).getSettlementDate()), 0);
			Assert.assertEquals(trades.get(1).getSettlementDate().compareTo(actual.get(1).getSettlementDate()), 0);
			Assert.assertEquals(trades.get(2).getSettlementDate().compareTo(actual.get(2).getSettlementDate()), 0);
			Assert.assertEquals(trades.get(3).getSettlementDate().compareTo(actual.get(3).getSettlementDate()), 0);
			Assert.assertEquals(trades.get(4).getSettlementDate().compareTo(actual.get(4).getSettlementDate()), 0);
			Assert.assertEquals(trades.get(5).getSettlementDate().compareTo(actual.get(5).getSettlementDate()), 0);
			Assert.assertEquals(trades.get(6).getSettlementDate().compareTo(actual.get(6).getSettlementDate()), 0);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testProcessEverydayIncoming() {
		try {
			List<SellVO> actual = tradeService.processEverydayIncoming(trades);
			
			Assert.assertEquals(actual.size(), sellVOs.size());
			Assert.assertEquals(sellVOs.get(0).getDate().compareTo(actual.get(0).getDate()), 0);
			Assert.assertEquals(sellVOs.get(1).getDate().compareTo(actual.get(1).getDate()), 0);
			Assert.assertEquals(sellVOs.get(2).getDate().compareTo(actual.get(2).getDate()), 0);
			Assert.assertEquals(sellVOs.get(0).getAmount(), actual.get(0).getAmount());
			Assert.assertEquals(sellVOs.get(1).getAmount(), actual.get(1).getAmount());
			Assert.assertEquals(sellVOs.get(2).getAmount(), actual.get(2).getAmount());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testProcessEverydayOutgoing() {
		try {
			List<BuyVO> actual = tradeService.processEverydayOutgoing(trades);
			
			Assert.assertEquals(actual.size(), buyVOs.size());
			Assert.assertEquals(buyVOs.get(0).getDate().compareTo(actual.get(0).getDate()), 0);
			Assert.assertEquals(buyVOs.get(1).getDate().compareTo(actual.get(1).getDate()), 0);
			Assert.assertEquals(buyVOs.get(2).getDate().compareTo(actual.get(2).getDate()), 0);
			Assert.assertEquals(buyVOs.get(0).getAmount(), actual.get(0).getAmount());
			Assert.assertEquals(buyVOs.get(1).getAmount(), actual.get(1).getAmount());
			Assert.assertEquals(buyVOs.get(2).getAmount(), actual.get(2).getAmount());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTopBuyCalls() {
		List<Ranking> actual = tradeService.getTopBuyCalls(trades);
		
		Assert.assertEquals(topBuyers.size(), actual.size());
		
		Assert.assertEquals(topBuyers.get(0).getEntity(), actual.get(0).getEntity());
		Assert.assertEquals(topBuyers.get(0).getAmount(), actual.get(0).getAmount());
		Assert.assertEquals(topBuyers.get(0).getInstructionDate().compareTo(actual.get(0).getInstructionDate()), 0);
		Assert.assertEquals(topBuyers.get(0).getSettlementDate().compareTo(actual.get(0).getSettlementDate()), 0);
		
		Assert.assertEquals(topBuyers.get(1).getEntity(), actual.get(1).getEntity());
		Assert.assertEquals(topBuyers.get(1).getAmount(), actual.get(1).getAmount());
		Assert.assertEquals(topBuyers.get(1).getInstructionDate().compareTo(actual.get(1).getInstructionDate()), 0);
		Assert.assertEquals(topBuyers.get(1).getSettlementDate().compareTo(actual.get(1).getSettlementDate()), 0);
		
		Assert.assertEquals(topBuyers.get(2).getEntity(), actual.get(2).getEntity());
		Assert.assertEquals(topBuyers.get(2).getAmount(), actual.get(2).getAmount());
		Assert.assertEquals(topBuyers.get(2).getInstructionDate().compareTo(actual.get(2).getInstructionDate()), 0);
		Assert.assertEquals(topBuyers.get(2).getSettlementDate().compareTo(actual.get(2).getSettlementDate()), 0);
		
	}

	@Test
	public void testGetTopSellCalls() {
		List<Ranking> actual = tradeService.getTopSellCalls(trades);
		
		Assert.assertEquals(topSellers.size(), actual.size());
		
		Assert.assertEquals(topSellers.get(0).getEntity(), actual.get(0).getEntity());
		Assert.assertEquals(topSellers.get(0).getAmount(), actual.get(0).getAmount());
		Assert.assertEquals(topSellers.get(0).getInstructionDate().compareTo(actual.get(0).getInstructionDate()), 0);
		Assert.assertEquals(topSellers.get(0).getSettlementDate().compareTo(actual.get(0).getSettlementDate()), 0);
		
		Assert.assertEquals(topSellers.get(1).getEntity(), actual.get(1).getEntity());
		Assert.assertEquals(topSellers.get(1).getAmount(), actual.get(1).getAmount());
		Assert.assertEquals(topSellers.get(1).getInstructionDate().compareTo(actual.get(1).getInstructionDate()), 0);
		Assert.assertEquals(topSellers.get(1).getSettlementDate().compareTo(actual.get(1).getSettlementDate()), 0);
		
		Assert.assertEquals(topSellers.get(2).getEntity(), actual.get(2).getEntity());
		Assert.assertEquals(topSellers.get(2).getAmount(), actual.get(2).getAmount());
		Assert.assertEquals(topSellers.get(2).getInstructionDate().compareTo(actual.get(2).getInstructionDate()), 0);
		Assert.assertEquals(topSellers.get(2).getSettlementDate().compareTo(actual.get(2).getSettlementDate()), 0);
		
		Assert.assertEquals(topSellers.get(3).getEntity(), actual.get(3).getEntity());
		Assert.assertEquals(topSellers.get(3).getAmount(), actual.get(3).getAmount());
		Assert.assertEquals(topSellers.get(3).getInstructionDate().compareTo(actual.get(3).getInstructionDate()), 0);
		Assert.assertEquals(topSellers.get(3).getSettlementDate().compareTo(actual.get(3).getSettlementDate()), 0);
	}

}
