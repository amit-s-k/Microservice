package com.stackroute.microservice.user.service;

import com.stackroute.microservice.user.domain.Track;
import com.stackroute.microservice.user.exceptions.TrackAlreadyExistsException;
import com.stackroute.microservice.user.exceptions.TrackNotFoundException;
import com.stackroute.microservice.user.repository.MuzixRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MuzixServiceTest {
    List<Track> list = null;
    private Track track;
    //Create a mock for MuzixRepository
    @Mock
    private MuzixRepository muzixRepository;
    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MuzixServiceImpl muzixService;

    //Initialising the mock object
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackId(10);
        track.setTrackName("jayanthi");
        track.setTrackComments("moms love");
        list = new ArrayList<>();
        list.add(track);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {
        when(muzixRepository.save(any())).thenReturn(track);
        Track savedTrack = muzixService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(muzixRepository.save(ArgumentMatchers.any())).thenReturn(null);
        Track savedTrack = muzixService.saveTrack(track);
//        System.out.println("savedTrack" + savedTrack);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(track);
    }

    @Test
    public void getAllTracks() {
        muzixRepository.save(track);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(list);
        List<Track> tracklist = muzixService.getAllTracks();
        Assert.assertEquals(list, tracklist);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(track);
    }
//    @Test(expected = TrackNotFoundException.class)
//    public void getAllTracksFailure() throws TrackNotFoundException{
//        List<Track> tracklist = muzixService.getAllTracks();
//        Assert.assertNull(tracklist);
//    }

    @Test
    public void deleteById() throws TrackNotFoundException {
        when(muzixRepository.existsById(anyInt())).thenReturn(true);
//        when(muzixRepository.save((Track)any())).thenReturn(track);
        muzixRepository.save(track);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Track> list1 = muzixService.removeById(track.getTrackId());
        Assert.assertEquals(list, list1);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(track);
    }

    @Test(expected = TrackNotFoundException.class)
    public void deleteByIdFailure() throws TrackNotFoundException {
        when(muzixRepository.existsById(2)).thenReturn(true);
        muzixRepository.save(track);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Track> list1 = muzixService.removeById(track.getTrackId());
        Assert.assertEquals(list, list1);
    }

    @Test
    public void updateTrack() throws TrackNotFoundException {
        when(muzixRepository.existsById(anyInt())).thenReturn(true);
        when(muzixRepository.findById(anyInt())).thenReturn(Optional.of(track));
        when(muzixRepository.save(any())).thenReturn(track);
        Track track1 = muzixService.updateTrack(track.getTrackId(), anyString());
        Assert.assertEquals(track1, track);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(track);
    }

    @Test
    public void trackByTrackId() throws TrackNotFoundException {
        when(muzixRepository.existsById(anyInt())).thenReturn(true);
        when(muzixRepository.findById(anyInt())).thenReturn(Optional.of(track));
        muzixRepository.save(track);
        Track track1 = muzixService.trackByTrackId(track.getTrackId());
        Assert.assertEquals(track1, track);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(track);
    }

    @Test
    public void trackByTrackName() throws TrackNotFoundException {
        when(muzixRepository.findByTrackName(anyString())).thenReturn(list);
        List<Track> muzixlist = muzixService.trackByTrackName("jayanthi");
        Assert.assertEquals(list, muzixlist);

        //verify here verifies that userRepository save method is only called twice
        verify(muzixRepository, times(2)).findByTrackName("jayanthi");
    }
}
