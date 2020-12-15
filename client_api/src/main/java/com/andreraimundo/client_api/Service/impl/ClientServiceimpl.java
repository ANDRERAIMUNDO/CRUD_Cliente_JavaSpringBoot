package com.andreraimundo.client_api.Service.impl;

import lombok.var;
import lombok.Builder;

import java.util.List;

import com.andreraimundo.client_api.Controller.Exceptions.type.*;
import com.andreraimundo.client_api.Dto.ClientDto;
import com.andreraimundo.client_api.Entity.Client;
import com.andreraimundo.client_api.Repository.ClienteRepository;
import com.andreraimundo.client_api.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.lang.Object;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClientServiceimpl implements ClientService {

    @Autowired
    ClienteRepository clientRepository;

    public Client find(int id) {
        var cli = clientRepository.findById(id);
        if (!cli.isPresent()) {
            // Lança uma exceção informando que o cliente não existe
            throw new DataIntegrationException("Cliente não existe");
        }
        return cli.get();
    }

    // Busca todos os clientes da base
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // Realiza o update do Cliente
    public Client update(int idClient, ClientDto client) {
        var cli = this.find(idClient);
        cli.setName(client.getName());
        cli.setEmail(client.getEmail());
        return clientRepository.save(cli);
    }

    public Client insert(ClientDto client) {

        var cli = clientRepository.findOptionalByEmail(client.getEmail());

        if (cli.isPresent()) {
            throw new DataIntegrationException("Email Ja Cadastrado");
        }

        return clientRepository.save(cli.get());
    }

    public void delete(int idClient) {
        // Busca o cliente que será deletado
        var cli = this.find(idClient);
        // deleta o Cliente
        clientRepository.delete(cli);
    }
}
