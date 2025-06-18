package com.mintic.genericstore.utils.constants;

public class ControllerConstants {
    // API PATHS
    public static final String BASE_URL = "/api/generic-store/";
    public static final String BRANCH_URL = "branch";
    public static final String CLIENT_URL = "client";
    public static final String S3 = "s3";
    public static final String PRODUCT_URL = "product";
    public static final String SALE_URL = "sale";
    public static final String SALE_DETAIL_URL = "saledetail";
    public static final String SUPPLIER_URL = "supplier";
    public static final String USER_URL = "user";
    public static final String AUTH_URL = "auth";

    // BRANCH
    public static final String SAVE_BRANCH_SUMMARY = "Create or update a branch";
    public static final String SAVE_BRANCH_DESCRIPTION = "Saves a new branch or updates an existing one based on the provided branch data.";
    public static final String SAVE_BRANCH_200 = "Branch saved successfully";
    public static final String SAVE_BRANCH_400 = "Invalid input data";
    public static final String SAVE_BRANCH_500 = "Internal server error";
    public static final String BRANCH_TAG_NAME_USER = "Branch Controller";
    public static final String BRANCH_TAG_DESCRIPTION_USER = "Operations related to branch management";

    // SALE
    public static final String GET_SALES_BY_BRANCH_SUMMARY = "Get sales by branch";
    public static final String GET_SALES_BY_BRANCH_DESCRIPTION = "Retrieves the list of sales for the specified branch.";
    public static final String GET_SALES_BY_BRANCH_200 = "Sales retrieved successfully";
    public static final String GET_SALES_BY_BRANCH_400 = "Invalid request body";
    public static final String GET_SALES_BY_BRANCH_500 = "Error processing the request";
    public static final String BRANCH_TAG_DESCRIPTION = "Operations related to branches and their sales";
    public static final String SALE_TAG_NAME_USER = "Sale Controller";
    public static final String SALE_TAG_DESCRIPTION_USER = "Operations related to sale management";

    // CLIENT
    public static final String SUMMARY_GET_ALL_CLIENTS = "Get all clients";
    public static final String DESC_GET_ALL_CLIENTS = "Retrieve a list of all clients records.";
    public static final String SUMMARY_GET_BY_ID = "Get client by ID";
    public static final String DESC_GET_BY_ID = "Retrieve a specific client by their identification number.";
    public static final String SUMMARY_SAVE = "Register a new client";
    public static final String DESC_SAVE = "Create a new client in the system.";
    public static final String RESP_OK_CLIENTS_RETRIEVED = "Clients retrieved successfully";
    public static final String RESP_OK_S3_RETRIEVED = "S3 info retrieved successfully";
    public static final String RESP_ERR_INTERNAL_SERVER = "Internal server error";
    public static final String RESP_OK_CLIENT_RETRIEVED = "Client retrieved successfully";
    public static final String RESP_ERR_CLIENT_NOT_FOUND = "Client not found";
    public static final String RESP_OK_CLIENT_CREATED = "Client created successfully";
    public static final String RESP_ERR_BAD_REQUEST = "Invalid input data";
    public static final String RESP_OK_CLIENT_UPDATED = "Client updated successfully";
    public static final String RESP_OK_CLIENT_DELETED = "Client deleted successfully";
    public static final String CLIENT_TAG_NAME_USER = "Client Controller";
    public static final String S3_TAG_NAME_USER = "S3 Controller";
    public static final String CLIENT_TAG_DESCRIPTION_USER = "Operations related to client management";
    public static final String S3_TAG_DESCRIPTION_USER = "Operations related to S3 management";

    // PRODUCT
    public static final String DESC_GET_ALL_PRODUCTS = "Retrieve a list of all available products.";
    public static final String DESC_GET_ONE_PRODUCTS = "Retrieve a single product by its unique code.";
    public static final String DESC_CREATE_PRODUCTS = "Add a new product to the system.";
    public static final String DESC_CREATE_ALL_PRODUCTS = "Add multiple products in a single operation.";
    public static final String DESC_UPDATE_PRODUCTS = "Update the data of an existing product.";
    public static final String DESC_DELETE_PRODUCTS = "Delete a product from the system using its code.";
    public static final String OK_ALL = "List of products returned successfully";
    public static final String OK_ONE = "Product found";
    public static final String OK_CREATED = "Product created successfully";
    public static final String OK_CREATED_ALL = "Products created successfully";
    public static final String OK_UPDATED = "Product updated successfully";
    public static final String OK_DELETED = "Product deleted successfully";
    public static final String ERROR_BAD_REQUEST = "Invalid input data";
    public static final String ERROR_NOT_FOUND = "Product not found";
    public static final String ERROR_INTERNAL = "Internal server error";
    public static final String PRODUCT_TAG_NAME_USER = "Product Controller";
    public static final String PRODUCT_TAG_DESCRIPTION_USER = "Operations related to product management";

