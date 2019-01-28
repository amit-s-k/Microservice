package com.example.microservice.user.service;

import com.example.microservice.user.domain.Track;
import com.example.microservice.user.exceptions.TrackAlreadyExistsException;
import com.example.microservice.user.exceptions.TrackNotFoundException;

import java.util.List;

//MuzixService interface//
public interface MuzixService {
    Track saveTrack(Track track) throws TrackAlreadyExistsException;

    List<Track> getAllTracks();

    List<Track> removeById(int trackId) throws TrackNotFoundException;

    Track updateTrack(int trackId, String trackComments) throws TrackNotFoundException;

    List<Track> trackByTrackName(String trackName) throws TrackNotFoundException;

    Track trackByTrackId(int trackId) throws TrackNotFoundException;
}
