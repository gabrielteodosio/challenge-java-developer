package br.com.neurotech.challenge.service;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.VehicleModel;

@Service
public class CreditService {
	
	/**
	 * Efetua a checagem se o cliente está apto a receber crédito
	 * para um determinado modelo de veículo
	 */
	boolean checkCredit(String clientId, VehicleModel model) {
		return true;
	}
	
}
