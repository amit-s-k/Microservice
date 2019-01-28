package com.example.microservice.user.repository;


import com.example.microservice.user.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MuzixRepository extends MongoRepository<Track,Integer> {
    public List<Track> findByTrackName(String trackName);
}


