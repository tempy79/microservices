package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

public interface ICardsService {

    void createCards(CardsDto cardsDto);

    CardsDto findByMobileeNum(String mobileeNum);

    boolean updateCustomer(CardsDto cardsDto);

    boolean delete(CardsDto cardsDto);
}
