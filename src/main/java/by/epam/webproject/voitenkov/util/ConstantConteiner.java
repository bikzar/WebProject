package by.epam.webproject.voitenkov.util;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 24, 2019
 */
public class ConstantConteiner {

	// DB
	public static final String WAITING_TIME_SEC = "isValidTimeOut";
	public static final String POOL_SIZE = "poolSize";
//-----------------------------------------------------------------------------

	// Command
	public static final String GO_TO_HISTORY_PAGE_COMMAND = "GoToHistoryPageCommand";
	public static final String LOAD_BANK_ACCOUNT_COMMAND = "LoadBankAccount";
	public static final String BLOCK_CREDIT_CARD_COMMAND = "BlockCreditCard";
	public static final String REPLENISH_ACCOUNT_COMMAND = "ReplenishAccount";
	public static final String LOAD_PAY_HISTORY_COMAND = "LoadPayHistory";
	public static final String GO_TO_PAY_PAGE_COMMAND = "GoToPayPageCommand";
	public static final String REGISTRATION_COMMAND = "Registration";
	public static final String GO_TO_LOGIN_PAGE = "GoToLogInPage";
	public static final String DEFAULT_COMMAND = "Default";
	public static final String LOGOUT_COMMAND = "Logout";
	public static final String LOGIN_COMMAND = "Login";
	public static final String PAY_COMMAND = "Pay";
	public static final String COMMAND = "Command";
	
//-----------------------------------------------------------------------------

	// Path
	public static final String PATH_TO_INDEX_FOR_REDIRECT = "start";
	public static final String BANK_ACCOUNT_DETAILS_PAGE = "BankAccountDetailsPage";
	public static final String RIGHT_PAHT_FOR_REENTER = "RightReEnterPath";
	public static final String GO_REGISTRATION_PAGE = "RegistrationPage";
	public static final String GO_TO_REPLANISH_PAGE = "GoToReplanishPage";
	public static final String GO_TO_PAY_PAGE = "GoToPayPage";
	public static final String DEFAULT_PAGE = "DefaultPage";
	public static final String LOGIN_PAGE = "LoginPage";
	public static final String ERROR_PAGE = "ErrorPage";
	public static final String ADMIN_PAGE = "AdminPage";
	public static final String USER_PAGE = "UserPage";
	public static final String HISTORY_PAGE = "HistoryPage";

//-----------------------------------------------------------------------------

	// Query
	public static final String GET_USER_ID_BY_LOGIN = "GetUserIdByLogin";
	public static final String DELETE_USER_BY_ID = "DeleteUserById";
	public static final String GET_USER_PASWORD = "GetUserPassword";
	public static final String GET_USER_BY_ID = "GetUserById";
	public static final String UPDATE_USER = "UpdateUser";
	public static final String SAVE_USER = "SaveUser";

	public static final String GET_BANK_ACCOUNT_ID_BY_CREDIT_CARD_ID = "BankAccountIdByCreditCardId";
	public static final String GET_ALL_BANK_ACCOUNT_BY_ID = "GetAllBankAcoountById";
	public static final String DELETE_BANK_ACCOUNT_BY_ID = "DeleteBankAccountById";
	public static final String GET_BANK_ACCOUNT_BY_ID = "GetBankAccountById";
	public static final String UPDATE_BANK_ACCOUNT = "UpdateBankAccount";
	public static final String SAVE_BANK_ACCOUNT = "SaveBankAccount";

	public static final String BLOCK_CREDIT_CARD_BY_ID = "BlockCreditCardById";
	public static final String GET_CREDIT_CARD_BY_ID = "GetCreditCardById";
	public static final String GET_ALL_CREDIT_CARD_BY_BANK_ACCOUNT_ID = "GetAllCreditCardByBankAccountsId";
	public static final String SAVE_CREDIT_CARD = "SaveCreditCard";
	public static final String UPDATE_CREDIT_CARD = "UpdateCreditCard";
	public static final String DELETE_CREDIT_CARD = "DeleteCraditCard";

	public static final String SAVE_TRANSACTION = "SaveTransaction";
	public static final String DELETE_TRANSACTION = "DeleteTransaction";
	public static final String LOAD_HISTORY_QUERY = "LoadHistory";

//-----------------------------------------------------------------------------	

	// User constant
	public static final int NOT_ADMIN = 0;
	public static final int ADMIN = 1;
	public static final String USER = "User";

	// DB_NAMES
	public static final String DB_USER_SECOND_NAME = "DBSecondName";
	public static final String DB_USER_PASSWORD = "DBPassword";
	public static final String DB_BIRTH_DATE = "DBBirthDate";
	public static final String DB_USER_NAME = "DBName";
	public static final String DB_IS_ADMIN = "DBIsAdmin";
	public static final String DB_USER_ID = "DBUSerID";
	public static final String DB_LOGIN = "DBLogin";

