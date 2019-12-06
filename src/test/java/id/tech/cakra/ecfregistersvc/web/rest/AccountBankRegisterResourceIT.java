package id.tech.cakra.ecfregistersvc.web.rest;

import id.tech.cakra.ecfregistersvc.EcfregistersvcApp;
import id.tech.cakra.ecfregistersvc.domain.AccountBankRegister;
import id.tech.cakra.ecfregistersvc.repository.AccountBankRegisterRepository;
import id.tech.cakra.ecfregistersvc.service.AccountBankRegisterService;
import id.tech.cakra.ecfregistersvc.service.dto.AccountBankRegisterDTO;
import id.tech.cakra.ecfregistersvc.service.mapper.AccountBankRegisterMapper;
import id.tech.cakra.ecfregistersvc.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.ecfregistersvc.web.rest.TestUtil.sameInstant;
import static id.tech.cakra.ecfregistersvc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AccountBankRegisterResource} REST controller.
 */
@SpringBootTest(classes = EcfregistersvcApp.class)
public class AccountBankRegisterResourceIT {

    private static final String DEFAULT_BANK_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final Long DEFAULT_CURRENCY_ID = 1L;
    private static final Long UPDATED_CURRENCY_ID = 2L;

    private static final Long DEFAULT_BANK_ID = 1L;
    private static final Long UPDATED_BANK_ID = 2L;

    private static final LocalDate DEFAULT_CREATE_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_CREATE_USER_ID = 1L;
    private static final Long UPDATED_CREATE_USER_ID = 2L;

    private static final LocalDate DEFAULT_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_LAST_MODIFICATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFICATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_LAST_MODIFICATION_USER_ID = 1L;
    private static final Long UPDATED_LAST_MODIFICATION_USER_ID = 2L;

    @Autowired
    private AccountBankRegisterRepository accountBankRegisterRepository;

    @Autowired
    private AccountBankRegisterMapper accountBankRegisterMapper;

    @Autowired
    private AccountBankRegisterService accountBankRegisterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAccountBankRegisterMockMvc;

