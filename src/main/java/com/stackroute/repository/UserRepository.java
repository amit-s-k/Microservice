package com.stackroute.repository;

//import com.stackroute.domain.Track;
import com.stackroute.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,Integer> {
    public List<User> findByUserName(String username);
}


