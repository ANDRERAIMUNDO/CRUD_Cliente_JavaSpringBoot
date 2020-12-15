package com.andreraimundo.client_api.Service;

import com.andreraimundo.client_api.Dto.*;
import com.andreraimundo.client_api.Entity.*;

import java.util.List;

public interface ClientService {
    Client find(int id);

    List<Client> findAll();

    Client update(int idClient, ClientDto client);

    Client insert(ClientDto client);

    void delete(int idClient);
}
