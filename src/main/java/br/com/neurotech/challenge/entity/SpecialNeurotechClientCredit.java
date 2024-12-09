package br.com.neurotech.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SpecialNeurotechClientCredit {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class NeurotechClientNameIncome {

        private String name;
        private Double income;

    }

    private List<NeurotechClientNameIncome> clients;

    public SpecialNeurotechClientCredit(List<NeurotechClient> clients) {
        this.clients = clients.stream().map((client) -> new NeurotechClientNameIncome(client.getName(), client.getIncome())).toList();
    }
}
