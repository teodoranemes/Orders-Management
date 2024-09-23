package bll.validators;

import model.Client;
import dao.ClientDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class handles the business logic related to operations on clients.
 * It includes functionality to find, insert, update, delete, and retrieve all clients,
 * while also validating client data before any operations are performed.
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a ClientBLL object with a specified ClientDAO.
     * @param clientDAO The data access object for client data.
     */
    public ClientBLL(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
        validators = new ArrayList<>();
        //validators.add(new EmailValidator());
    }

    /**
     * Finds a client by their ID.
     * @param id The ID of the client to find.
     * @return The found Client object.
     * @throws NoSuchElementException if no client is found with the given ID.
     */
    public Client findClientById(int id) throws NoSuchElementException {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with ID " + id + " was not found.");
        }
        return client;
    }

    /**
     * Inserts a new client into the database after validation.
     * @param client The Client object to be inserted.
     * @return The ID of the inserted client.
     */
    public int insertClient(Client client) {
        validateClient(client);
        return clientDAO.insert(client);
    }

    /**
     * Updates a client's information in the database after validation.
     * @param client The Client object to update.
     */
    public void updateClient(Client client) {
        validateClient(client);
        clientDAO.update(client);
    }

    /**
     * Deletes a client from the database.
     * @param clientId The ID of the client to be deleted.
     */
    public void deleteClient(int clientId) {
        clientDAO.delete(clientId);
    }

    /**
     * Retrieves all clients from the database.
     * @return A list of all clients.
     */
    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    /**
     * Validates a client object using all specified validators.
     * @param client The Client object to validate.
     */
    private void validateClient(Client client) {
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
    }
}
