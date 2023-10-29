package com.eposting.epost.repository;

import com.eposting.epost.model.Profile;
import com.eposting.epost.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

}