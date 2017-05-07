package com.trade.builder;

import java.util.Date;

import com.trade.model.Ranking;

public class RankingBuilder {
	private String entity;
	private Date instructionDate;
	private Date settlementDate;
	private double amount;
	
	public RankingBuilder withEntity(String entity) {
		this.entity = entity;
		return this;
	}
	
	public RankingBuilder withInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
		return this;
	}
	
	public RankingBuilder withSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
		return this;
	}
	
	public RankingBuilder withAmount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public Ranking build() {
		Ranking ranking = new Ranking();
		ranking.setAmount(amount);
		ranking.setEntity(entity);
		ranking.setInstructionDate(instructionDate);
		ranking.setSettlementDate(settlementDate);
		return ranking;
	}
}
