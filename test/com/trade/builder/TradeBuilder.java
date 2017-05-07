package com.trade.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.trade.model.Trade;

public class TradeBuilder {
	private String entity;
	private boolean isBuy;
	private double agreedFX;
	private String currencySymbol;
	private String instructionDate;
	private String settlementDate;
	private long units;
	private double pricerPerUnit;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
	
	public TradeBuilder withEntity(String entity) {
		this.entity = entity;
		return this;
	}
	
	public TradeBuilder withSetBuy(boolean isBuy) {
		this.isBuy = isBuy;
		return this;
	}
	
	public TradeBuilder withAgreedFX(double agreedFX) {
		this.agreedFX = agreedFX;
		return this;
	}
	
	public TradeBuilder withCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
		return this;
	}
	
	public TradeBuilder withInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
		return this;
	}
	
	public TradeBuilder withUnits(long units) {
		this.units = units;
		return this;
	}
	
	public TradeBuilder withPricePerUnit(double pricePerUnit) {
		this.pricerPerUnit = pricePerUnit;
		return this;
	}
	
	public TradeBuilder withSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
		return this;
	}
	
	public Trade build() throws ParseException {
		Trade trade = new Trade();
		trade.setEntity(entity);
		trade.setBuy(isBuy);
		trade.setAgreedFX(agreedFX);
		trade.setCurrencySymbol(currencySymbol);
		trade.setInstructionDate(sdf.parse(instructionDate));
		trade.setSettlementDate(sdf.parse(settlementDate));
		trade.setUnits(units);
		trade.setPricerPerUnit(pricerPerUnit);
		return trade;
	}
}