    // SALE
    public static final String TAG_SALE_CONTROLLER_DESCRIPTION = "Operations related to sales management";
    public static final String GET_ALL_SALES_SUMMARY = "Get all sales";
    public static final String GET_ALL_SALES_DESCRIPTION = "Retrieve a list of all registered sales.";
    public static final String GET_SALE_BY_ID_SUMMARY = "Get sale by ID";
    public static final String GET_SALE_BY_ID_DESCRIPTION = "Retrieve a specific sale by its code.";
    public static final String CREATE_SALE_SUMMARY = "Create a new sale";
    public static final String CREATE_SALE_DESCRIPTION = "Register a new sale.";
    public static final String UPDATE_SALE_SUMMARY = "Update an existing sale";
    public static final String UPDATE_SALE_DESCRIPTION = "Modify the information of a registered sale.";
    public static final String DELETE_SALE_SUMMARY = "Delete a sale";
    public static final String DELETE_SALE_DESCRIPTION = "Delete a sale by its code.";
    public static final String RESPONSE_OK = "Sale successfully retrieved";
    public static final String RESPONSE_CREATED = "Sale successfully created";
    public static final String RESPONSE_UPDATED = "Sale successfully updated";
    public static final String RESPONSE_DELETED = "Sale successfully deleted";
    public static final String RESPONSE_BAD_REQUEST = "Invalid input";
    public static final String RESPONSE_NOT_FOUND = "Sale not found";
    public static final String RESPONSE_INTERNAL_ERROR = "Internal server error";
    public static final String SALE_TAG_NAME = "Sale Controller";
    public static final String SALE_TAG_DESCRIPTION = "Operations related to sale management";

    // SALE DETAIL
    public static final String SUMMARY_GET_ALL_SALE_DETAIL = "Get all sale details";
    public static final String DESC_GET_ALL_SALE_DETAIL = "Retrieve a list of all sale detail records.";
    public static final String APIRES_OK_GET_ALL_SALE_DETAIL = "Successfully retrieved all sale details";
    public static final String APIRES_BADREQ_GET_ALL_SALE_DETAIL = "Invalid request";
    public static final String APIRES_INTERNAL_ERR_SALE_DETAIL = "Internal server error";
    public static final String SUMMARY_GET_BY_CODE_SALE_DETAIL = "Get sale detail by sale code";
    public static final String DESC_GET_BY_CODE_SALE_DETAIL = "Retrieve a specific sale detail by its sale code.";
    public static final String APIRES_OK_GET_BY_CODE_SALE_DETAIL = "Successfully retrieved sale detail by sale code";
    public static final String APIRES_NOT_FOUND_SALE_DETAIL = "Sale detail not found";
    public static final String SUMMARY_CREATE_SALE_DETAIL = "Create sale detail records";
    public static final String DESC_CREATE_SALE_DETAIL = "Register multiple sale detail entries.";
    public static final String APIRES_OK_CREATE_SALE_DETAIL = "Successfully created sale details";
    public static final String APIRES_BADREQ_CREATE_SALE_DETAIL = "Invalid input data";
    public static final String SUMMARY_UPDATE_SALE_DETAIL = "Update a sale detail";
    public static final String DESC_UPDATE_SALE_DETAIL = "Update information of an existing sale detail record.";
    public static final String APIRES_OK_UPDATE_SALE_DETAIL = "Successfully updated sale detail";
    public static final String SUMMARY_DELETE_SALE_DETAIL = "Delete a sale detail";
    public static final String DESC_DELETE_SALE_DETAIL = "Delete a sale detail record by its sale code.";
    public static final String APIRES_OK_DELETE_SALE_DETAIL = "Successfully deleted sale detail";
    public static final String SALE_DETAIL_TAG_NAME = "Sale Detail Controller";
    public static final String SALE_DETAIL_TAG_DESCRIPTION = "Operations related to sale detail management";

