package com.eazybytes.cards.controller;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CardsController {

    private ICardsService cardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(CardsDto cardsDto){
        cardsService.createCards(cardsDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "201"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetch(@RequestParam String mobileNumber){
        CardsDto byMobileeNum = cardsService.findByMobileeNum(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(byMobileeNum);
    }

    @GetMapping("/update")
    public ResponseEntity<CardsDto> update(CardsDto cardsDto){
        boolean isUpdated = cardsService.updateCustomer(cardsDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cardsDto);
        }
    }


    @GetMapping("/delete")
    public ResponseEntity<CardsDto> delete(CardsDto cardsDto){
        boolean isDeleted = cardsService.delete(cardsDto);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cardsDto);
        }
    }
}
