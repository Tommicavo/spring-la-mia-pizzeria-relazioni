package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Offer;
import org.java.spring.db.repo.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public Offer findById(int id) {
        return offerRepository.findById(id).get();
    }

    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }
}
