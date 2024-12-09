package br.com.neurotech.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NeurotechClientCredit {

    private NeurotechClient client;
    private List<VehicleModel> availableCredit;

}
