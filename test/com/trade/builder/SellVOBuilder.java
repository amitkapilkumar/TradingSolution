package com.trade.builder;

import java.util.Date;

import com.trade.model.SellVO;

public class SellVOBuilder {
	private Date date;
	private double amount;
	
	public SellVOBuilder withDate(Date date) {
		this.date = date;
		return this;
	}
	
	public SellVOBuilder withAmount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public SellVO build() {
		SellVO sellVO = new SellVO();
		sellVO.setDate(date);
		sellVO.setAmount(amount);
		return sellVO;
	}
	
}
