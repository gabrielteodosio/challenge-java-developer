package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.repository.NeurotechClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;

import java.util.List;
import java.util.Optional;

@Service
public class NeurotechClientService {

	@Autowired
	private NeurotechClientRepository neurotechClientRepository;
	
	public NeurotechClient create(NeurotechClient neurotechClient) {
		return neurotechClientRepository.save(neurotechClient);
	}
	
	public Optional<NeurotechClient> retrieve(String id) {
		return neurotechClientRepository.findById(id);
	}

	public List<NeurotechClient> retrieveAll() {
		return neurotechClientRepository.findAll();
	}

	public Optional<NeurotechClient> delete(String id) {
		Optional<NeurotechClient> clientOptional = retrieve(id);

		if (clientOptional.isPresent()) {
			neurotechClientRepository.deleteById(id);
		}

		return clientOptional;
	}

	public Optional<NeurotechClient> update(String id, NeurotechClient neurotechClient) {
		Optional<NeurotechClient> neurotechClientOptional = retrieve(id);

		if (neurotechClientOptional.isPresent()) {
			NeurotechClient client = neurotechClientOptional.get();

			client.setName(neurotechClient.getName() != null ? neurotechClient.getName() : client.getName());
			client.setAge(neurotechClient.getAge() != null ? neurotechClient.getAge() : client.getAge());
			client.setIncome(neurotechClient.getIncome() != null ? neurotechClient.getIncome() : client.getIncome());

			neurotechClientRepository.save(client);
		}

		return neurotechClientOptional;
	}

	public List<NeurotechClient> getAllSpecialClients() {
		return neurotechClientRepository.findSpecialClients();
	}

}