    private AccountBankRegister accountBankRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountBankRegisterResource accountBankRegisterResource = new AccountBankRegisterResource(accountBankRegisterService);
        this.restAccountBankRegisterMockMvc = MockMvcBuilders.standaloneSetup(accountBankRegisterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountBankRegister createEntity(EntityManager em) {
        AccountBankRegister accountBankRegister = new AccountBankRegister()
            .bankAccountNo(DEFAULT_BANK_ACCOUNT_NO)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankBranch(DEFAULT_BANK_BRANCH)
            .status(DEFAULT_STATUS)
            .currencyId(DEFAULT_CURRENCY_ID)
            .bankId(DEFAULT_BANK_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountBankRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountBankRegister createUpdatedEntity(EntityManager em) {
        AccountBankRegister accountBankRegister = new AccountBankRegister()
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS)
            .currencyId(UPDATED_CURRENCY_ID)
            .bankId(UPDATED_BANK_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountBankRegister;
    }

    @BeforeEach
    public void initTest() {
        accountBankRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountBankRegister() throws Exception {
        int databaseSizeBeforeCreate = accountBankRegisterRepository.findAll().size();

        // Create the AccountBankRegister
        AccountBankRegisterDTO accountBankRegisterDTO = accountBankRegisterMapper.toDto(accountBankRegister);
        restAccountBankRegisterMockMvc.perform(post("/api/account-bank-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountBankRegister in the database
        List<AccountBankRegister> accountBankRegisterList = accountBankRegisterRepository.findAll();
        assertThat(accountBankRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        AccountBankRegister testAccountBankRegister = accountBankRegisterList.get(accountBankRegisterList.size() - 1);
        assertThat(testAccountBankRegister.getBankAccountNo()).isEqualTo(DEFAULT_BANK_ACCOUNT_NO);
        assertThat(testAccountBankRegister.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testAccountBankRegister.getBankBranch()).isEqualTo(DEFAULT_BANK_BRANCH);
        assertThat(testAccountBankRegister.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAccountBankRegister.getCurrencyId()).isEqualTo(DEFAULT_CURRENCY_ID);
        assertThat(testAccountBankRegister.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testAccountBankRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountBankRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountBankRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountBankRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountBankRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountBankRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountBankRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountBankRegisterRepository.findAll().size();

        // Create the AccountBankRegister with an existing ID
        accountBankRegister.setId(1L);
        AccountBankRegisterDTO accountBankRegisterDTO = accountBankRegisterMapper.toDto(accountBankRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountBankRegisterMockMvc.perform(post("/api/account-bank-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountBankRegister in the database
        List<AccountBankRegister> accountBankRegisterList = accountBankRegisterRepository.findAll();
        assertThat(accountBankRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountBankRegisters() throws Exception {
        // Initialize the database
        accountBankRegisterRepository.saveAndFlush(accountBankRegister);

        // Get all the accountBankRegisterList
        restAccountBankRegisterMockMvc.perform(get("/api/account-bank-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountBankRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankAccountNo").value(hasItem(DEFAULT_BANK_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].bankBranch").value(hasItem(DEFAULT_BANK_BRANCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].currencyId").value(hasItem(DEFAULT_CURRENCY_ID.intValue())))
            .andExpect(jsonPath("$.[*].bankId").value(hasItem(DEFAULT_BANK_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountBankRegister() throws Exception {
        // Initialize the database
        accountBankRegisterRepository.saveAndFlush(accountBankRegister);

        // Get the accountBankRegister
        restAccountBankRegisterMockMvc.perform(get("/api/account-bank-registers/{id}", accountBankRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountBankRegister.getId().intValue()))
            .andExpect(jsonPath("$.bankAccountNo").value(DEFAULT_BANK_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME))
            .andExpect(jsonPath("$.bankBranch").value(DEFAULT_BANK_BRANCH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.currencyId").value(DEFAULT_CURRENCY_ID.intValue()))
            .andExpect(jsonPath("$.bankId").value(DEFAULT_BANK_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountBankRegister() throws Exception {
        // Get the accountBankRegister
        restAccountBankRegisterMockMvc.perform(get("/api/account-bank-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountBankRegister() throws Exception {
        // Initialize the database
        accountBankRegisterRepository.saveAndFlush(accountBankRegister);

        int databaseSizeBeforeUpdate = accountBankRegisterRepository.findAll().size();

        // Update the accountBankRegister
        AccountBankRegister updatedAccountBankRegister = accountBankRegisterRepository.findById(accountBankRegister.getId()).get();
        // Disconnect from session so that the updates on updatedAccountBankRegister are not directly saved in db
        em.detach(updatedAccountBankRegister);
        updatedAccountBankRegister
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS)
            .currencyId(UPDATED_CURRENCY_ID)
            .bankId(UPDATED_BANK_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountBankRegisterDTO accountBankRegisterDTO = accountBankRegisterMapper.toDto(updatedAccountBankRegister);

        restAccountBankRegisterMockMvc.perform(put("/api/account-bank-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the AccountBankRegister in the database
        List<AccountBankRegister> accountBankRegisterList = accountBankRegisterRepository.findAll();
        assertThat(accountBankRegisterList).hasSize(databaseSizeBeforeUpdate);
        AccountBankRegister testAccountBankRegister = accountBankRegisterList.get(accountBankRegisterList.size() - 1);
        assertThat(testAccountBankRegister.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testAccountBankRegister.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testAccountBankRegister.getBankBranch()).isEqualTo(UPDATED_BANK_BRANCH);
        assertThat(testAccountBankRegister.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAccountBankRegister.getCurrencyId()).isEqualTo(UPDATED_CURRENCY_ID);
        assertThat(testAccountBankRegister.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testAccountBankRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountBankRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountBankRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountBankRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountBankRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountBankRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountBankRegister() throws Exception {
        int databaseSizeBeforeUpdate = accountBankRegisterRepository.findAll().size();

        // Create the AccountBankRegister
        AccountBankRegisterDTO accountBankRegisterDTO = accountBankRegisterMapper.toDto(accountBankRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountBankRegisterMockMvc.perform(put("/api/account-bank-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountBankRegister in the database
        List<AccountBankRegister> accountBankRegisterList = accountBankRegisterRepository.findAll();
        assertThat(accountBankRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountBankRegister() throws Exception {
        // Initialize the database
        accountBankRegisterRepository.saveAndFlush(accountBankRegister);

        int databaseSizeBeforeDelete = accountBankRegisterRepository.findAll().size();

        // Delete the accountBankRegister
        restAccountBankRegisterMockMvc.perform(delete("/api/account-bank-registers/{id}", accountBankRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountBankRegister> accountBankRegisterList = accountBankRegisterRepository.findAll();
        assertThat(accountBankRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
