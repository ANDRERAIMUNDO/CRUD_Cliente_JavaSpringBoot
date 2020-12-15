package com.andreraimundo.client_api.Controller.Exceptions;

import com.andreraimundo.client_api.Dto.ClientDto;
import com.andreraimundo.client_api.Entity.Client;
import com.andreraimundo.client_api.Service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Informando que Essa classe é um controller que segue o padrão Rest
@RestController
// Mapeando a rota /client
@RequestMapping(value = "/client")
public class ClientController {
    // @Autowired é responsavel pela injeção de Dependencia
    @Autowired
    ClientService clientService; // referenciando a camada "Service"

    // Mapeando a rota que será responsável por retornar um único cliente através do
    // parâmetro id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDto> get(@PathVariable int id) {
        var obj = clientService.find(id).clientDto();
        return ResponseEntity.ok(obj);
    }

    // Mapeando a rota que será responsável por retornar Todos os clientes
    // cadastrados.
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> getAll() {
        var obj = clientService.findAll().stream().map(Client::clientDto).collect(Collectors.toList());
        return ResponseEntity.ok(obj);
    }

    // Mapeando a rota que será responsável por adicionar um novo Cliente.
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> post(@Validated @RequestBody ClientDto objDTO) {
        clientService.insert(objDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Mapeando a rota que será responsável por Editar um Cliente.
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Validated @RequestBody ClientDto objDTO, @PathVariable Integer id) {
        var obj = clientService.update(id, objDTO);
        return ResponseEntity.ok(obj.clientDto());
    }

    // Mapeando a rota que será responsável por Deletar um Cliente de acordo com o
    // seu Id.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
