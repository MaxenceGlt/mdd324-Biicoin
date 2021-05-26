package com.ipiecoles.Model;

public class BitcoinEntry {
    public Integer getBitcoinAmount() {
        return bitcoinAmount;
    }

    public void setBitcoinAmount(Integer bitcoinAmount) {
        this.bitcoinAmount = bitcoinAmount;
    }

    public String getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(String currencyList) {
        this.currencyList = currencyList;
    }

    private Integer bitcoinAmount;
    private String currencyList;


}
