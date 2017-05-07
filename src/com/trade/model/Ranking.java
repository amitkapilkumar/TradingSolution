package com.trade.model;

import java.util.Date;

public class Ranking {
	private String entity;
	private Date instructionDate;
	private Date settlementDate;
	private double amount;
	
	public Ranking() {
		
	}
	
	public Ranking(String entity, Date instructionDate, Date settlementDate, double amount) {
		this.entity = entity;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.amount = amount;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
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
}