	// FORM_NAMES
	public static final String F_USER_SECOND_NAME = "FSecondName";
	public static final String F_BIRTH_DATE = "FBirthDate";
	public static final String F_USER_NAME = "FName";
	public static final String F_IS_ADMIN = "FIsAdmin";
	public static final String F_PASSWORD = "FPassword";
	public static final String F_LOGIN = "FLogin";
//-----------------------------------------------------------------------------

	// Bank Account constant
	public static final String BANK_ACCOUNT_LIST = "BankAccountList";
	public static final String BANK_ACCOUNT = "BankAccount";

	// DB_NAMES
	public static final String DB_BANK_ACCOUNT_CURR_ABR = "DBBankAccountCurrencyAbr";
	public static final String DB_BANK_ACCOUNT_IS_BLOCK = "DBBankAccountIsBlock";
	public static final String DB_BANK_ACCOUNT_MONEY = "DBBankAccountMoney";
	public static final String DB_BANK_ACCOUNT_ID = "DBBankAccountID";

	// FORM_NAMES
	public static final String F_BANK_ACCOUNT_ID = "FBankAccountID";

//-----------------------------------------------------------------------------

	// Credit Card constant
	public static final String CREDIT_CARD_LIST = "CreditCardList";

	// DB_NAMES
	public static final String DB_CREDIT_CARD_IS_BLOCK = "DBCreditCardIsBlock";
	public static final String DB_CREDIT_CARD_ID = "DBCreditCardID";

	// FORM_NAMES
	public static final String F_DESTINATION_CREDIT_CARD_ID = "FDestionationCardId";
	public static final String F_CREDIT_CARD_ID = "FCreditCardID";

//-----------------------------------------------------------------------------
	// Transaction constant
	public static final String TRANSACTION_LIST = "TransactionList";
	

	// DB_NAMES
	public static final String DB_TRANSACTION_CURRENCY = "DBTransactionCurrency";
	public static final String DB_TRANSACTION_DATE = "DBTransactionDate";
	public static final String DB_DESTINATION_ID = "DBDestinationId";
	public static final String DB_OPERATION_TYPE = "DBOperationType";
	public static final String DB_OPERATION_SUM = "DBOperationSum";
	public static final String DB_SOURCE_ID = "DBSourceId";
	
//-----------------------------------------------------------------------------

	// Error message
	public static final String INCORRECT_USER_PASSWORD_MSG = "IncorrectUserPassword";
	public static final String DUPLICATE_USER_IN_DB_MSG = "DuplicateUserInDB";
	public static final String CANT_LOAD_PAY_FORM_MSG = "CantLoadPayForm";
	public static final String PAY_OPERATON_FAIL_MSG = "PayOperationFail";
	public static final String CANT_LOAD_HISTORY_MSG = "CantLoadHistory";
	public static final String CANT_REPLENIASH_MSG = "CantReplenish";
	public static final String INCORRECT_DATA_MSG = "IncorrectInputData";
	public static final String CANT_SAVE_USER_MSG = "CantSaveUser";
	public static final String SOME_PROBLEM_MSG = "SomeProblem";
	public static final String DB_PROBLEM_MSG = "DBProblem";
	
//-----------------------------------------------------------------------------

	// General constant
	public static final String REQUEST_ERROR_ATTRIBUTE_NAME = "RequestErrorAttribName";
	public static final String PART_MAIN_BANK_ACCOUNT_NAME = "PartMainBankAccountName";
	public static final String COMMISION_ATTREBUTE_NAME = "Commision";
	public static final String INNER_BYN_BANK_ACCOUNT = "MainBankAccuntIdBYN";
	public static final String URL_REGEX_FIRST_ENTER = "UrlRegexFirstEnter";
	public static final String TRANSACTION_COMMISION = "TransactionCommision";
	public static final String IS_AFTER_CALCULATION = "IsAfterCalculation";
	public static final String SERIALIZATION_PATH = "SerializationPath";
	public static final String DATE_TIME_PATTERN = "DateTimePattern";
	public static final String TRANSACTION_SUM = "TransactionSum";
	public static final String SALE_BYN_CONST = "saleBYN/";
	public static final String CONTENT_REGEX = "ContentRegex";
	public static final String BUY_BYN_CONST = "buyBYN/";
	public static final String SUM = "Sum";

//-----------------------------------------------------------------------------

	// System configuration
	public static final String CONFIG_FILE_NAME = "configurations.xml";
	public static final String UTF_8_ENCODING = "UTF-8";
//-----------------------------------------------------------------------------

	// Filter constant
	public static final String RU_LANGUAGE_ABRIV = "ru";
}