    // SUPPLIER
    public static final String SUMMARY_GET_ALL_SUPPLIER = "Get all suppliers";
    public static final String DESC_GET_ALL_SUPPLIER = "Retrieve a list of all suppliers.";
    public static final String APIRES_OK_GET_ALL_SUPPLIER = "Successfully retrieved all suppliers";
    public static final String APIRES_INTERNAL_ERR_SUPPLIER = "Internal server error";
    public static final String SUMMARY_GET_BY_NIT_SUPPLIER = "Get supplier by NIT";
    public static final String DESC_GET_BY_NIT_SUPPLIER = "Retrieve a specific supplier by their NIT.";
    public static final String APIRES_OK_GET_BY_NIT_SUPPLIER = "Successfully retrieved supplier by NIT";
    public static final String APIRES_NOT_FOUND_SUPPLIER = "Supplier not found";
    public static final String SUMMARY_CREATE_SUPPLIER = "Create a new supplier";
    public static final String DESC_CREATE_SUPPLIER = "Register a new supplier.";
    public static final String APIRES_OK_CREATE_SUPPLIER = "Successfully created supplier";
    public static final String APIRES_BADREQ_CREATE_SUPPLIER = "Invalid input data";
    public static final String SUMMARY_UPDATE_SUPPLIER = "Update an existing supplier";
    public static final String DESC_UPDATE_SUPPLIER = "Update the details of an existing supplier.";
    public static final String APIRES_OK_UPDATE_SUPPLIER = "Successfully updated supplier";
    public static final String SUMMARY_DELETE_SUPPLIER = "Delete a supplier";
    public static final String DESC_DELETE_SUPPLIER = "Delete a supplier record by their NIT.";
    public static final String APIRES_OK_DELETE_SUPPLIER = "Successfully deleted supplier";
    public static final String SUPPLIER_TAG_NAME_SUPPLIER = "Supplier Controller";
    public static final String SUPPLIER_TAG_DESCRIPTION_SUPPLIER = "Operations related to supplier management";

    // USER
    public static final String SUMMARY_GET_ALL_USER = "Get all users_USER";
    public static final String DESC_GET_ALL_USER = "Retrieve a list of all users_USER.";
    public static final String APIRES_OK_GET_ALL_USER = "Successfully retrieved list of users_USER";
    public static final String APIRES_INTERNAL_ERR_USER = "Internal server error";
    public static final String SUMMARY_GET_BY_ID_USER = "Get a user by ID_USER";
    public static final String DESC_GET_BY_ID_USER = "Retrieve a specific user by their ID_USER.";
    public static final String APIRES_OK_GET_BY_ID_USER = "Successfully retrieved user_USER";
    public static final String APIRES_NOT_FOUND_USER = "User not found";
    public static final String SUMMARY_CREATE_USER = "Create a new user_USER";
    public static final String DESC_CREATE_USER = "Create a new user with the provided details_USER.";
    public static final String APIRES_OK_CREATE_USER = "Successfully created user_USER";
    public static final String APIRES_BADREQ_CREATE_USER = "Invalid input";
    public static final String SUMMARY_UPDATE_USER = "Update an existing user_USER";
    public static final String DESC_UPDATE_USER = "Update an existing user with new information_USER.";
    public static final String APIRES_OK_UPDATE_USER = "Successfully updated user_USER";
    public static final String SUMMARY_DELETE_USER = "Delete a user by ID_USER";
    public static final String DESC_DELETE_USER = "Delete a specific user by their ID_USER.";
    public static final String APIRES_OK_DELETE_USER = "Successfully deleted user_USER";
    public static final String USER_TAG_NAME_USER = "User Controller";
    public static final String USER_TAG_DESCRIPTION_USER = "Operations related to user management";

    // AUTH
    public static final String AUTHENTICATION = "Authentication";
    public static final String AUTHENTICATION_DESCRIPTION = "Endpoints for user login and JWT generation";
    public static final String AUTHENTICATION_SUMMARY = "Authenticate user and return JWT";
    public static final String AUTHENTICATION_INTERNAL_DESCRIPTION = "Authenticates user using username and password, returning a JWT on success.";
    public static final String AUTHENTICATION_OK = "User authenticated successfully";
    public static final String AUTHENTICATION_INVALID_CREDENTIALS = "Invalid credentials";
    public static final String LOGIN_CREDENTIALS = "Login credentials";

