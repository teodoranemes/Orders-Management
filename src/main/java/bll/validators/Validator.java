package bll.validators;

/**
 * This interface defines a contract for validating objects of a generic type `T`.
 */
public interface Validator<T> {

	/**
	 * Validates an object of type `T`.
	 *
	 * This method performs any necessary validation checks on the provided object.
	 * Specific implementations of this interface will define the validation logic for their respective types.
	 *
	 * @param t The object to be validated.
	 * @throws IllegalArgumentException or any other relevant exception if the validation fails.
	 */
	public void validate(T t);
}
