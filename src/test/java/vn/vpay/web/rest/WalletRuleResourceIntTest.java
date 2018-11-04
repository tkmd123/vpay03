package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.WalletRule;
import vn.vpay.repository.WalletRuleRepository;
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
 * Test class for the WalletRuleResource REST controller.
 *
 * @see WalletRuleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class WalletRuleResourceIntTest {

    private static final String DEFAULT_WALLET_RULE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_WALLET_RULE_FROM_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_WALLET_RULE_FROM_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_WALLET_RULE_TO_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_WALLET_RULE_TO_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_WALLET_RULE_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_RULE_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_RULE_UDF_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private WalletRuleRepository walletRuleRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWalletRuleMockMvc;

    private WalletRule walletRule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WalletRuleResource walletRuleResource = new WalletRuleResource(walletRuleRepository);
        this.restWalletRuleMockMvc = MockMvcBuilders.standaloneSetup(walletRuleResource)
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
    public static WalletRule createEntity(EntityManager em) {
        WalletRule walletRule = new WalletRule()
            .walletRuleCode(DEFAULT_WALLET_RULE_CODE)
            .walletRuleName(DEFAULT_WALLET_RULE_NAME)
            .walletRuleDesc(DEFAULT_WALLET_RULE_DESC)
            .walletRuleFromDate(DEFAULT_WALLET_RULE_FROM_DATE)
            .walletRuleToDate(DEFAULT_WALLET_RULE_TO_DATE)
            .walletRuleUDF1(DEFAULT_WALLET_RULE_UDF_1)
            .walletRuleUDF2(DEFAULT_WALLET_RULE_UDF_2)
            .walletRuleUDF3(DEFAULT_WALLET_RULE_UDF_3)
            .walletRuleUDF4(DEFAULT_WALLET_RULE_UDF_4)
            .walletRuleUDF5(DEFAULT_WALLET_RULE_UDF_5)
            .isDeleted(DEFAULT_IS_DELETED);
        return walletRule;
    }

    @Before
    public void initTest() {
        walletRule = createEntity(em);
    }

    @Test
    @Transactional
    public void createWalletRule() throws Exception {
        int databaseSizeBeforeCreate = walletRuleRepository.findAll().size();

        // Create the WalletRule
        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isCreated());

        // Validate the WalletRule in the database
        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeCreate + 1);
        WalletRule testWalletRule = walletRuleList.get(walletRuleList.size() - 1);
        assertThat(testWalletRule.getWalletRuleCode()).isEqualTo(DEFAULT_WALLET_RULE_CODE);
        assertThat(testWalletRule.getWalletRuleName()).isEqualTo(DEFAULT_WALLET_RULE_NAME);
        assertThat(testWalletRule.getWalletRuleDesc()).isEqualTo(DEFAULT_WALLET_RULE_DESC);
        assertThat(testWalletRule.getWalletRuleFromDate()).isEqualTo(DEFAULT_WALLET_RULE_FROM_DATE);
        assertThat(testWalletRule.getWalletRuleToDate()).isEqualTo(DEFAULT_WALLET_RULE_TO_DATE);
        assertThat(testWalletRule.getWalletRuleUDF1()).isEqualTo(DEFAULT_WALLET_RULE_UDF_1);
        assertThat(testWalletRule.getWalletRuleUDF2()).isEqualTo(DEFAULT_WALLET_RULE_UDF_2);
        assertThat(testWalletRule.getWalletRuleUDF3()).isEqualTo(DEFAULT_WALLET_RULE_UDF_3);
        assertThat(testWalletRule.getWalletRuleUDF4()).isEqualTo(DEFAULT_WALLET_RULE_UDF_4);
        assertThat(testWalletRule.getWalletRuleUDF5()).isEqualTo(DEFAULT_WALLET_RULE_UDF_5);
        assertThat(testWalletRule.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createWalletRuleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletRuleRepository.findAll().size();

        // Create the WalletRule with an existing ID
        walletRule.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        // Validate the WalletRule in the database
        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkWalletRuleCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRepository.findAll().size();
        // set the field null
        walletRule.setWalletRuleCode(null);

        // Create the WalletRule, which fails.

        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRepository.findAll().size();
        // set the field null
        walletRule.setWalletRuleName(null);

        // Create the WalletRule, which fails.

        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRepository.findAll().size();
        // set the field null
        walletRule.setWalletRuleFromDate(null);

        // Create the WalletRule, which fails.

        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWalletRuleToDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRepository.findAll().size();
        // set the field null
        walletRule.setWalletRuleToDate(null);

        // Create the WalletRule, which fails.

        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = walletRuleRepository.findAll().size();
        // set the field null
        walletRule.setIsDeleted(null);

        // Create the WalletRule, which fails.

        restWalletRuleMockMvc.perform(post("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWalletRules() throws Exception {
        // Initialize the database
        walletRuleRepository.saveAndFlush(walletRule);

        // Get all the walletRuleList
        restWalletRuleMockMvc.perform(get("/api/wallet-rules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(walletRule.getId().intValue())))
            .andExpect(jsonPath("$.[*].walletRuleCode").value(hasItem(DEFAULT_WALLET_RULE_CODE.toString())))
            .andExpect(jsonPath("$.[*].walletRuleName").value(hasItem(DEFAULT_WALLET_RULE_NAME.toString())))
            .andExpect(jsonPath("$.[*].walletRuleDesc").value(hasItem(DEFAULT_WALLET_RULE_DESC.toString())))
            .andExpect(jsonPath("$.[*].walletRuleFromDate").value(hasItem(DEFAULT_WALLET_RULE_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].walletRuleToDate").value(hasItem(DEFAULT_WALLET_RULE_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].walletRuleUDF1").value(hasItem(DEFAULT_WALLET_RULE_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].walletRuleUDF2").value(hasItem(DEFAULT_WALLET_RULE_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].walletRuleUDF3").value(hasItem(DEFAULT_WALLET_RULE_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].walletRuleUDF4").value(hasItem(DEFAULT_WALLET_RULE_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].walletRuleUDF5").value(hasItem(DEFAULT_WALLET_RULE_UDF_5.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWalletRule() throws Exception {
        // Initialize the database
        walletRuleRepository.saveAndFlush(walletRule);

        // Get the walletRule
        restWalletRuleMockMvc.perform(get("/api/wallet-rules/{id}", walletRule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(walletRule.getId().intValue()))
            .andExpect(jsonPath("$.walletRuleCode").value(DEFAULT_WALLET_RULE_CODE.toString()))
            .andExpect(jsonPath("$.walletRuleName").value(DEFAULT_WALLET_RULE_NAME.toString()))
            .andExpect(jsonPath("$.walletRuleDesc").value(DEFAULT_WALLET_RULE_DESC.toString()))
            .andExpect(jsonPath("$.walletRuleFromDate").value(DEFAULT_WALLET_RULE_FROM_DATE.toString()))
            .andExpect(jsonPath("$.walletRuleToDate").value(DEFAULT_WALLET_RULE_TO_DATE.toString()))
            .andExpect(jsonPath("$.walletRuleUDF1").value(DEFAULT_WALLET_RULE_UDF_1.toString()))
            .andExpect(jsonPath("$.walletRuleUDF2").value(DEFAULT_WALLET_RULE_UDF_2.toString()))
            .andExpect(jsonPath("$.walletRuleUDF3").value(DEFAULT_WALLET_RULE_UDF_3.toString()))
            .andExpect(jsonPath("$.walletRuleUDF4").value(DEFAULT_WALLET_RULE_UDF_4.toString()))
            .andExpect(jsonPath("$.walletRuleUDF5").value(DEFAULT_WALLET_RULE_UDF_5.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWalletRule() throws Exception {
        // Get the walletRule
        restWalletRuleMockMvc.perform(get("/api/wallet-rules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWalletRule() throws Exception {
        // Initialize the database
        walletRuleRepository.saveAndFlush(walletRule);

        int databaseSizeBeforeUpdate = walletRuleRepository.findAll().size();

        // Update the walletRule
        WalletRule updatedWalletRule = walletRuleRepository.findById(walletRule.getId()).get();
        // Disconnect from session so that the updates on updatedWalletRule are not directly saved in db
        em.detach(updatedWalletRule);
        updatedWalletRule
            .walletRuleCode(UPDATED_WALLET_RULE_CODE)
            .walletRuleName(UPDATED_WALLET_RULE_NAME)
            .walletRuleDesc(UPDATED_WALLET_RULE_DESC)
            .walletRuleFromDate(UPDATED_WALLET_RULE_FROM_DATE)
            .walletRuleToDate(UPDATED_WALLET_RULE_TO_DATE)
            .walletRuleUDF1(UPDATED_WALLET_RULE_UDF_1)
            .walletRuleUDF2(UPDATED_WALLET_RULE_UDF_2)
            .walletRuleUDF3(UPDATED_WALLET_RULE_UDF_3)
            .walletRuleUDF4(UPDATED_WALLET_RULE_UDF_4)
            .walletRuleUDF5(UPDATED_WALLET_RULE_UDF_5)
            .isDeleted(UPDATED_IS_DELETED);

        restWalletRuleMockMvc.perform(put("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWalletRule)))
            .andExpect(status().isOk());

        // Validate the WalletRule in the database
        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeUpdate);
        WalletRule testWalletRule = walletRuleList.get(walletRuleList.size() - 1);
        assertThat(testWalletRule.getWalletRuleCode()).isEqualTo(UPDATED_WALLET_RULE_CODE);
        assertThat(testWalletRule.getWalletRuleName()).isEqualTo(UPDATED_WALLET_RULE_NAME);
        assertThat(testWalletRule.getWalletRuleDesc()).isEqualTo(UPDATED_WALLET_RULE_DESC);
        assertThat(testWalletRule.getWalletRuleFromDate()).isEqualTo(UPDATED_WALLET_RULE_FROM_DATE);
        assertThat(testWalletRule.getWalletRuleToDate()).isEqualTo(UPDATED_WALLET_RULE_TO_DATE);
        assertThat(testWalletRule.getWalletRuleUDF1()).isEqualTo(UPDATED_WALLET_RULE_UDF_1);
        assertThat(testWalletRule.getWalletRuleUDF2()).isEqualTo(UPDATED_WALLET_RULE_UDF_2);
        assertThat(testWalletRule.getWalletRuleUDF3()).isEqualTo(UPDATED_WALLET_RULE_UDF_3);
        assertThat(testWalletRule.getWalletRuleUDF4()).isEqualTo(UPDATED_WALLET_RULE_UDF_4);
        assertThat(testWalletRule.getWalletRuleUDF5()).isEqualTo(UPDATED_WALLET_RULE_UDF_5);
        assertThat(testWalletRule.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingWalletRule() throws Exception {
        int databaseSizeBeforeUpdate = walletRuleRepository.findAll().size();

        // Create the WalletRule

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletRuleMockMvc.perform(put("/api/wallet-rules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(walletRule)))
            .andExpect(status().isBadRequest());

        // Validate the WalletRule in the database
        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWalletRule() throws Exception {
        // Initialize the database
        walletRuleRepository.saveAndFlush(walletRule);

        int databaseSizeBeforeDelete = walletRuleRepository.findAll().size();

        // Get the walletRule
        restWalletRuleMockMvc.perform(delete("/api/wallet-rules/{id}", walletRule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WalletRule> walletRuleList = walletRuleRepository.findAll();
        assertThat(walletRuleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletRule.class);
        WalletRule walletRule1 = new WalletRule();
        walletRule1.setId(1L);
        WalletRule walletRule2 = new WalletRule();
        walletRule2.setId(walletRule1.getId());
        assertThat(walletRule1).isEqualTo(walletRule2);
        walletRule2.setId(2L);
        assertThat(walletRule1).isNotEqualTo(walletRule2);
        walletRule1.setId(null);
        assertThat(walletRule1).isNotEqualTo(walletRule2);
    }
}
