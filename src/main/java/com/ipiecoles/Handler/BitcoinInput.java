package com.ipiecoles.Handler;

public class BitcoinInput {

    private Integer bitcoinAmount;
    private String currencyList;

    public BitcoinInput(int bitcoinAmount, String currencyList) {
        this.bitcoinAmount = bitcoinAmount;
        this.currencyList = currencyList;
    }

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
}
