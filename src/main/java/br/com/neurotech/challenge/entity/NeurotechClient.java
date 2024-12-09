package br.com.neurotech.challenge.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "NeurotechClient")
public class NeurotechClient {

	@Id
	private String id;

	private String name;
	private Integer age;
	private Double income;

}