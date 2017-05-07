package com.trade.builder;

import java.util.Date;

import com.trade.model.BuyVO;

public class BuyVOBuilder {
	private Date date;
	private double amount;
	
	public BuyVOBuilder withDate(Date date) {
		this.date = date;
		return this;
	}
	
	public BuyVOBuilder withAmount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public BuyVO build() {
		BuyVO buyVO = new BuyVO();
		buyVO.setDate(date);
		buyVO.setAmount(amount);
		return buyVO;
	}

}
