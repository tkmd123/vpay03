package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.WalletRuleRate;
import vn.vpay.repository.WalletRuleRateRepository;
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
 * Test class for the WalletRuleRateResource REST controller.
 *
 * @see WalletRuleRateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class WalletRuleRateResourceIntTest {

    private static final String DEFAULT_WALLET_RULE_RATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_RATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_RATE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_WALLET_RULE_RATE_FROM_VALUE = 1L;
    private static final Long UPDATED_WALLET_RULE_RATE_FROM_VALUE = 2L;

    private static final Long DEFAULT_WALLET_RULE_RATE_TO_VALUE = 1L;
    private static final Long UPDATED_WALLET_RULE_RATE_TO_VALUE = 2L;

    private static final Long DEFAULT_WALLET_RULE_RATE_DISCOUNT = 1L;
    private static final Long UPDATED_WALLET_RULE_RATE_DISCOUNT = 2L;

    private static final String DEFAULT_WALLET_RULE_RATE_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_RATE_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_RATE_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_RATE_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_RATE_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_RATE_UDF_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private WalletRuleRateRepository walletRuleRateRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWalletRuleRateMockMvc;

    private WalletRuleRate walletRuleRate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WalletRuleRateResource walletRuleRateResource = new WalletRuleRateResource(walletRuleRateRepository);
        this.restWalletRuleRateMockMvc = MockMvcBuilders.standaloneSetup(walletRuleRateResource)
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
    public static WalletRuleRate createEntity(EntityManager em) {
        WalletRuleRate walletRuleRate = new WalletRuleRate()
            .walletRuleRateCode(DEFAULT_WALLET_RULE_RATE_CODE)
            .walletRuleRateName(DEFAULT_WALLET_RULE_RATE_NAME)
            .walletRuleRateDesc(DEFAULT_WALLET_RULE_RATE_DESC)
            .walletRuleRateFromValue(DEFAULT_WALLET_RULE_RATE_FROM_VALUE)
            .walletRuleRateToValue(DEFAULT_WALLET_RULE_RATE_TO_VALUE)
            .walletRuleRateDiscount(DEFAULT_WALLET_RULE_RATE_DISCOUNT)
            .walletRuleRateUDF1(DEFAULT_WALLET_RULE_RATE_UDF_1)
            .walletRuleRateUDF2(DEFAULT_WALLET_RULE_RATE_UDF_2)
            .walletRuleRateUDF3(DEFAULT_WALLET_RULE_RATE_UDF_3)
            .walletRuleRateUDF4(DEFAULT_WALLET_RULE_RATE_UDF_4)
            .walletRuleRateUDF5(DEFAULT_WALLET_RULE_RATE_UDF_5)
            .isDeleted(DEFAULT_IS_DELETED);
        return walletRuleRate;
    }

    @Before
    public void initTest() {
        walletRuleRate = createEntity(em);
    }

    @Test
    @Transactional
    public void createWalletRuleRate() throws Exception {
        int databaseSizeBeforeCreate = walletRuleRateRepository.findAll().size();

        // Create the WalletRuleRate
        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isCreated());

        // Validate the WalletRuleRate in the database
        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeCreate + 1);
        WalletRuleRate testWalletRuleRate = walletRuleRateList.get(walletRuleRateList.size() - 1);
        assertThat(testWalletRuleRate.getWalletRuleRateCode()).isEqualTo(DEFAULT_WALLET_RULE_RATE_CODE);
        assertThat(testWalletRuleRate.getWalletRuleRateName()).isEqualTo(DEFAULT_WALLET_RULE_RATE_NAME);
        assertThat(testWalletRuleRate.getWalletRuleRateDesc()).isEqualTo(DEFAULT_WALLET_RULE_RATE_DESC);
        assertThat(testWalletRuleRate.getWalletRuleRateFromValue()).isEqualTo(DEFAULT_WALLET_RULE_RATE_FROM_VALUE);
        assertThat(testWalletRuleRate.getWalletRuleRateToValue()).isEqualTo(DEFAULT_WALLET_RULE_RATE_TO_VALUE);
        assertThat(testWalletRuleRate.getWalletRuleRateDiscount()).isEqualTo(DEFAULT_WALLET_RULE_RATE_DISCOUNT);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF1()).isEqualTo(DEFAULT_WALLET_RULE_RATE_UDF_1);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF2()).isEqualTo(DEFAULT_WALLET_RULE_RATE_UDF_2);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF3()).isEqualTo(DEFAULT_WALLET_RULE_RATE_UDF_3);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF4()).isEqualTo(DEFAULT_WALLET_RULE_RATE_UDF_4);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF5()).isEqualTo(DEFAULT_WALLET_RULE_RATE_UDF_5);
        assertThat(testWalletRuleRate.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createWalletRuleRateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletRuleRateRepository.findAll().size();

        // Create the WalletRuleRate with an existing ID
        walletRuleRate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        // Validate the WalletRuleRate in the database
        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkWalletRuleRateCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRateRepository.findAll().size();
        // set the field null
        walletRuleRate.setWalletRuleRateCode(null);

        // Create the WalletRuleRate, which fails.

        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleRateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRateRepository.findAll().size();
        // set the field null
        walletRuleRate.setWalletRuleRateName(null);

        // Create the WalletRuleRate, which fails.

        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleRateFromValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRateRepository.findAll().size();
        // set the field null
        walletRuleRate.setWalletRuleRateFromValue(null);

        // Create the WalletRuleRate, which fails.

        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleRateToValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRateRepository.findAll().size();
        // set the field null
        walletRuleRate.setWalletRuleRateToValue(null);

        // Create the WalletRuleRate, which fails.

        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleRateDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRateRepository.findAll().size();
        // set the field null
        walletRuleRate.setWalletRuleRateDiscount(null);

        // Create the WalletRuleRate, which fails.

        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRateRepository.findAll().size();
        // set the field null
        walletRuleRate.setIsDeleted(null);

        // Create the WalletRuleRate, which fails.

        restWalletRuleRateMockMvc.perform(post("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWalletRuleRates() throws Exception {
        // Initialize the database
        walletRuleRateRepository.saveAndFlush(walletRuleRate);

        // Get all the walletRuleRateList
        restWalletRuleRateMockMvc.perform(get("/api/wallet-rule-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(walletRuleRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].walletRuleRateCode").value(hasItem(DEFAULT_WALLET_RULE_RATE_CODE.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateName").value(hasItem(DEFAULT_WALLET_RULE_RATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateDesc").value(hasItem(DEFAULT_WALLET_RULE_RATE_DESC.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateFromValue").value(hasItem(DEFAULT_WALLET_RULE_RATE_FROM_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].walletRuleRateToValue").value(hasItem(DEFAULT_WALLET_RULE_RATE_TO_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].walletRuleRateDiscount").value(hasItem(DEFAULT_WALLET_RULE_RATE_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].walletRuleRateUDF1").value(hasItem(DEFAULT_WALLET_RULE_RATE_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateUDF2").value(hasItem(DEFAULT_WALLET_RULE_RATE_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateUDF3").value(hasItem(DEFAULT_WALLET_RULE_RATE_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateUDF4").value(hasItem(DEFAULT_WALLET_RULE_RATE_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].walletRuleRateUDF5").value(hasItem(DEFAULT_WALLET_RULE_RATE_UDF_5.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWalletRuleRate() throws Exception {
        // Initialize the database
        walletRuleRateRepository.saveAndFlush(walletRuleRate);

        // Get the walletRuleRate
        restWalletRuleRateMockMvc.perform(get("/api/wallet-rule-rates/{id}", walletRuleRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(walletRuleRate.getId().intValue()))
            .andExpect(jsonPath("$.walletRuleRateCode").value(DEFAULT_WALLET_RULE_RATE_CODE.toString()))
            .andExpect(jsonPath("$.walletRuleRateName").value(DEFAULT_WALLET_RULE_RATE_NAME.toString()))
            .andExpect(jsonPath("$.walletRuleRateDesc").value(DEFAULT_WALLET_RULE_RATE_DESC.toString()))
            .andExpect(jsonPath("$.walletRuleRateFromValue").value(DEFAULT_WALLET_RULE_RATE_FROM_VALUE.intValue()))
            .andExpect(jsonPath("$.walletRuleRateToValue").value(DEFAULT_WALLET_RULE_RATE_TO_VALUE.intValue()))
            .andExpect(jsonPath("$.walletRuleRateDiscount").value(DEFAULT_WALLET_RULE_RATE_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.walletRuleRateUDF1").value(DEFAULT_WALLET_RULE_RATE_UDF_1.toString()))
            .andExpect(jsonPath("$.walletRuleRateUDF2").value(DEFAULT_WALLET_RULE_RATE_UDF_2.toString()))
            .andExpect(jsonPath("$.walletRuleRateUDF3").value(DEFAULT_WALLET_RULE_RATE_UDF_3.toString()))
            .andExpect(jsonPath("$.walletRuleRateUDF4").value(DEFAULT_WALLET_RULE_RATE_UDF_4.toString()))
            .andExpect(jsonPath("$.walletRuleRateUDF5").value(DEFAULT_WALLET_RULE_RATE_UDF_5.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWalletRuleRate() throws Exception {
        // Get the walletRuleRate
        restWalletRuleRateMockMvc.perform(get("/api/wallet-rule-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWalletRuleRate() throws Exception {
        // Initialize the database
        walletRuleRateRepository.saveAndFlush(walletRuleRate);

        int databaseSizeBeforeUpdate = walletRuleRateRepository.findAll().size();

        // Update the walletRuleRate
        WalletRuleRate updatedWalletRuleRate = walletRuleRateRepository.findById(walletRuleRate.getId()).get();
        // Disconnect from session so that the updates on updatedWalletRuleRate are not directly saved in db
        em.detach(updatedWalletRuleRate);
        updatedWalletRuleRate
            .walletRuleRateCode(UPDATED_WALLET_RULE_RATE_CODE)
            .walletRuleRateName(UPDATED_WALLET_RULE_RATE_NAME)
            .walletRuleRateDesc(UPDATED_WALLET_RULE_RATE_DESC)
            .walletRuleRateFromValue(UPDATED_WALLET_RULE_RATE_FROM_VALUE)
            .walletRuleRateToValue(UPDATED_WALLET_RULE_RATE_TO_VALUE)
            .walletRuleRateDiscount(UPDATED_WALLET_RULE_RATE_DISCOUNT)
            .walletRuleRateUDF1(UPDATED_WALLET_RULE_RATE_UDF_1)
            .walletRuleRateUDF2(UPDATED_WALLET_RULE_RATE_UDF_2)
            .walletRuleRateUDF3(UPDATED_WALLET_RULE_RATE_UDF_3)
            .walletRuleRateUDF4(UPDATED_WALLET_RULE_RATE_UDF_4)
            .walletRuleRateUDF5(UPDATED_WALLET_RULE_RATE_UDF_5)
            .isDeleted(UPDATED_IS_DELETED);

        restWalletRuleRateMockMvc.perform(put("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWalletRuleRate)))
            .andExpect(status().isOk());

        // Validate the WalletRuleRate in the database
        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeUpdate);
        WalletRuleRate testWalletRuleRate = walletRuleRateList.get(walletRuleRateList.size() - 1);
        assertThat(testWalletRuleRate.getWalletRuleRateCode()).isEqualTo(UPDATED_WALLET_RULE_RATE_CODE);
        assertThat(testWalletRuleRate.getWalletRuleRateName()).isEqualTo(UPDATED_WALLET_RULE_RATE_NAME);
        assertThat(testWalletRuleRate.getWalletRuleRateDesc()).isEqualTo(UPDATED_WALLET_RULE_RATE_DESC);
        assertThat(testWalletRuleRate.getWalletRuleRateFromValue()).isEqualTo(UPDATED_WALLET_RULE_RATE_FROM_VALUE);
        assertThat(testWalletRuleRate.getWalletRuleRateToValue()).isEqualTo(UPDATED_WALLET_RULE_RATE_TO_VALUE);
        assertThat(testWalletRuleRate.getWalletRuleRateDiscount()).isEqualTo(UPDATED_WALLET_RULE_RATE_DISCOUNT);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF1()).isEqualTo(UPDATED_WALLET_RULE_RATE_UDF_1);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF2()).isEqualTo(UPDATED_WALLET_RULE_RATE_UDF_2);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF3()).isEqualTo(UPDATED_WALLET_RULE_RATE_UDF_3);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF4()).isEqualTo(UPDATED_WALLET_RULE_RATE_UDF_4);
        assertThat(testWalletRuleRate.getWalletRuleRateUDF5()).isEqualTo(UPDATED_WALLET_RULE_RATE_UDF_5);
        assertThat(testWalletRuleRate.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingWalletRuleRate() throws Exception {
        int databaseSizeBeforeUpdate = walletRuleRateRepository.findAll().size();

        // Create the WalletRuleRate

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletRuleRateMockMvc.perform(put("/api/wallet-rule-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRuleRate)))
            .andExpect(status().isBadRequest());

        // Validate the WalletRuleRate in the database
        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWalletRuleRate() throws Exception {
        // Initialize the database
        walletRuleRateRepository.saveAndFlush(walletRuleRate);

        int databaseSizeBeforeDelete = walletRuleRateRepository.findAll().size();

        // Get the walletRuleRate
        restWalletRuleRateMockMvc.perform(delete("/api/wallet-rule-rates/{id}", walletRuleRate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WalletRuleRate> walletRuleRateList = walletRuleRateRepository.findAll();
        assertThat(walletRuleRateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletRuleRate.class);
        WalletRuleRate walletRuleRate1 = new WalletRuleRate();
        walletRuleRate1.setId(1L);
        WalletRuleRate walletRuleRate2 = new WalletRuleRate();
        walletRuleRate2.setId(walletRuleRate1.getId());
        assertThat(walletRuleRate1).isEqualTo(walletRuleRate2);
        walletRuleRate2.setId(2L);
        assertThat(walletRuleRate1).isNotEqualTo(walletRuleRate2);
        walletRuleRate1.setId(null);
        assertThat(walletRuleRate1).isNotEqualTo(walletRuleRate2);
    }
}
