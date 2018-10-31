package vykmnsk;


import java.math.BigDecimal;

public class Event {
    private String buyerParty;
    private String sellerParty;
    private BigDecimal premiumAmount;
    private String premiumCurrency;

    public void setBuyerParty(String buyerParty) {
        this.buyerParty = buyerParty.toUpperCase();
    }

    public void setSellerParty(String sellerParty) {
        this.sellerParty = sellerParty.toUpperCase();
    }

    public void setPremiumAmount(String premiumAmountStr) {
        try {
            this.premiumAmount = new BigDecimal(premiumAmountStr);
        } catch (Exception e) {
            this.premiumAmount = null;
        }
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency.toUpperCase();
    }

    public String toString(){
        return String.format("%s,%s,%.2f,%s", buyerParty, sellerParty, premiumAmount, premiumCurrency);
    }

    public boolean isValid(){
        boolean isKnownSellerAU = sellerParty.equals("EMU_BANK") && premiumCurrency.equals(Constants.CURRENCY.AUD.name());
        boolean isKnownSellerUS = sellerParty.equals("BISON_BANK") && premiumCurrency.equals(Constants.CURRENCY.USD.name());
        return  (isKnownSellerAU || isKnownSellerUS) && !Utils.areAnagrams(buyerParty, sellerParty);
    }


}
