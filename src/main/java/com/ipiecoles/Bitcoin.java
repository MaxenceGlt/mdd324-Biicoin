package com.ipiecoles;

public class Bitcoin {

    private double bitcoinAmount;

    private CurrencyPrice currenciesEquivalent;


    public CurrencyPrice getCurrenciesEquivalent() {
        return currenciesEquivalent;
    }

    public void setCurrenciesEquivalent(CurrencyPrice currenciesEquivalent) {
        this.currenciesEquivalent = currenciesEquivalent;
    }


    public double getBitcoinAmount() {
        return bitcoinAmount;
    }

    public void setBitcoinAmount(double bitcoinAmount) {
        this.bitcoinAmount = bitcoinAmount;
    }


}
