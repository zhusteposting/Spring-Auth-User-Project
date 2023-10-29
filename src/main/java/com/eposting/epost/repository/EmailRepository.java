package com.eposting.epost.repository;

import com.eposting.epost.model.Email;
import com.eposting.epost.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

}