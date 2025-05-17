package com.mintic.genericstore.utils.constants;

public class ServiceConstants {
    // User
    public static final String USER_NOT_FOUND_ID = "No user found with that ID";
    public static final String USER_NOT_FOUND_NAME = "No user found with that name";
    public static final String USERNAME_TAKEN = "Username is already taken";
    public static final String EMAIL_IN_USE = "Email is already in use";
    public static final String USER_DOES_NOT_EXIST = "User not found with username: ";
    public static final String ROLE_NOT_FOUND = "Error: Role %s not found.";
    public static final String NO_CLIENTS_IN_SYSTEM = "There are no clients in the system";
    public static final String USER_NOT_FOUND = "No user found with the provided ID";
    public static final String USER_NOT_FOUND_WITH = "No user found with the provided ID: {}";
    public static final String USERNAME_IS_TAKEN = "Username is already taken";
    public static final String USER_REGISTERED = "User registered successfully";
    public static final String USER_OR_EMAIL_IS_TAKEN = "User or email already exists";
    public static final String REGISTER_NEW_USER = "Register new user";
    public static final String REGISTER_DESCRIPTION = "Registers a new user and assigns one or more roles.";
    public static final String DEFAULT_ROLE_NOT_FOUND = "Default role not found";

    // Supplier
    public static final String SUPPLIERS_NOT_FOUND = "No suppliers found in the system";
    public static final String SUPPLIER_NOT_FOUND_NAME = "No supplier found with the provided name";
    public static final String SUPPLIER_NIT_EXISTS = "This Supplier Id already exists in the database";
    public static final String SUPPLIER_NOT_FOUND_ID = "No supplier found with the provided ID";
    public static final String SUPPLIER_NOT_FOUND = "Supplier not found with id: ";

    // Sale
    public static final String SALE_NOT_FOUND = "No sale found with the provided code";
    public static final String SALE_NOT_FOUND_WITH = "No sale found with the provided code: {}";
    public static final String NO_SALES_FOUND = "No sales found in the system";

    // Sale Detail
    public static final String NO_SALE_DETAILS_FOUND = "No sale details found in the system";
    public static final String NO_SALE_DETAIL_FOUND_WITH_CODE = "No sale detail found with code: ";
    public static final String SALE_DETAILS_SAVED_SUCCESS = "Sale details saved successfully!";
    public static final String ERROR_SAVING_SALE_DETAILS = "Failed to save sale details";
    public static final String NO_SALES_FOUND_FOR_BRANCH = "No sales found for branch: ";

    // Product
    public static final String NO_PRODUCTS_IN_SYSTEM = "There are no products in the system";
    public static final String NO_PRODUCT_FOUND_WITH_CODE = "No product found with this code";
    public static final String NO_PRODUCT_FOUND_WITH_CODE_EXT = "No product found with this code: ";
    public static final String NO_PRODUCT_FOUND_WITH_NAME = "No product found with this name";
    public static final String SAVED_PRODUCTS_MESSAGE = "Saved %d products, failed %d";

    // Client
    public static final String FETCHING_ALL_CLIENTS_CLIENT = "Fetching all clients from the system";
    public static final String FETCHING_CLIENT_BY_ID_CLIENT = "Fetching client by ID: {}";
    public static final String FETCHING_CLIENT_BY_NAME_CLIENT = "Fetching client by name: {}";
    public static final String SAVING_NEW_CLIENT_CLIENT = "Saving new client with ID: {}";
    public static final String CLIENT_SAVED_SUCCESSFULLY_CLIENT = "Client saved successfully with ID: {}";
    public static final String DELETING_CLIENT_CLIENT = "Deleting client with ID: {}";
    public static final String CLIENT_DELETED_SUCCESSFULLY_CLIENT = "Client deleted successfully: {}";
    public static final String UPDATING_CLIENT_CLIENT = "Updating client with ID: {}";
    public static final String CLIENT_UPDATED_SUCCESSFULLY_CLIENT = "Client updated successfully with ID: {}";

    // Branch
    public static final String SAVING_BRANCH = "Saving new branch: {}";
    public static final String BRANCH_SAVED_BRANCH = "Branch saved successfully with ID: {}";
    public static final String ERROR_SAVING_BRANCH_BRANCH = "Error saving branch: {}";
    public static final String FAILED_TO_SAVE_BRANCH_BRANCH = "Failed to save branch";
    public static final String FETCHING_SALES_BRANCH = "Fetching sales for branch ID: {}";
    public static final String NO_SALES_FOUND_BRANCH = "No sales found for branch: {}";
    public static final String SALES_SUMMARY_BRANCH = "Sales summary generated for branch: {}";

    // Other
    public static final String INVALID_DATE = "The date does not have a valid format.";
    public static final String INVALID_DATA = "Invalid data.";
    public static final String NO_MESSAGE_PROVIDED = "No message provided";
    public static final String ERROR_TEMPLATE = "Error occurred: {}";
    public static final String NO_USERS_FOUND_IN_SYSTEM = "No users found in the system";
    public static final String UNAUTHORIZED_ERROR = "Unauthorized error: {}";
    public static final String UNAUTHORIZED_ERROR_2 = "Error: Unauthorized";
    public static final String CANNOT_SET_AUTH = "Cannot set user authentication: {}";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String INVALID_JWT_SIGNATURE = "Invalid JWT signature: {}";
    public static final String INVALID_JWT_TOKEN = "Invalid JWT token: {}";
    public static final String JWT_TOKEN_EXPIRED = "JWT token is expired: {}";
    public static final String JWT_TOKEN_UNSUPPORTED = "JWT token is unsupported: {}";
    public static final String JWT_EMPTY = "JWT claims string is empty: {}";
    public static final String BRANCH_NOT_FOUND = "Branch not found with ID: ";
    public static final String PRODUCT_EXIST_MESSAGE = "A product with this code already exists: ";
    public static final String NO_CLIENT_FOUND_BY_ID = "No client found with the provided ID";
    public static final String NO_CLIENT_FOUND_BY_NAME = "No client found with the provided name";
    public static final String CLIENT_EXISTS_IN_DB = "A client with this ID already exists in the database";
    public static final String CONCURRENCY_TYPE_USD = "USD";
    public static final String CONCURRENCY_TYPE_COP = "COP";
    public static final Double CONCURRENCY_RATE = 4000.00;
    public static final String CONCURRENCY_ERROR_MESSAGE = "Could not get USD->COP exchange rate";
    public static final String SERVICE_DOWN = "Concurrency service is down";
}
