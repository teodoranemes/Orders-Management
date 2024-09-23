package presentation;

import javax.swing.*;
import bll.validators.ClientBLL;
import bll.validators.OrderBLL;
import bll.validators.ProductBLL;
import connection.ConnectionFactory;
import dao.BillDAO;
import model.Client;
import model.Product;
import model.Order;
import dao.ClientDAO;
import dao.ProductDAO;
import dao.OrderDAO;

import java.util.List;

/**
 * The Controller class handles user interactions, communicates with the BLL (Business Logic Layer),
 * and updates the View accordingly.
 */
public class Controller {
    private View view;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;

    /**
     * Constructor for the Controller class.
     *
     * @param view the view instance
     * @param clientBLL the client business logic layer instance
     * @param productBLL the product business logic layer instance
     * @param orderBLL the order business logic layer instance
     */
    public Controller(View view, ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.view = view;
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.orderBLL = orderBLL;
        initView();
    }

    /**
     * Initializes the view and sets up the event listeners.
     */
    private void initView() {
        view.setVisible(true);
        setupClientListeners();
        setupProductListeners();
        setupOrderListeners();
    }

    /**
     * Sets up listeners for client-related actions.
     */
    private void setupClientListeners() {
        view.getBtnAddClient().addActionListener(e -> addClient());
        view.getBtnEditClient().addActionListener(e -> editClient());
        view.getBtnDeleteClient().addActionListener(e -> deleteClient());
        view.getBtnViewClients().addActionListener(e -> viewClients());
    }

    /**
     * Sets up listeners for product-related actions.
     */
    private void setupProductListeners() {
        view.getBtnAddProduct().addActionListener(e -> addProduct());
        view.getBtnEditProduct().addActionListener(e -> editProduct());
        view.getBtnDeleteProduct().addActionListener(e -> deleteProduct());
        view.getBtnViewProducts().addActionListener(e -> viewProducts());
    }

    /**
     * Sets up listeners for order-related actions.
     */
    private void setupOrderListeners() {
        view.getBtnAddOrder().addActionListener(e -> addOrder());
    }

    /**
     * Adds a new client.
     */
    private void addClient() {
        try {
            Client client = new Client(
                    Integer.parseInt(view.getTxtAddClientId().getText()),
                    view.getTxtAddClientName().getText(),
                    view.getTxtAddClientAddress().getText(),
                    view.getTxtAddClientEmail().getText(),
                    Integer.parseInt(view.getTxtAddClientAge().getText())
            );
            clientBLL.insertClient(client);
            JOptionPane.showMessageDialog(view, "Client added successfully!");
            viewClients();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding client: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Edits an existing client.
     */
    private void editClient() {
        try {
            Client client = new Client(
                    Integer.parseInt(view.getTxtEditClientId().getText()),
                    view.getTxtEditClientName().getText(),
                    view.getTxtEditClientAddress().getText(),
                    view.getTxtEditClientEmail().getText(),
                    Integer.parseInt(view.getTxtEditClientAge().getText())
            );
            clientBLL.updateClient(client);
            JOptionPane.showMessageDialog(view, "Client updated successfully!");
            viewClients();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error updating client: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deletes an existing client.
     */
    private void deleteClient() {
        try {
            int clientId = Integer.parseInt(view.getTxtDeleteClientId().getText());
            clientBLL.deleteClient(clientId);
            JOptionPane.showMessageDialog(view, "Client deleted successfully!");
            viewClients();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error deleting client: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays all clients.
     */
    private void viewClients() {
        try {
            List<Client> clients = clientBLL.getAllClients();
            view.updateClientTable(clients);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error retrieving clients: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new product.
     */
    private void addProduct() {
        try {
            Product product = new Product(
                    Integer.parseInt(view.getTxtAddProductId().getText()),
                    view.getTxtAddProductName().getText(),
                    Integer.parseInt(view.getTxtAddProductPrice().getText()),
                    Integer.parseInt(view.getTxtAddProductQuantity().getText())
            );
            productBLL.insertProduct(product);
            JOptionPane.showMessageDialog(view, "Product added successfully!");
            viewProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Edits an existing product.
     */
    private void editProduct() {
        try {
            Product product = new Product(
                    Integer.parseInt(view.getTxtEditProductId().getText()),
                    view.getTxtEditProductName().getText(),
                    Integer.parseInt(view.getTxtEditProductPrice().getText()),
                    Integer.parseInt(view.getTxtEditProductQuantity().getText())
            );
            productBLL.updateProduct(product);
            JOptionPane.showMessageDialog(view, "Product updated successfully!");
            viewProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error updating product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deletes an existing product.
     */
    private void deleteProduct() {
        try {
            int productId = Integer.parseInt(view.getTxtDeleteProductId().getText());
            productBLL.deleteProduct(productId);
            JOptionPane.showMessageDialog(view, "Product deleted successfully!");
            viewProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error deleting product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays all products.
     */
    private void viewProducts() {
        try {
            List<Product> products = productBLL.getAllProducts();
            view.updateProductTable(products);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error retrieving products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new order.
     */
    private void addOrder() {
        try {

            int productId = Integer.parseInt(view.getTxtProductId().getText());
            int quantity = Integer.parseInt(view.getTxtQuantity().getText());
            int clientId = Integer.parseInt(view.getTxtClientId().getText());
            int orderId = Integer.parseInt(view.getTxtOrderId().getText());
            int price = Integer.parseInt(view.getTxtPrice().getText());

            Order order = new Order(orderId, clientId, productId, quantity, price);
            if (orderBLL.insert(order)) {
                JOptionPane.showMessageDialog(view, "Order added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add order: Not enough stock or other error.", "Order Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid input: Please check your data.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to run the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        View view = new View();
        ConnectionFactory connectionFactory = new ConnectionFactory();

        ClientDAO clientDao = new ClientDAO(connectionFactory);
        ProductDAO productDao = new ProductDAO(connectionFactory);
        OrderDAO orderDao = new OrderDAO(connectionFactory);
        BillDAO billDao = new BillDAO();

        ClientBLL clientBLL = new ClientBLL(clientDao);
        ProductBLL productBLL = new ProductBLL(productDao);
        OrderBLL orderBLL = new OrderBLL(orderDao, productBLL, billDao);

        new Controller(view, clientBLL, productBLL, orderBLL);
    }
}
