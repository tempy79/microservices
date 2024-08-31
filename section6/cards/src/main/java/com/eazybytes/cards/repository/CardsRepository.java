package com.eazybytes.cards.repository;

import com.eazybytes.cards.entity.Cards;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {


    Optional<Cards> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);
}
