package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.PartnerLog;
import vn.vpay.repository.PartnerLogRepository;
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
 * Test class for the PartnerLogResource REST controller.
 *
 * @see PartnerLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class PartnerLogResourceIntTest {

    private static final String DEFAULT_PARTNER_LOG_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_TRANS_REF = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_TRANS_REF = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_PRODUCT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_PRODUCT_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_RESULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_RESULT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_RESULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_RESULT_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_PARTNER_LOG_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PARTNER_LOG_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PARTNER_LOG_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_LOG_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_LOG_UDF_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private PartnerLogRepository partnerLogRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPartnerLogMockMvc;

    private PartnerLog partnerLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PartnerLogResource partnerLogResource = new PartnerLogResource(partnerLogRepository);
        this.restPartnerLogMockMvc = MockMvcBuilders.standaloneSetup(partnerLogResource)
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
    public static PartnerLog createEntity(EntityManager em) {
        PartnerLog partnerLog = new PartnerLog()
            .partnerLogAmount(DEFAULT_PARTNER_LOG_AMOUNT)
            .partnerLogTransRef(DEFAULT_PARTNER_LOG_TRANS_REF)
            .partnerLogUsername(DEFAULT_PARTNER_LOG_USERNAME)
            .partnerLogProductTypeCode(DEFAULT_PARTNER_LOG_PRODUCT_TYPE_CODE)
            .partnerLogResultCode(DEFAULT_PARTNER_LOG_RESULT_CODE)
            .partnerLogResultDesc(DEFAULT_PARTNER_LOG_RESULT_DESC)
            .partnerLogDate(DEFAULT_PARTNER_LOG_DATE)
            .partnerLogUDF1(DEFAULT_PARTNER_LOG_UDF_1)
            .partnerLogUDF2(DEFAULT_PARTNER_LOG_UDF_2)
            .partnerLogUDF3(DEFAULT_PARTNER_LOG_UDF_3)
            .partnerLogUDF4(DEFAULT_PARTNER_LOG_UDF_4)
            .partnerLogUDF5(DEFAULT_PARTNER_LOG_UDF_5)
            .isDeleted(DEFAULT_IS_DELETED);
        return partnerLog;
    }

    @Before
    public void initTest() {
        partnerLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartnerLog() throws Exception {
        int databaseSizeBeforeCreate = partnerLogRepository.findAll().size();

        // Create the PartnerLog
        restPartnerLogMockMvc.perform(post("/api/partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerLog)))
            .andExpect(status().isCreated());

        // Validate the PartnerLog in the database
        List<PartnerLog> partnerLogList = partnerLogRepository.findAll();
        assertThat(partnerLogList).hasSize(databaseSizeBeforeCreate + 1);
        PartnerLog testPartnerLog = partnerLogList.get(partnerLogList.size() - 1);
        assertThat(testPartnerLog.getPartnerLogAmount()).isEqualTo(DEFAULT_PARTNER_LOG_AMOUNT);
        assertThat(testPartnerLog.getPartnerLogTransRef()).isEqualTo(DEFAULT_PARTNER_LOG_TRANS_REF);
        assertThat(testPartnerLog.getPartnerLogUsername()).isEqualTo(DEFAULT_PARTNER_LOG_USERNAME);
        assertThat(testPartnerLog.getPartnerLogProductTypeCode()).isEqualTo(DEFAULT_PARTNER_LOG_PRODUCT_TYPE_CODE);
        assertThat(testPartnerLog.getPartnerLogResultCode()).isEqualTo(DEFAULT_PARTNER_LOG_RESULT_CODE);
        assertThat(testPartnerLog.getPartnerLogResultDesc()).isEqualTo(DEFAULT_PARTNER_LOG_RESULT_DESC);
        assertThat(testPartnerLog.getPartnerLogDate()).isEqualTo(DEFAULT_PARTNER_LOG_DATE);
        assertThat(testPartnerLog.getPartnerLogUDF1()).isEqualTo(DEFAULT_PARTNER_LOG_UDF_1);
        assertThat(testPartnerLog.getPartnerLogUDF2()).isEqualTo(DEFAULT_PARTNER_LOG_UDF_2);
        assertThat(testPartnerLog.getPartnerLogUDF3()).isEqualTo(DEFAULT_PARTNER_LOG_UDF_3);
        assertThat(testPartnerLog.getPartnerLogUDF4()).isEqualTo(DEFAULT_PARTNER_LOG_UDF_4);
        assertThat(testPartnerLog.getPartnerLogUDF5()).isEqualTo(DEFAULT_PARTNER_LOG_UDF_5);
        assertThat(testPartnerLog.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createPartnerLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partnerLogRepository.findAll().size();

        // Create the PartnerLog with an existing ID
        partnerLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartnerLogMockMvc.perform(post("/api/partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerLog)))
            .andExpect(status().isBadRequest());

        // Validate the PartnerLog in the database
        List<PartnerLog> partnerLogList = partnerLogRepository.findAll();
        assertThat(partnerLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerLogRepository.findAll().size();
        // set the field null
        partnerLog.setIsDeleted(null);

        // Create the PartnerLog, which fails.

        restPartnerLogMockMvc.perform(post("/api/partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerLog)))
            .andExpect(status().isBadRequest());

        List<PartnerLog> partnerLogList = partnerLogRepository.findAll();
        assertThat(partnerLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPartnerLogs() throws Exception {
        // Initialize the database
        partnerLogRepository.saveAndFlush(partnerLog);

        // Get all the partnerLogList
        restPartnerLogMockMvc.perform(get("/api/partner-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partnerLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].partnerLogAmount").value(hasItem(DEFAULT_PARTNER_LOG_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].partnerLogTransRef").value(hasItem(DEFAULT_PARTNER_LOG_TRANS_REF.toString())))
            .andExpect(jsonPath("$.[*].partnerLogUsername").value(hasItem(DEFAULT_PARTNER_LOG_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].partnerLogProductTypeCode").value(hasItem(DEFAULT_PARTNER_LOG_PRODUCT_TYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].partnerLogResultCode").value(hasItem(DEFAULT_PARTNER_LOG_RESULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].partnerLogResultDesc").value(hasItem(DEFAULT_PARTNER_LOG_RESULT_DESC.toString())))
            .andExpect(jsonPath("$.[*].partnerLogDate").value(hasItem(DEFAULT_PARTNER_LOG_DATE.toString())))
            .andExpect(jsonPath("$.[*].partnerLogUDF1").value(hasItem(DEFAULT_PARTNER_LOG_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].partnerLogUDF2").value(hasItem(DEFAULT_PARTNER_LOG_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].partnerLogUDF3").value(hasItem(DEFAULT_PARTNER_LOG_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].partnerLogUDF4").value(hasItem(DEFAULT_PARTNER_LOG_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].partnerLogUDF5").value(hasItem(DEFAULT_PARTNER_LOG_UDF_5.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPartnerLog() throws Exception {
        // Initialize the database
        partnerLogRepository.saveAndFlush(partnerLog);

        // Get the partnerLog
        restPartnerLogMockMvc.perform(get("/api/partner-logs/{id}", partnerLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(partnerLog.getId().intValue()))
            .andExpect(jsonPath("$.partnerLogAmount").value(DEFAULT_PARTNER_LOG_AMOUNT.toString()))
            .andExpect(jsonPath("$.partnerLogTransRef").value(DEFAULT_PARTNER_LOG_TRANS_REF.toString()))
            .andExpect(jsonPath("$.partnerLogUsername").value(DEFAULT_PARTNER_LOG_USERNAME.toString()))
            .andExpect(jsonPath("$.partnerLogProductTypeCode").value(DEFAULT_PARTNER_LOG_PRODUCT_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.partnerLogResultCode").value(DEFAULT_PARTNER_LOG_RESULT_CODE.toString()))
            .andExpect(jsonPath("$.partnerLogResultDesc").value(DEFAULT_PARTNER_LOG_RESULT_DESC.toString()))
            .andExpect(jsonPath("$.partnerLogDate").value(DEFAULT_PARTNER_LOG_DATE.toString()))
            .andExpect(jsonPath("$.partnerLogUDF1").value(DEFAULT_PARTNER_LOG_UDF_1.toString()))
            .andExpect(jsonPath("$.partnerLogUDF2").value(DEFAULT_PARTNER_LOG_UDF_2.toString()))
            .andExpect(jsonPath("$.partnerLogUDF3").value(DEFAULT_PARTNER_LOG_UDF_3.toString()))
            .andExpect(jsonPath("$.partnerLogUDF4").value(DEFAULT_PARTNER_LOG_UDF_4.toString()))
            .andExpect(jsonPath("$.partnerLogUDF5").value(DEFAULT_PARTNER_LOG_UDF_5.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPartnerLog() throws Exception {
        // Get the partnerLog
        restPartnerLogMockMvc.perform(get("/api/partner-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartnerLog() throws Exception {
        // Initialize the database
        partnerLogRepository.saveAndFlush(partnerLog);

        int databaseSizeBeforeUpdate = partnerLogRepository.findAll().size();

        // Update the partnerLog
        PartnerLog updatedPartnerLog = partnerLogRepository.findById(partnerLog.getId()).get();
        // Disconnect from session so that the updates on updatedPartnerLog are not directly saved in db
        em.detach(updatedPartnerLog);
        updatedPartnerLog
            .partnerLogAmount(UPDATED_PARTNER_LOG_AMOUNT)
            .partnerLogTransRef(UPDATED_PARTNER_LOG_TRANS_REF)
            .partnerLogUsername(UPDATED_PARTNER_LOG_USERNAME)
            .partnerLogProductTypeCode(UPDATED_PARTNER_LOG_PRODUCT_TYPE_CODE)
            .partnerLogResultCode(UPDATED_PARTNER_LOG_RESULT_CODE)
            .partnerLogResultDesc(UPDATED_PARTNER_LOG_RESULT_DESC)
            .partnerLogDate(UPDATED_PARTNER_LOG_DATE)
            .partnerLogUDF1(UPDATED_PARTNER_LOG_UDF_1)
            .partnerLogUDF2(UPDATED_PARTNER_LOG_UDF_2)
            .partnerLogUDF3(UPDATED_PARTNER_LOG_UDF_3)
            .partnerLogUDF4(UPDATED_PARTNER_LOG_UDF_4)
            .partnerLogUDF5(UPDATED_PARTNER_LOG_UDF_5)
            .isDeleted(UPDATED_IS_DELETED);

        restPartnerLogMockMvc.perform(put("/api/partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPartnerLog)))
            .andExpect(status().isOk());

        // Validate the PartnerLog in the database
        List<PartnerLog> partnerLogList = partnerLogRepository.findAll();
        assertThat(partnerLogList).hasSize(databaseSizeBeforeUpdate);
        PartnerLog testPartnerLog = partnerLogList.get(partnerLogList.size() - 1);
        assertThat(testPartnerLog.getPartnerLogAmount()).isEqualTo(UPDATED_PARTNER_LOG_AMOUNT);
        assertThat(testPartnerLog.getPartnerLogTransRef()).isEqualTo(UPDATED_PARTNER_LOG_TRANS_REF);
        assertThat(testPartnerLog.getPartnerLogUsername()).isEqualTo(UPDATED_PARTNER_LOG_USERNAME);
        assertThat(testPartnerLog.getPartnerLogProductTypeCode()).isEqualTo(UPDATED_PARTNER_LOG_PRODUCT_TYPE_CODE);
        assertThat(testPartnerLog.getPartnerLogResultCode()).isEqualTo(UPDATED_PARTNER_LOG_RESULT_CODE);
        assertThat(testPartnerLog.getPartnerLogResultDesc()).isEqualTo(UPDATED_PARTNER_LOG_RESULT_DESC);
        assertThat(testPartnerLog.getPartnerLogDate()).isEqualTo(UPDATED_PARTNER_LOG_DATE);
        assertThat(testPartnerLog.getPartnerLogUDF1()).isEqualTo(UPDATED_PARTNER_LOG_UDF_1);
        assertThat(testPartnerLog.getPartnerLogUDF2()).isEqualTo(UPDATED_PARTNER_LOG_UDF_2);
        assertThat(testPartnerLog.getPartnerLogUDF3()).isEqualTo(UPDATED_PARTNER_LOG_UDF_3);
        assertThat(testPartnerLog.getPartnerLogUDF4()).isEqualTo(UPDATED_PARTNER_LOG_UDF_4);
        assertThat(testPartnerLog.getPartnerLogUDF5()).isEqualTo(UPDATED_PARTNER_LOG_UDF_5);
        assertThat(testPartnerLog.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingPartnerLog() throws Exception {
        int databaseSizeBeforeUpdate = partnerLogRepository.findAll().size();

        // Create the PartnerLog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartnerLogMockMvc.perform(put("/api/partner-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerLog)))
            .andExpect(status().isBadRequest());

        // Validate the PartnerLog in the database
        List<PartnerLog> partnerLogList = partnerLogRepository.findAll();
        assertThat(partnerLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePartnerLog() throws Exception {
        // Initialize the database
        partnerLogRepository.saveAndFlush(partnerLog);

        int databaseSizeBeforeDelete = partnerLogRepository.findAll().size();

        // Get the partnerLog
        restPartnerLogMockMvc.perform(delete("/api/partner-logs/{id}", partnerLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PartnerLog> partnerLogList = partnerLogRepository.findAll();
        assertThat(partnerLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PartnerLog.class);
        PartnerLog partnerLog1 = new PartnerLog();
        partnerLog1.setId(1L);
        PartnerLog partnerLog2 = new PartnerLog();
        partnerLog2.setId(partnerLog1.getId());
        assertThat(partnerLog1).isEqualTo(partnerLog2);
        partnerLog2.setId(2L);
        assertThat(partnerLog1).isNotEqualTo(partnerLog2);
        partnerLog1.setId(null);
        assertThat(partnerLog1).isNotEqualTo(partnerLog2);
    }
}
