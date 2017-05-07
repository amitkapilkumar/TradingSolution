package com.trade.parser;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.trade.builder.TradeBuilder;
import com.trade.model.Trade;

import junit.framework.Assert;

public class DataParserImplTest {
	
	private List<String> list;
	private List<Trade> expected;
	private DataParserImpl dataParser;

	@Before
	public void setUp() throws Exception {
		dataParser = new DataParserImpl();
		list = new ArrayList<String>() {{
			add("foo, B, 0.50, SGP, 01 Jan 2016, 02 Jan 2016, 200, 100.25");
			add("bar, S, 0.22, AED, 05 Jan 2016, 07 Jan 2016, 450, 150.5");
			add("bar, S, 0.24, AED, 04 Jan 2016, 07 Jan 2016, 250, 160.5");
			add("tbay, S, 0.63, SAR, 01 Feb 2016, 02 Feb 2016, 700, 421.24");
			add("haxon, B, 0.72, GBP, 23 Feb 2016, 27 Feb 2016, 900, 567.58");
			add("dripkart, B, 0.30, INR, 09 March 2016, 11 March 2016, 1000, 142.27");
			add("munez-corp, S, 0.780, AED, 23 March 2016, 25 March 2016, 350, 680.39");
		}};
		
		expected = new ArrayList<Trade>() {{
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
	}

	@Test
	public void testParse() {
		try {
			List<Trade> actual = dataParser.parse(list);
			
			Assert.assertEquals(expected.size(), actual.size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
