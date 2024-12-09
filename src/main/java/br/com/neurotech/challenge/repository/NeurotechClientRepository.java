package br.com.neurotech.challenge.repository;

import br.com.neurotech.challenge.entity.NeurotechClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NeurotechClientRepository extends MongoRepository<NeurotechClient, String> {

    @Query("{ 'age' : { $gt: 23, $lt: 49 }, 'income' : { $gt: 5000, $lt: 15000 } }")
    public List<NeurotechClient> findSpecialClients();
}
