package presentation;

import model.Client;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The View class in the MVC pattern represents the visual representation of the data.
 * It provides a graphical user interface for interaction with the application.
 */
public class View extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextField txtAddClientId = new JTextField(10);
    private JTextField txtAddClientName = new JTextField(10);
    private JTextField txtAddClientAddress = new JTextField(10);
    private JTextField txtAddClientEmail = new JTextField(10);
    private JTextField txtAddClientAge = new JTextField(10);
    private JTextField txtEditClientId = new JTextField(10);
    private JTextField txtEditClientName = new JTextField(10);
    private JTextField txtEditClientAddress = new JTextField(10);
    private JTextField txtEditClientEmail = new JTextField(10);
    private JTextField txtEditClientAge = new JTextField(10);
    private JTextField txtDeleteClientId = new JTextField(10);

    private JButton btnAddClient = new JButton("Add Client");
    private JButton btnEditClient = new JButton("Edit Client");
    private JButton btnDeleteClient = new JButton("Delete Client");

    private JButton btnAddProduct = new JButton("Add Product");
    private JButton btnEditProduct = new JButton("Edit Product");
    private JButton btnDeleteProduct = new JButton("Delete Product");

    private JTextField txtAddProductId= new JTextField(10);
    private JTextField txtAddProductName= new JTextField(10);
    private JTextField txtAddProductPrice= new JTextField(10);
    private JTextField txtAddProductQuantity= new JTextField(10);

    private JTextField txtEditProductId= new JTextField(10);
    private JTextField txtEditProductName= new JTextField(10);
    private JTextField txtEditProductPrice= new JTextField(10);
    private JTextField txtEditProductQuantity= new JTextField(10);

    private JTextField txtOrderId= new JTextField(10);
    private JTextField txtClientId= new JTextField(10);
    private JTextField txtProductId= new JTextField(10);
    private JTextField txtPrice= new JTextField(10);
    private JTextField txtQuantity= new JTextField(10);
    private JTextField txtDeleteProductId= new JTextField(10);
    private JButton btnAddOrder = new JButton("Add order");

    private JTable clientsTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Address", "Email", "Age"}, 0));
    private JTable productsTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Quantity"}, 0));

    private JButton btnViewClients = new JButton("View Clients");
    private JButton btnViewProducts = new JButton("View Products");

    /**
     * Constructs the main window and initializes the user interface components.
     */
    public View() {
        initUI();
    }

    /**
     * Initializes the user interface components and layout.
     */
    private void initUI() {
        setTitle("Order Management System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane.addTab("Clients", createClientsPanel());
        tabbedPane.addTab("Products", createProductsPanel());
        tabbedPane.addTab("Orders", createOrdersPanel());

        add(tabbedPane);
    }

    /**
     * Creates the panel for client management.
     * @return JPanel containing client management components.
     */
    private JPanel createClientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JPanel addPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        addPanel.add(new JLabel("Add Client ID:"));
        addPanel.add(txtAddClientId);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(txtAddClientName);
        addPanel.add(new JLabel("Address:"));
        addPanel.add(txtAddClientAddress);
        addPanel.add(new JLabel("Email:"));
        addPanel.add(txtAddClientEmail);
        addPanel.add(new JLabel("Age:"));
        addPanel.add(txtAddClientAge);
        addPanel.add(btnAddClient);

        JPanel editPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        editPanel.add(new JLabel("Edit Client ID:"));
        editPanel.add(txtEditClientId);
        editPanel.add(new JLabel("Name:"));
        editPanel.add(txtEditClientName);
        editPanel.add(new JLabel("Address:"));
        editPanel.add(txtEditClientAddress);
        editPanel.add(new JLabel("Email:"));
        editPanel.add(txtEditClientEmail);
        editPanel.add(new JLabel("Age:"));
        editPanel.add(txtEditClientAge);
        editPanel.add(btnEditClient);

        JPanel deletePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        deletePanel.add(new JLabel("Delete Client ID:"));
        deletePanel.add(txtDeleteClientId);
        deletePanel.add(btnDeleteClient);

        formPanel.add(addPanel);
        formPanel.add(editPanel);
        formPanel.add(deletePanel);

        JScrollPane tableScrollPane = new JScrollPane(clientsTable);
        tableScrollPane.setPreferredSize(new Dimension(800, 200));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel viewPanel = new JPanel();
        viewPanel.add(btnViewClients);
        panel.add(viewPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the panel for product management.
     * @return JPanel containing product management components.
     */
    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JPanel addPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        addPanel.add(new JLabel("Product ID:"));
        addPanel.add(txtAddProductId);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(txtAddProductName);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(txtAddProductPrice);
        addPanel.add(new JLabel("Quantity:"));
        addPanel.add(txtAddProductQuantity);
        addPanel.add(btnAddProduct);

        JPanel editPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        editPanel.add(new JLabel("Product ID:"));
        editPanel.add(txtEditProductId);
        editPanel.add(new JLabel("Name:"));
        editPanel.add(txtEditProductName);
        editPanel.add(new JLabel("Price:"));
        editPanel.add(txtEditProductPrice);
        editPanel.add(new JLabel("Quantity:"));
        editPanel.add(txtEditProductQuantity);
        editPanel.add(btnEditProduct);

        JPanel deletePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        deletePanel.add(new JLabel("Product ID:"));
        deletePanel.add(txtDeleteProductId);
        deletePanel.add(btnDeleteProduct);

        formPanel.add(addPanel);
        formPanel.add(editPanel);
        formPanel.add(deletePanel);

        JScrollPane tableScrollPane = new JScrollPane(productsTable);
        tableScrollPane.setPreferredSize(new Dimension(800, 200));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel viewPanel = new JPanel();
        viewPanel.add(btnViewProducts);
        panel.add(viewPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Updates the clients table with new data.
     * @param clients List of clients to display in the table.
     */
    public void updateClientTable(List<Client> clients) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Client client : clients) {
            model.addRow(new Object[]{client.getId(), client.getName(), client.getAddress(), client.getEmail(), client.getAge()});
        }
    }

    /**
     * Updates the products table with new data.
     * @param products List of products to display in the table.
     */
    public void updateProductTable(List<Product> products) {
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Product product : products) {
            model.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getQuantity()});
        }
    }

    /**
     * Creates the panel for order management.
     * @return JPanel containing order management components.
     */
    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("Order ID:"));
        formPanel.add(txtOrderId);

        formPanel.add(new JLabel("Client ID:"));
        formPanel.add(txtClientId);

        formPanel.add(new JLabel("Product ID:"));
        formPanel.add(txtProductId);

        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(txtQuantity);

        formPanel.add(new JLabel("Price:"));
        formPanel.add(txtPrice);

        formPanel.add(new JLabel());
        formPanel.add(btnAddOrder);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }


    public JTextField getTxtAddClientId() { return txtAddClientId; }
    public JTextField getTxtAddClientName() { return txtAddClientName; }
    public JTextField getTxtAddClientAddress() { return txtAddClientAddress; }
    public JTextField getTxtAddClientEmail() { return txtAddClientEmail; }
    public JTextField getTxtAddClientAge() { return txtAddClientAge; }
    public JTextField getTxtEditClientId() { return txtEditClientId; }
    public JTextField getTxtEditClientName() { return txtEditClientName; }
    public JTextField getTxtEditClientAddress() { return txtEditClientAddress; }
    public JTextField getTxtEditClientEmail() { return txtEditClientEmail; }
    public JTextField getTxtEditClientAge() { return txtEditClientAge; }
    public JTextField getTxtDeleteClientId() { return txtDeleteClientId; }
    public JButton getBtnAddClient() { return btnAddClient; }
    public JButton getBtnEditClient() { return btnEditClient; }
    public JButton getBtnDeleteClient() { return btnDeleteClient; }

    public JButton getBtnAddProduct() { return btnAddProduct; }
    public JButton getBtnEditProduct() { return btnEditProduct; }
    public JButton getBtnDeleteProduct() { return btnDeleteProduct; }

    public JButton getBtnAddOrder() { return btnAddOrder; }

    public JButton getBtnViewClients() { return btnViewClients; }

    public JButton getBtnViewProducts() { return btnViewProducts; }

    public JTextField getTxtAddProductId() { return txtAddProductId; }

    public JTextField getTxtAddProductName() { return txtAddProductName; }

    public JTextField getTxtAddProductPrice() { return txtAddProductPrice; }

    public JTextField getTxtAddProductQuantity() { return txtAddProductQuantity; }

    public JTextField getTxtEditProductId() { return txtEditProductId; }

    public JTextField getTxtEditProductName() { return txtEditProductName; }

    public JTextField getTxtEditProductPrice() { return txtEditProductPrice; }

    public JTextField getTxtEditProductQuantity() { return txtEditProductQuantity; }

    public JTextField getTxtDeleteProductId() { return txtDeleteProductId; }

    public JTextField getTxtOrderId() { return txtOrderId; }
    public JTextField getTxtClientId() { return txtClientId; }

    public JTextField getTxtProductId() { return txtProductId; }

    public JTextField getTxtQuantity() { return txtQuantity; }

    public JTextField getTxtPrice() { return txtPrice; }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            View ex = new View();
            ex.setVisible(true);
        });
    }
}
