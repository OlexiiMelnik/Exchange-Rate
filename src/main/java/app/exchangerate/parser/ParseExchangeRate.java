package app.exchangerate.parser;

import app.exchangerate.model.ExchangeRate;

public interface ParseExchangeRate<T> {
    ExchangeRate parseApiExchangeRate(T apiExchangeRate);
}
