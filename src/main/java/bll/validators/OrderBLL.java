package bll.validators;

import dao.BillDAO;
import model.Bill;
import model.Order;
import dao.OrderDAO;

/**
 * This class handles the business logic for orders within the application.
 * It interacts with the data access layer (DAO) for orders, products, and bills.
 */
public class OrderBLL {

    /**
     * DAO object for interacting with the orders table in the database.
     */
    private OrderDAO orderDAO;

    /**
     * Reference to the ProductBLL class for handling product-related operations.
     */
    private ProductBLL productBLL;

    /**
     * DAO object for interacting with the bills table in the database.
     */
    private BillDAO billDao;

    /**
     * Constructor that takes references to OrderDAO, ProductBLL, and BillDAO objects.
     *
     * @param orderDAO DAO object for order operations.
     * @param productBLL Business logic layer for products.
     * @param billDao DAO object for bill operations.
     */
    public OrderBLL(OrderDAO orderDAO, ProductBLL productBLL, BillDAO billDao) {
        this.orderDAO = orderDAO;
        this.productBLL = productBLL;
        this.billDao = billDao;
    }

    /**
     * Inserts a new order into the database.
     *
     * This method performs the following steps:
     * 1. Updates the product quantity using the ProductBLL class.
     * 2. Inserts the order into the database using the OrderDAO class.
     * 3. Creates a Bill object based on the inserted order data.
     * 4. Inserts the Bill object into the database using the BillDAO class.
     *
     * @param order The order object to be inserted.
     * @return True if the order was inserted successfully, False otherwise.
     */
    public boolean insert(Order order) {

        if (productBLL.updateProductQuantity(order.getProductId(), order.getQuantity())) {

                orderDAO.insert(order);
                Bill bill = new Bill(order.getId(), order.getClientId(), order.getProductId(), order.getQuantity(), order.getPrice());
                billDao.insertBill(bill);
                return true;
        }
        return false;
    }
}
