import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.trade.TradeManager;
import com.trade.model.BuyVO;
import com.trade.model.Ranking;
import com.trade.model.SellVO;
import com.trade.model.Trade;

public class Main {
	public static void main(String args[]) {
		List<String> list = new ArrayList<String>() {{
			add("foo, B, 0.50, SGP, 01 Jan 2016, 02 Jan 2016, 200, 100.25");
			add("bar, S, 0.22, AED, 05 Jan 2016, 07 Jan 2016, 450, 150.5");
			add("bar, S, 0.24, AED, 04 Jan 2016, 07 Jan 2016, 250, 160.5");
			add("tbay, S, 0.63, SAR, 01 Feb 2016, 02 Feb 2016, 700, 421.24");
			add("haxon, B, 0.72, GBP, 23 Feb 2016, 27 Feb 2016, 900, 567.58");
			add("dripkart, B, 0.30, INR, 09 March 2016, 11 March 2016, 1000, 142.27");
			add("munez-corp, S, 0.780, AED, 23 March 2016, 25 March 2016, 350, 680.39");
		}};
		
		try {
			TradeManager tradeManager = new TradeManager();
			List<Trade> trades = tradeManager.processTradeData(list);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
			
			System.out.println("Amount in USD settled incoming everyday");
			List<SellVO> sellVOs = tradeManager.getSettledAmountforEverydayIncoming(trades);
			System.out.println("Date\t\t\tAmount");
			for(SellVO sellVO : sellVOs) {
				System.out.println(sdf.format(sellVO.getDate()) + "\t\t\t" + sellVO.getAmount());
			}
			
			System.out.println("************************************************************************************************************\n");
			
			System.out.println("Amount in USD settled outgoing everyday");
			List<BuyVO> buyVOs = tradeManager.getSettledAmountforEverydayOutgoing(trades);
			System.out.println("Date\t\t\tAmount");
			for(BuyVO buyVO : buyVOs) {
				System.out.println(sdf.format(buyVO.getDate()) + "\t\t\t" + buyVO.getAmount());
			}
			
			System.out.println("************************************************************************************************************\n");
			
			System.out.println("Ranking based on incoming");
			System.out.println("Rank\tEntity\t\tInstructionDate\t\tSettlementDate\t\tAmount");
			List<Ranking> rankings = tradeManager.getTopSellCalls(trades);
			for(int i=0; i < rankings.size(); i++) {
				Ranking rank = rankings.get(i);
				System.out.println((i+1) + "\t" +rank.getEntity() + "\t\t" + sdf.format(rank.getInstructionDate()) + "\t\t" 
				+ sdf.format(rank.getSettlementDate()) + "\t\t" + rank.getAmount());
			}
			
			System.out.println("************************************************************************************************************\n");
			
			System.out.println("Ranking based on outgoing");
			System.out.println("Rank\tEntity\t\tInstructionDate\t\tSettlementDate\t\tAmount");
			rankings = tradeManager.getTopBuyCalls(trades);
			for(int i=0; i < rankings.size(); i++) {
				Ranking rank = rankings.get(i);
				System.out.println((i+1) + "\t" +rank.getEntity() + "\t\t" + sdf.format(rank.getInstructionDate()) + "\t\t" + 
						sdf.format(rank.getSettlementDate()) + "\t\t" + rank.getAmount());
			}
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
	}
}
