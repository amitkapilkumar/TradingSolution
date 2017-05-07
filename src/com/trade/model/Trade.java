package com.trade.model;

import java.util.Date;

public class Trade {
	private String entity;
	private boolean isBuy;
	private double agreedFX;
	private String currencySymbol;
	private Date instructionDate;
	private Date settlementDate;
	private long units;
	private double pricerPerUnit;
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public boolean isBuy() {
		return isBuy;
	}
	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
	public double getAgreedFX() {
		return agreedFX;
	}
	public void setAgreedFX(double agreedFX) {
		this.agreedFX = agreedFX;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public Date getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public long getUnits() {
		return units;
	}
	public void setUnits(long units) {
		this.units = units;
	}
	public double getPricerPerUnit() {
		return pricerPerUnit;
	}
	public void setPricerPerUnit(double pricerPerUnit) {
		this.pricerPerUnit = pricerPerUnit;
	}
	
	public double getTradeAmount() {
		return pricerPerUnit * agreedFX * units;
	}
}