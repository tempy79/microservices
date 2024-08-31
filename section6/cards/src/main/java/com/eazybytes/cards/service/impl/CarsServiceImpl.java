package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCards(CardsDto cardsDto) {
        Cards cards = CardsMapper.mapToCards(cardsDto, new Cards());
        cardsRepository.save(cards);
    }

    @Override
    public CardsDto findByMobileeNum(String mobileeNum) {
        Cards byMobileNumber = cardsRepository.findByMobileNumber(mobileeNum).orElseThrow(() -> new ResourceNotFoundException("not found"));
        return CardsMapper.mapToCardsDto(byMobileNumber, new CardsDto());
    }

    @Override
    public boolean updateCustomer(CardsDto cardsDto) {
        boolean isUpdated = false;
        Optional<Cards> byMobileNumber = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber());
        if(byMobileNumber.isPresent()){
            Cards cards = byMobileNumber.get();
            CardsMapper.mapToCards(cardsDto, byMobileNumber.get());
            cardsRepository.save(cards);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean delete(CardsDto cardsDto) {
        boolean isDeleted = false;
        cardsRepository.findByMobileNumber(cardsDto.getMobileNumber()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        cardsRepository.deleteByMobileNumber(cardsDto.getMobileNumber());
        isDeleted = true;
        return isDeleted;
    }

}
