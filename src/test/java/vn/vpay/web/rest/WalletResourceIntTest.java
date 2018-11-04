package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.Wallet;
import vn.vpay.repository.WalletRepository;
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
import java.util.List;


import static vn.vpay.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WalletResource REST controller.
 *
 * @see WalletResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class WalletResourceIntTest {

    private static final String DEFAULT_WALLET_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_WALLET_IS_ACTIVE = false;
    private static final Boolean UPDATED_WALLET_IS_ACTIVE = true;

    private static final String DEFAULT_WALLET_DESC = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_UDF_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWalletMockMvc;

    private Wallet wallet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WalletResource walletResource = new WalletResource(walletRepository);
        this.restWalletMockMvc = MockMvcBuilders.standaloneSetup(walletResource)
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
    public static Wallet createEntity(EntityManager em) {
        Wallet wallet = new Wallet()
            .walletNumber(DEFAULT_WALLET_NUMBER)
            .walletIsActive(DEFAULT_WALLET_IS_ACTIVE)
            .walletDesc(DEFAULT_WALLET_DESC)
            .walletUDF1(DEFAULT_WALLET_UDF_1)
            .walletUDF2(DEFAULT_WALLET_UDF_2)
            .walletUDF3(DEFAULT_WALLET_UDF_3)
            .walletUDF4(DEFAULT_WALLET_UDF_4)
            .walletUDF5(DEFAULT_WALLET_UDF_5)
            .isDeleted(DEFAULT_IS_DELETED);
        return wallet;
    }

    @Before
    public void initTest() {
        wallet = createEntity(em);
    }

    @Test
    @Transactional
    public void createWallet() throws Exception {
        int databaseSizeBeforeCreate = walletRepository.findAll().size();

        // Create the Wallet
        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isCreated());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeCreate + 1);
        Wallet testWallet = walletList.get(walletList.size() - 1);
        assertThat(testWallet.getWalletNumber()).isEqualTo(DEFAULT_WALLET_NUMBER);
        assertThat(testWallet.isWalletIsActive()).isEqualTo(DEFAULT_WALLET_IS_ACTIVE);
        assertThat(testWallet.getWalletDesc()).isEqualTo(DEFAULT_WALLET_DESC);
        assertThat(testWallet.getWalletUDF1()).isEqualTo(DEFAULT_WALLET_UDF_1);
        assertThat(testWallet.getWalletUDF2()).isEqualTo(DEFAULT_WALLET_UDF_2);
        assertThat(testWallet.getWalletUDF3()).isEqualTo(DEFAULT_WALLET_UDF_3);
        assertThat(testWallet.getWalletUDF4()).isEqualTo(DEFAULT_WALLET_UDF_4);
        assertThat(testWallet.getWalletUDF5()).isEqualTo(DEFAULT_WALLET_UDF_5);
        assertThat(testWallet.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createWalletWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletRepository.findAll().size();

        // Create the Wallet with an existing ID
        wallet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkWalletNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRepository.findAll().size();
        // set the field null
        wallet.setWalletNumber(null);

        // Create the Wallet, which fails.

        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRepository.findAll().size();
        // set the field null
        wallet.setWalletIsActive(null);

        // Create the Wallet, which fails.

        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRepository.findAll().size();
        // set the field null
        wallet.setIsDeleted(null);

        // Create the Wallet, which fails.

        restWalletMockMvc.perform(post("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWallets() throws Exception {
        // Initialize the database
        walletRepository.saveAndFlush(wallet);

        // Get all the walletList
        restWalletMockMvc.perform(get("/api/wallets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wallet.getId().intValue())))
            .andExpect(jsonPath("$.[*].walletNumber").value(hasItem(DEFAULT_WALLET_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].walletIsActive").value(hasItem(DEFAULT_WALLET_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].walletDesc").value(hasItem(DEFAULT_WALLET_DESC.toString())))
            .andExpect(jsonPath("$.[*].walletUDF1").value(hasItem(DEFAULT_WALLET_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].walletUDF2").value(hasItem(DEFAULT_WALLET_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].walletUDF3").value(hasItem(DEFAULT_WALLET_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].walletUDF4").value(hasItem(DEFAULT_WALLET_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].walletUDF5").value(hasItem(DEFAULT_WALLET_UDF_5.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWallet() throws Exception {
        // Initialize the database
        walletRepository.saveAndFlush(wallet);

        // Get the wallet
        restWalletMockMvc.perform(get("/api/wallets/{id}", wallet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wallet.getId().intValue()))
            .andExpect(jsonPath("$.walletNumber").value(DEFAULT_WALLET_NUMBER.toString()))
            .andExpect(jsonPath("$.walletIsActive").value(DEFAULT_WALLET_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.walletDesc").value(DEFAULT_WALLET_DESC.toString()))
            .andExpect(jsonPath("$.walletUDF1").value(DEFAULT_WALLET_UDF_1.toString()))
            .andExpect(jsonPath("$.walletUDF2").value(DEFAULT_WALLET_UDF_2.toString()))
            .andExpect(jsonPath("$.walletUDF3").value(DEFAULT_WALLET_UDF_3.toString()))
            .andExpect(jsonPath("$.walletUDF4").value(DEFAULT_WALLET_UDF_4.toString()))
            .andExpect(jsonPath("$.walletUDF5").value(DEFAULT_WALLET_UDF_5.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWallet() throws Exception {
        // Get the wallet
        restWalletMockMvc.perform(get("/api/wallets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWallet() throws Exception {
        // Initialize the database
        walletRepository.saveAndFlush(wallet);

        int databaseSizeBeforeUpdate = walletRepository.findAll().size();

        // Update the wallet
        Wallet updatedWallet = walletRepository.findById(wallet.getId()).get();
        // Disconnect from session so that the updates on updatedWallet are not directly saved in db
        em.detach(updatedWallet);
        updatedWallet
            .walletNumber(UPDATED_WALLET_NUMBER)
            .walletIsActive(UPDATED_WALLET_IS_ACTIVE)
            .walletDesc(UPDATED_WALLET_DESC)
            .walletUDF1(UPDATED_WALLET_UDF_1)
            .walletUDF2(UPDATED_WALLET_UDF_2)
            .walletUDF3(UPDATED_WALLET_UDF_3)
            .walletUDF4(UPDATED_WALLET_UDF_4)
            .walletUDF5(UPDATED_WALLET_UDF_5)
            .isDeleted(UPDATED_IS_DELETED);

        restWalletMockMvc.perform(put("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWallet)))
            .andExpect(status().isOk());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeUpdate);
        Wallet testWallet = walletList.get(walletList.size() - 1);
        assertThat(testWallet.getWalletNumber()).isEqualTo(UPDATED_WALLET_NUMBER);
        assertThat(testWallet.isWalletIsActive()).isEqualTo(UPDATED_WALLET_IS_ACTIVE);
        assertThat(testWallet.getWalletDesc()).isEqualTo(UPDATED_WALLET_DESC);
        assertThat(testWallet.getWalletUDF1()).isEqualTo(UPDATED_WALLET_UDF_1);
        assertThat(testWallet.getWalletUDF2()).isEqualTo(UPDATED_WALLET_UDF_2);
        assertThat(testWallet.getWalletUDF3()).isEqualTo(UPDATED_WALLET_UDF_3);
        assertThat(testWallet.getWalletUDF4()).isEqualTo(UPDATED_WALLET_UDF_4);
        assertThat(testWallet.getWalletUDF5()).isEqualTo(UPDATED_WALLET_UDF_5);
        assertThat(testWallet.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingWallet() throws Exception {
        int databaseSizeBeforeUpdate = walletRepository.findAll().size();

        // Create the Wallet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletMockMvc.perform(put("/api/wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wallet)))
            .andExpect(status().isBadRequest());

        // Validate the Wallet in the database
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWallet() throws Exception {
        // Initialize the database
        walletRepository.saveAndFlush(wallet);

        int databaseSizeBeforeDelete = walletRepository.findAll().size();

        // Get the wallet
        restWalletMockMvc.perform(delete("/api/wallets/{id}", wallet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wallet> walletList = walletRepository.findAll();
        assertThat(walletList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wallet.class);
        Wallet wallet1 = new Wallet();
        wallet1.setId(1L);
        Wallet wallet2 = new Wallet();
        wallet2.setId(wallet1.getId());
        assertThat(wallet1).isEqualTo(wallet2);
        wallet2.setId(2L);
        assertThat(wallet1).isNotEqualTo(wallet2);
        wallet1.setId(null);
        assertThat(wallet1).isNotEqualTo(wallet2);
    }
}
