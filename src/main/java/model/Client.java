package model;

/**
 * Represents a client in the system with personal and contact information.
 */
public class Client {

    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    /**
     * Constructs a new client with specified details.
     *
     * @param id      the unique identifier of the client
     * @param name    the name of the client
     * @param address the address of the client
     * @param email   the email address of the client
     * @param age     the age of the client
     */
    public Client(int id, String name, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Gets the client's unique identifier.
     *
     * @return the client's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the client's unique identifier.
     *
     * @param id the new id for the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the client.
     *
     * @return the client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the new name for the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the client.
     *
     * @return the client's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     *
     * @param address the new address for the client
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the age of the client.
     *
     * @return the client's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the client.
     *
     * @param age the new age for the client
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the email address of the client.
     *
     * @return the client's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email the new email for the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