    // OTHER
    public static final String CROSS_ORIGIN_URL = "http://localhost:8080";
    public static final String FETCHING_EXCHANGE_RATE = "Fetching USD to COP exchange rate...";
    public static final String CLIENT_ERROR_HANDLE_4XX = "Client error while calling exchange rate service: Status={}, Body={}";
    public static final String CLIENT_ERROR_HANDLE_5XX = "Client error while calling exchange rate service: Status={}, Body={}";
    public static final String CLIENT_ERROR_MESSAGE = "Client error from exchange rate service: ";
    public static final String FETCHING_SUCCESSFULLY = "Successfully retrieved USD to COP rate: {}";
    public static final String FETCHING_NULL = "Exchange rate response was null";

    // Controller Tag
    public static final String CONTROLLER_TAG_NAME = "S3 Controller";
    public static final String CONTROLLER_TAG_DESCRIPTION = "Operations with Amazon S3 such as creating buckets, uploading, and downloading files";

    // Paths
    public static final String CREATE_PATH = "/create";
    public static final String CHECK_PATH = "/check/{bucketName}";
    public static final String LIST_PATH = "/list";
    public static final String UPLOAD_PATH = "/upload";
    public static final String DOWNLOAD_PATH = "/download";
    public static final String PRESIGNED_UPLOAD_PATH = "/upload/presigned";
    public static final String PRESIGNED_DOWNLOAD_PATH = "/download/presigned";

    // Operation summaries
    public static final String CREATE_BUCKET_SUMMARY = "Create S3 Bucket";
    public static final String CREATE_BUCKET_DESC = "Creates a new Amazon S3 bucket with the provided name.";

    public static final String CHECK_BUCKET_SUMMARY = "Check Bucket";
    public static final String CHECK_BUCKET_DESC = "Checks if a bucket exists in Amazon S3.";

    public static final String LIST_BUCKETS_SUMMARY = "List Buckets";
    public static final String LIST_BUCKETS_DESC = "Lists all existing buckets in Amazon S3.";

    public static final String UPLOAD_FILE_SUMMARY = "Upload File";
    public static final String UPLOAD_FILE_DESC = "Uploads a file to the specified bucket in Amazon S3.";

    public static final String DOWNLOAD_FILE_SUMMARY = "Download File";
    public static final String DOWNLOAD_FILE_DESC = "Downloads a file from Amazon S3.";

    public static final String PRESIGNED_UPLOAD_SUMMARY = "Generate Presigned Upload URL";
    public static final String PRESIGNED_UPLOAD_DESC = "Generates a presigned URL to upload files to S3.";

    public static final String PRESIGNED_DOWNLOAD_SUMMARY = "Generate Presigned Download URL";
    public static final String PRESIGNED_DOWNLOAD_DESC = "Generates a presigned URL to download files from S3.";

    // API response messages
    public static final String RESPONSE_200_BUCKET_CREATED = "Bucket created successfully";
    public static final String RESPONSE_200_BUCKET_CHECK = "Bucket existence check result";
    public static final String RESPONSE_200_BUCKET_LISTED = "Successfully retrieved bucket list";
    public static final String RESPONSE_200_FILE_UPLOADED = "File uploaded successfully";
    public static final String RESPONSE_200_FILE_DOWNLOADED = "File downloaded successfully";
    public static final String RESPONSE_200_PRESIGNED_UPLOAD = "Presigned upload URL generated successfully";
    public static final String RESPONSE_200_PRESIGNED_DOWNLOAD = "Presigned download URL generated successfully";

    public static final String RESPONSE_500_ERROR = "Internal server error";
    public static final String RESPONSE_500_UPLOAD_ERROR = "Error uploading the file";
    public static final String RESPONSE_500_DOWNLOAD_ERROR = "Error downloading the file";
    public static final String RESPONSE_500_PRESIGNED_ERROR = "Error generating presigned URL";

    // Parameter descriptions
    public static final String PARAM_BUCKET_NAME = "Name of the bucket";
    public static final String PARAM_BUCKET_NAME_CREATE = "Name of the bucket to create";
    public static final String PARAM_KEY = "Key or path where the file will be stored";
    public static final String PARAM_FILE = "File to upload";
    public static final String PARAM_TIME = "URL validity duration in minutes";
}
