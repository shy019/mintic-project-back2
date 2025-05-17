package com.mintic.genericstore.utils;

import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Client;
import com.mintic.genericstore.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;

    public Client validateClientExistsById(Long clientId) {
        return clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new NotFoundException(NO_CLIENT_FOUND_BY_ID));
    }

    public Client validateClientExistsByName(String clientName) {
        return clientRepository.findByClientName(clientName)
                .orElseThrow(() -> new NotFoundException(NO_CLIENT_FOUND_BY_NAME));
    }

    public void ensureClientDoesNotExist(Long clientId) {
        if (clientRepository.existsByClientId(clientId)) {
            throw new NotFoundException(CLIENT_EXISTS_IN_DB);
        }
    }
}
