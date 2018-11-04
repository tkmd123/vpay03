package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.PayPartnerLog;
import vn.vpay.repository.PayPartnerLogRepository;
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
 * Test class for the PayPartnerLogResource REST controller.
 *
 * @see PayPartnerLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class PayPartnerLogResourceIntTest {

    private static final String DEFAULT_PAY_LOG_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_TRANS_REF = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_TRANS_REF = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_PRODUCT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_PRODUCT_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_RESULE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_RESULE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_RESULE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_RESULE_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_PAY_LOG_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAY_LOG_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PAY_LOG_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_LOG_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_LOG_UDF_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private PayPartnerLogRepository payPartnerLogRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPayPartnerLogMockMvc;

    private PayPartnerLog payPartnerLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PayPartnerLogResource payPartnerLogResource = new PayPartnerLogResource(payPartnerLogRepository);
        this.restPayPartnerLogMockMvc = MockMvcBuilders.standaloneSetup(payPartnerLogResource)
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
    public static PayPartnerLog createEntity(EntityManager em) {
        PayPartnerLog payPartnerLog = new PayPartnerLog()
            .payLogAmount(DEFAULT_PAY_LOG_AMOUNT)
            .payLogTransRef(DEFAULT_PAY_LOG_TRANS_REF)
            .payLogUsername(DEFAULT_PAY_LOG_USERNAME)
            .payLogProductTypeCode(DEFAULT_PAY_LOG_PRODUCT_TYPE_CODE)
            .payLogResuleCode(DEFAULT_PAY_LOG_RESULE_CODE)
            .payLogResuleDesc(DEFAULT_PAY_LOG_RESULE_DESC)
            .payLogDate(DEFAULT_PAY_LOG_DATE)
            .payLogUDF1(DEFAULT_PAY_LOG_UDF_1)
            .payLogUDF2(DEFAULT_PAY_LOG_UDF_2)
            .payLogUDF3(DEFAULT_PAY_LOG_UDF_3)
            .payLogUDF4(DEFAULT_PAY_LOG_UDF_4)
            .payLogUDF5(DEFAULT_PAY_LOG_UDF_5)
            .isDeleted(DEFAULT_IS_DELETED);
        return payPartnerLog;
    }

    @Before
    public void initTest() {
        payPartnerLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createPayPartnerLog() throws Exception {
        int databaseSizeBeforeCreate = payPartnerLogRepository.findAll().size();

        // Create the PayPartnerLog
        restPayPartnerLogMockMvc.perform(post("/api/pay-partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartnerLog)))
            .andExpect(status().isCreated());

        // Validate the PayPartnerLog in the database
        List<PayPartnerLog> payPartnerLogList = payPartnerLogRepository.findAll();
        assertThat(payPartnerLogList).hasSize(databaseSizeBeforeCreate + 1);
        PayPartnerLog testPayPartnerLog = payPartnerLogList.get(payPartnerLogList.size() - 1);
        assertThat(testPayPartnerLog.getPayLogAmount()).isEqualTo(DEFAULT_PAY_LOG_AMOUNT);
        assertThat(testPayPartnerLog.getPayLogTransRef()).isEqualTo(DEFAULT_PAY_LOG_TRANS_REF);
        assertThat(testPayPartnerLog.getPayLogUsername()).isEqualTo(DEFAULT_PAY_LOG_USERNAME);
        assertThat(testPayPartnerLog.getPayLogProductTypeCode()).isEqualTo(DEFAULT_PAY_LOG_PRODUCT_TYPE_CODE);
        assertThat(testPayPartnerLog.getPayLogResuleCode()).isEqualTo(DEFAULT_PAY_LOG_RESULE_CODE);
        assertThat(testPayPartnerLog.getPayLogResuleDesc()).isEqualTo(DEFAULT_PAY_LOG_RESULE_DESC);
        assertThat(testPayPartnerLog.getPayLogDate()).isEqualTo(DEFAULT_PAY_LOG_DATE);
        assertThat(testPayPartnerLog.getPayLogUDF1()).isEqualTo(DEFAULT_PAY_LOG_UDF_1);
        assertThat(testPayPartnerLog.getPayLogUDF2()).isEqualTo(DEFAULT_PAY_LOG_UDF_2);
        assertThat(testPayPartnerLog.getPayLogUDF3()).isEqualTo(DEFAULT_PAY_LOG_UDF_3);
        assertThat(testPayPartnerLog.getPayLogUDF4()).isEqualTo(DEFAULT_PAY_LOG_UDF_4);
        assertThat(testPayPartnerLog.getPayLogUDF5()).isEqualTo(DEFAULT_PAY_LOG_UDF_5);
        assertThat(testPayPartnerLog.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createPayPartnerLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = payPartnerLogRepository.findAll().size();

        // Create the PayPartnerLog with an existing ID
        payPartnerLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayPartnerLogMockMvc.perform(post("/api/pay-partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartnerLog)))
            .andExpect(status().isBadRequest());

        // Validate the PayPartnerLog in the database
        List<PayPartnerLog> payPartnerLogList = payPartnerLogRepository.findAll();
        assertThat(payPartnerLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = payPartnerLogRepository.findAll().size();
        // set the field null
        payPartnerLog.setIsDeleted(null);

        // Create the PayPartnerLog, which fails.

        restPayPartnerLogMockMvc.perform(post("/api/pay-partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartnerLog)))
            .andExpect(status().isBadRequest());

        List<PayPartnerLog> payPartnerLogList = payPartnerLogRepository.findAll();
        assertThat(payPartnerLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPayPartnerLogs() throws Exception {
        // Initialize the database
        payPartnerLogRepository.saveAndFlush(payPartnerLog);

        // Get all the payPartnerLogList
        restPayPartnerLogMockMvc.perform(get("/api/pay-partner-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payPartnerLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].payLogAmount").value(hasItem(DEFAULT_PAY_LOG_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].payLogTransRef").value(hasItem(DEFAULT_PAY_LOG_TRANS_REF.toString())))
            .andExpect(jsonPath("$.[*].payLogUsername").value(hasItem(DEFAULT_PAY_LOG_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].payLogProductTypeCode").value(hasItem(DEFAULT_PAY_LOG_PRODUCT_TYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].payLogResuleCode").value(hasItem(DEFAULT_PAY_LOG_RESULE_CODE.toString())))
            .andExpect(jsonPath("$.[*].payLogResuleDesc").value(hasItem(DEFAULT_PAY_LOG_RESULE_DESC.toString())))
            .andExpect(jsonPath("$.[*].payLogDate").value(hasItem(DEFAULT_PAY_LOG_DATE.toString())))
            .andExpect(jsonPath("$.[*].payLogUDF1").value(hasItem(DEFAULT_PAY_LOG_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].payLogUDF2").value(hasItem(DEFAULT_PAY_LOG_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].payLogUDF3").value(hasItem(DEFAULT_PAY_LOG_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].payLogUDF4").value(hasItem(DEFAULT_PAY_LOG_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].payLogUDF5").value(hasItem(DEFAULT_PAY_LOG_UDF_5.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPayPartnerLog() throws Exception {
        // Initialize the database
        payPartnerLogRepository.saveAndFlush(payPartnerLog);

        // Get the payPartnerLog
        restPayPartnerLogMockMvc.perform(get("/api/pay-partner-logs/{id}", payPartnerLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(payPartnerLog.getId().intValue()))
            .andExpect(jsonPath("$.payLogAmount").value(DEFAULT_PAY_LOG_AMOUNT.toString()))
            .andExpect(jsonPath("$.payLogTransRef").value(DEFAULT_PAY_LOG_TRANS_REF.toString()))
            .andExpect(jsonPath("$.payLogUsername").value(DEFAULT_PAY_LOG_USERNAME.toString()))
            .andExpect(jsonPath("$.payLogProductTypeCode").value(DEFAULT_PAY_LOG_PRODUCT_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.payLogResuleCode").value(DEFAULT_PAY_LOG_RESULE_CODE.toString()))
            .andExpect(jsonPath("$.payLogResuleDesc").value(DEFAULT_PAY_LOG_RESULE_DESC.toString()))
            .andExpect(jsonPath("$.payLogDate").value(DEFAULT_PAY_LOG_DATE.toString()))
            .andExpect(jsonPath("$.payLogUDF1").value(DEFAULT_PAY_LOG_UDF_1.toString()))
            .andExpect(jsonPath("$.payLogUDF2").value(DEFAULT_PAY_LOG_UDF_2.toString()))
            .andExpect(jsonPath("$.payLogUDF3").value(DEFAULT_PAY_LOG_UDF_3.toString()))
            .andExpect(jsonPath("$.payLogUDF4").value(DEFAULT_PAY_LOG_UDF_4.toString()))
            .andExpect(jsonPath("$.payLogUDF5").value(DEFAULT_PAY_LOG_UDF_5.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPayPartnerLog() throws Exception {
        // Get the payPartnerLog
        restPayPartnerLogMockMvc.perform(get("/api/pay-partner-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePayPartnerLog() throws Exception {
        // Initialize the database
        payPartnerLogRepository.saveAndFlush(payPartnerLog);

        int databaseSizeBeforeUpdate = payPartnerLogRepository.findAll().size();

        // Update the payPartnerLog
        PayPartnerLog updatedPayPartnerLog = payPartnerLogRepository.findById(payPartnerLog.getId()).get();
        // Disconnect from session so that the updates on updatedPayPartnerLog are not directly saved in db
        em.detach(updatedPayPartnerLog);
        updatedPayPartnerLog
            .payLogAmount(UPDATED_PAY_LOG_AMOUNT)
            .payLogTransRef(UPDATED_PAY_LOG_TRANS_REF)
            .payLogUsername(UPDATED_PAY_LOG_USERNAME)
            .payLogProductTypeCode(UPDATED_PAY_LOG_PRODUCT_TYPE_CODE)
            .payLogResuleCode(UPDATED_PAY_LOG_RESULE_CODE)
            .payLogResuleDesc(UPDATED_PAY_LOG_RESULE_DESC)
            .payLogDate(UPDATED_PAY_LOG_DATE)
            .payLogUDF1(UPDATED_PAY_LOG_UDF_1)
            .payLogUDF2(UPDATED_PAY_LOG_UDF_2)
            .payLogUDF3(UPDATED_PAY_LOG_UDF_3)
            .payLogUDF4(UPDATED_PAY_LOG_UDF_4)
            .payLogUDF5(UPDATED_PAY_LOG_UDF_5)
            .isDeleted(UPDATED_IS_DELETED);

        restPayPartnerLogMockMvc.perform(put("/api/pay-partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPayPartnerLog)))
            .andExpect(status().isOk());

        // Validate the PayPartnerLog in the database
        List<PayPartnerLog> payPartnerLogList = payPartnerLogRepository.findAll();
        assertThat(payPartnerLogList).hasSize(databaseSizeBeforeUpdate);
        PayPartnerLog testPayPartnerLog = payPartnerLogList.get(payPartnerLogList.size() - 1);
        assertThat(testPayPartnerLog.getPayLogAmount()).isEqualTo(UPDATED_PAY_LOG_AMOUNT);
        assertThat(testPayPartnerLog.getPayLogTransRef()).isEqualTo(UPDATED_PAY_LOG_TRANS_REF);
        assertThat(testPayPartnerLog.getPayLogUsername()).isEqualTo(UPDATED_PAY_LOG_USERNAME);
        assertThat(testPayPartnerLog.getPayLogProductTypeCode()).isEqualTo(UPDATED_PAY_LOG_PRODUCT_TYPE_CODE);
        assertThat(testPayPartnerLog.getPayLogResuleCode()).isEqualTo(UPDATED_PAY_LOG_RESULE_CODE);
        assertThat(testPayPartnerLog.getPayLogResuleDesc()).isEqualTo(UPDATED_PAY_LOG_RESULE_DESC);
        assertThat(testPayPartnerLog.getPayLogDate()).isEqualTo(UPDATED_PAY_LOG_DATE);
        assertThat(testPayPartnerLog.getPayLogUDF1()).isEqualTo(UPDATED_PAY_LOG_UDF_1);
        assertThat(testPayPartnerLog.getPayLogUDF2()).isEqualTo(UPDATED_PAY_LOG_UDF_2);
        assertThat(testPayPartnerLog.getPayLogUDF3()).isEqualTo(UPDATED_PAY_LOG_UDF_3);
        assertThat(testPayPartnerLog.getPayLogUDF4()).isEqualTo(UPDATED_PAY_LOG_UDF_4);
        assertThat(testPayPartnerLog.getPayLogUDF5()).isEqualTo(UPDATED_PAY_LOG_UDF_5);
        assertThat(testPayPartnerLog.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingPayPartnerLog() throws Exception {
        int databaseSizeBeforeUpdate = payPartnerLogRepository.findAll().size();

        // Create the PayPartnerLog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayPartnerLogMockMvc.perform(put("/api/pay-partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartnerLog)))
            .andExpect(status().isBadRequest());

        // Validate the PayPartnerLog in the database
        List<PayPartnerLog> payPartnerLogList = payPartnerLogRepository.findAll();
        assertThat(payPartnerLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePayPartnerLog() throws Exception {
        // Initialize the database
        payPartnerLogRepository.saveAndFlush(payPartnerLog);

        int databaseSizeBeforeDelete = payPartnerLogRepository.findAll().size();

        // Get the payPartnerLog
        restPayPartnerLogMockMvc.perform(delete("/api/pay-partner-logs/{id}", payPartnerLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PayPartnerLog> payPartnerLogList = payPartnerLogRepository.findAll();
        assertThat(payPartnerLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayPartnerLog.class);
        PayPartnerLog payPartnerLog1 = new PayPartnerLog();
        payPartnerLog1.setId(1L);
        PayPartnerLog payPartnerLog2 = new PayPartnerLog();
        payPartnerLog2.setId(payPartnerLog1.getId());
        assertThat(payPartnerLog1).isEqualTo(payPartnerLog2);
        payPartnerLog2.setId(2L);
        assertThat(payPartnerLog1).isNotEqualTo(payPartnerLog2);
        payPartnerLog1.setId(null);
        assertThat(payPartnerLog1).isNotEqualTo(payPartnerLog2);
    }
}
