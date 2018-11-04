package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.WalletTransaction;
import vn.vpay.repository.WalletTransactionRepository;
import vn.vpay.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static vn.vpay.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WalletTransactionResource REST controller.
 *
 * @see WalletTransactionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class WalletTransactionResourceIntTest {

    private static final Long DEFAULT_WALLET_TRANS_AMOUNT = 1L;
    private static final Long UPDATED_WALLET_TRANS_AMOUNT = 2L;

    private static final Instant DEFAULT_WALLET_TRANS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_WALLET_TRANS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_WALLET_TRANS_REF = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_REF = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_TRANS_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_TRANS_UDF_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWalletTransactionMockMvc;

    private WalletTransaction walletTransaction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WalletTransactionResource walletTransactionResource = new WalletTransactionResource(walletTransactionRepository);
        this.restWalletTransactionMockMvc = MockMvcBuilders.standaloneSetup(walletTransactionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WalletTransaction createEntity(EntityManager em) {
        WalletTransaction walletTransaction = new WalletTransaction()
            .walletTransAmount(DEFAULT_WALLET_TRANS_AMOUNT)
            .walletTransDate(DEFAULT_WALLET_TRANS_DATE)
            .walletTransRef(DEFAULT_WALLET_TRANS_REF)
            .walletTransUsername(DEFAULT_WALLET_TRANS_USERNAME)
            .walletTransStatus(DEFAULT_WALLET_TRANS_STATUS)
            .walletTransUDF1(DEFAULT_WALLET_TRANS_UDF_1)
            .walletTransUDF2(DEFAULT_WALLET_TRANS_UDF_2)
            .walletTransUDF3(DEFAULT_WALLET_TRANS_UDF_3)
            .walletTransUDF4(DEFAULT_WALLET_TRANS_UDF_4)
            .walletTransUDF5(DEFAULT_WALLET_TRANS_UDF_5)
            .isDeleted(DEFAULT_IS_DELETED);
        return walletTransaction;
    }

    @Before
    public void initTest() {
        walletTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createWalletTransaction() throws Exception {
        int databaseSizeBeforeCreate = walletTransactionRepository.findAll().size();

        // Create the WalletTransaction
        restWalletTransactionMockMvc.perform(post("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletTransaction)))
            .andExpect(status().isCreated());

        // Validate the WalletTransaction in the database
        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        WalletTransaction testWalletTransaction = walletTransactionList.get(walletTransactionList.size() - 1);
        assertThat(testWalletTransaction.getWalletTransAmount()).isEqualTo(DEFAULT_WALLET_TRANS_AMOUNT);
        assertThat(testWalletTransaction.getWalletTransDate()).isEqualTo(DEFAULT_WALLET_TRANS_DATE);
        assertThat(testWalletTransaction.getWalletTransRef()).isEqualTo(DEFAULT_WALLET_TRANS_REF);
        assertThat(testWalletTransaction.getWalletTransUsername()).isEqualTo(DEFAULT_WALLET_TRANS_USERNAME);
        assertThat(testWalletTransaction.getWalletTransStatus()).isEqualTo(DEFAULT_WALLET_TRANS_STATUS);
        assertThat(testWalletTransaction.getWalletTransUDF1()).isEqualTo(DEFAULT_WALLET_TRANS_UDF_1);
        assertThat(testWalletTransaction.getWalletTransUDF2()).isEqualTo(DEFAULT_WALLET_TRANS_UDF_2);
        assertThat(testWalletTransaction.getWalletTransUDF3()).isEqualTo(DEFAULT_WALLET_TRANS_UDF_3);
        assertThat(testWalletTransaction.getWalletTransUDF4()).isEqualTo(DEFAULT_WALLET_TRANS_UDF_4);
        assertThat(testWalletTransaction.getWalletTransUDF5()).isEqualTo(DEFAULT_WALLET_TRANS_UDF_5);
        assertThat(testWalletTransaction.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createWalletTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletTransactionRepository.findAll().size();

        // Create the WalletTransaction with an existing ID
        walletTransaction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletTransactionMockMvc.perform(post("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletTransaction)))
            .andExpect(status().isBadRequest());

        // Validate the WalletTransaction in the database
        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkWalletTransAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletTransactionRepository.findAll().size();
        // set the field null
        walletTransaction.setWalletTransAmount(null);

        // Create the WalletTransaction, which fails.

        restWalletTransactionMockMvc.perform(post("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletTransaction)))
            .andExpect(status().isBadRequest());

        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletTransDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletTransactionRepository.findAll().size();
        // set the field null
        walletTransaction.setWalletTransDate(null);

        // Create the WalletTransaction, which fails.

        restWalletTransactionMockMvc.perform(post("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletTransaction)))
            .andExpect(status().isBadRequest());

        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletTransactionRepository.findAll().size();
        // set the field null
        walletTransaction.setIsDeleted(null);

        // Create the WalletTransaction, which fails.

        restWalletTransactionMockMvc.perform(post("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletTransaction)))
            .andExpect(status().isBadRequest());

        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWalletTransactions() throws Exception {
        // Initialize the database
        walletTransactionRepository.saveAndFlush(walletTransaction);

        // Get all the walletTransactionList
        restWalletTransactionMockMvc.perform(get("/api/wallet-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(walletTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].walletTransAmount").value(hasItem(DEFAULT_WALLET_TRANS_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].walletTransDate").value(hasItem(DEFAULT_WALLET_TRANS_DATE.toString())))
            .andExpect(jsonPath("$.[*].walletTransRef").value(hasItem(DEFAULT_WALLET_TRANS_REF.toString())))
            .andExpect(jsonPath("$.[*].walletTransUsername").value(hasItem(DEFAULT_WALLET_TRANS_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].walletTransStatus").value(hasItem(DEFAULT_WALLET_TRANS_STATUS.toString())))
            .andExpect(jsonPath("$.[*].walletTransUDF1").value(hasItem(DEFAULT_WALLET_TRANS_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].walletTransUDF2").value(hasItem(DEFAULT_WALLET_TRANS_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].walletTransUDF3").value(hasItem(DEFAULT_WALLET_TRANS_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].walletTransUDF4").value(hasItem(DEFAULT_WALLET_TRANS_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].walletTransUDF5").value(hasItem(DEFAULT_WALLET_TRANS_UDF_5.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWalletTransaction() throws Exception {
        // Initialize the database
        walletTransactionRepository.saveAndFlush(walletTransaction);

        // Get the walletTransaction
        restWalletTransactionMockMvc.perform(get("/api/wallet-transactions/{id}", walletTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(walletTransaction.getId().intValue()))
            .andExpect(jsonPath("$.walletTransAmount").value(DEFAULT_WALLET_TRANS_AMOUNT.intValue()))
            .andExpect(jsonPath("$.walletTransDate").value(DEFAULT_WALLET_TRANS_DATE.toString()))
            .andExpect(jsonPath("$.walletTransRef").value(DEFAULT_WALLET_TRANS_REF.toString()))
            .andExpect(jsonPath("$.walletTransUsername").value(DEFAULT_WALLET_TRANS_USERNAME.toString()))
            .andExpect(jsonPath("$.walletTransStatus").value(DEFAULT_WALLET_TRANS_STATUS.toString()))
            .andExpect(jsonPath("$.walletTransUDF1").value(DEFAULT_WALLET_TRANS_UDF_1.toString()))
            .andExpect(jsonPath("$.walletTransUDF2").value(DEFAULT_WALLET_TRANS_UDF_2.toString()))
            .andExpect(jsonPath("$.walletTransUDF3").value(DEFAULT_WALLET_TRANS_UDF_3.toString()))
            .andExpect(jsonPath("$.walletTransUDF4").value(DEFAULT_WALLET_TRANS_UDF_4.toString()))
            .andExpect(jsonPath("$.walletTransUDF5").value(DEFAULT_WALLET_TRANS_UDF_5.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWalletTransaction() throws Exception {
        // Get the walletTransaction
        restWalletTransactionMockMvc.perform(get("/api/wallet-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWalletTransaction() throws Exception {
        // Initialize the database
        walletTransactionRepository.saveAndFlush(walletTransaction);

        int databaseSizeBeforeUpdate = walletTransactionRepository.findAll().size();

        // Update the walletTransaction
        WalletTransaction updatedWalletTransaction = walletTransactionRepository.findById(walletTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedWalletTransaction are not directly saved in db
        em.detach(updatedWalletTransaction);
        updatedWalletTransaction
            .walletTransAmount(UPDATED_WALLET_TRANS_AMOUNT)
            .walletTransDate(UPDATED_WALLET_TRANS_DATE)
            .walletTransRef(UPDATED_WALLET_TRANS_REF)
            .walletTransUsername(UPDATED_WALLET_TRANS_USERNAME)
            .walletTransStatus(UPDATED_WALLET_TRANS_STATUS)
            .walletTransUDF1(UPDATED_WALLET_TRANS_UDF_1)
            .walletTransUDF2(UPDATED_WALLET_TRANS_UDF_2)
            .walletTransUDF3(UPDATED_WALLET_TRANS_UDF_3)
            .walletTransUDF4(UPDATED_WALLET_TRANS_UDF_4)
            .walletTransUDF5(UPDATED_WALLET_TRANS_UDF_5)
            .isDeleted(UPDATED_IS_DELETED);

        restWalletTransactionMockMvc.perform(put("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWalletTransaction)))
            .andExpect(status().isOk());

        // Validate the WalletTransaction in the database
        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeUpdate);
        WalletTransaction testWalletTransaction = walletTransactionList.get(walletTransactionList.size() - 1);
        assertThat(testWalletTransaction.getWalletTransAmount()).isEqualTo(UPDATED_WALLET_TRANS_AMOUNT);
        assertThat(testWalletTransaction.getWalletTransDate()).isEqualTo(UPDATED_WALLET_TRANS_DATE);
        assertThat(testWalletTransaction.getWalletTransRef()).isEqualTo(UPDATED_WALLET_TRANS_REF);
        assertThat(testWalletTransaction.getWalletTransUsername()).isEqualTo(UPDATED_WALLET_TRANS_USERNAME);
        assertThat(testWalletTransaction.getWalletTransStatus()).isEqualTo(UPDATED_WALLET_TRANS_STATUS);
        assertThat(testWalletTransaction.getWalletTransUDF1()).isEqualTo(UPDATED_WALLET_TRANS_UDF_1);
        assertThat(testWalletTransaction.getWalletTransUDF2()).isEqualTo(UPDATED_WALLET_TRANS_UDF_2);
        assertThat(testWalletTransaction.getWalletTransUDF3()).isEqualTo(UPDATED_WALLET_TRANS_UDF_3);
        assertThat(testWalletTransaction.getWalletTransUDF4()).isEqualTo(UPDATED_WALLET_TRANS_UDF_4);
        assertThat(testWalletTransaction.getWalletTransUDF5()).isEqualTo(UPDATED_WALLET_TRANS_UDF_5);
        assertThat(testWalletTransaction.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingWalletTransaction() throws Exception {
        int databaseSizeBeforeUpdate = walletTransactionRepository.findAll().size();

        // Create the WalletTransaction

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletTransactionMockMvc.perform(put("/api/wallet-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletTransaction)))
            .andExpect(status().isBadRequest());

        // Validate the WalletTransaction in the database
        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWalletTransaction() throws Exception {
        // Initialize the database
        walletTransactionRepository.saveAndFlush(walletTransaction);

        int databaseSizeBeforeDelete = walletTransactionRepository.findAll().size();

        // Get the walletTransaction
        restWalletTransactionMockMvc.perform(delete("/api/wallet-transactions/{id}", walletTransaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WalletTransaction> walletTransactionList = walletTransactionRepository.findAll();
        assertThat(walletTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletTransaction.class);
        WalletTransaction walletTransaction1 = new WalletTransaction();
        walletTransaction1.setId(1L);
        WalletTransaction walletTransaction2 = new WalletTransaction();
        walletTransaction2.setId(walletTransaction1.getId());
        assertThat(walletTransaction1).isEqualTo(walletTransaction2);
        walletTransaction2.setId(2L);
        assertThat(walletTransaction1).isNotEqualTo(walletTransaction2);
        walletTransaction1.setId(null);
        assertThat(walletTransaction1).isNotEqualTo(walletTransaction2);
    }
}
