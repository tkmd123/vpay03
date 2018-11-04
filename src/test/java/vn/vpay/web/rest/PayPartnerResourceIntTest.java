package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.PayPartner;
import vn.vpay.repository.PayPartnerRepository;
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
 * Test class for the PayPartnerResource REST controller.
 *
 * @see PayPartnerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class PayPartnerResourceIntTest {

    private static final String DEFAULT_PAY_PARTNER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_SECRET_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_SECRET_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_PARTNER_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PAY_PARTNER_UDF_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private PayPartnerRepository payPartnerRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPayPartnerMockMvc;

    private PayPartner payPartner;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PayPartnerResource payPartnerResource = new PayPartnerResource(payPartnerRepository);
        this.restPayPartnerMockMvc = MockMvcBuilders.standaloneSetup(payPartnerResource)
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
    public static PayPartner createEntity(EntityManager em) {
        PayPartner payPartner = new PayPartner()
            .payPartnerCode(DEFAULT_PAY_PARTNER_CODE)
            .payPartnerName(DEFAULT_PAY_PARTNER_NAME)
            .payPartnerDesc(DEFAULT_PAY_PARTNER_DESC)
            .payPartnerUsername(DEFAULT_PAY_PARTNER_USERNAME)
            .payPartnerSecretKey(DEFAULT_PAY_PARTNER_SECRET_KEY)
            .payPartnerStatus(DEFAULT_PAY_PARTNER_STATUS)
            .payPartnerUDF1(DEFAULT_PAY_PARTNER_UDF_1)
            .payPartnerUDF2(DEFAULT_PAY_PARTNER_UDF_2)
            .payPartnerUDF3(DEFAULT_PAY_PARTNER_UDF_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return payPartner;
    }

    @Before
    public void initTest() {
        payPartner = createEntity(em);
    }

    @Test
    @Transactional
    public void createPayPartner() throws Exception {
        int databaseSizeBeforeCreate = payPartnerRepository.findAll().size();

        // Create the PayPartner
        restPayPartnerMockMvc.perform(post("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartner)))
            .andExpect(status().isCreated());

        // Validate the PayPartner in the database
        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeCreate + 1);
        PayPartner testPayPartner = payPartnerList.get(payPartnerList.size() - 1);
        assertThat(testPayPartner.getPayPartnerCode()).isEqualTo(DEFAULT_PAY_PARTNER_CODE);
        assertThat(testPayPartner.getPayPartnerName()).isEqualTo(DEFAULT_PAY_PARTNER_NAME);
        assertThat(testPayPartner.getPayPartnerDesc()).isEqualTo(DEFAULT_PAY_PARTNER_DESC);
        assertThat(testPayPartner.getPayPartnerUsername()).isEqualTo(DEFAULT_PAY_PARTNER_USERNAME);
        assertThat(testPayPartner.getPayPartnerSecretKey()).isEqualTo(DEFAULT_PAY_PARTNER_SECRET_KEY);
        assertThat(testPayPartner.getPayPartnerStatus()).isEqualTo(DEFAULT_PAY_PARTNER_STATUS);
        assertThat(testPayPartner.getPayPartnerUDF1()).isEqualTo(DEFAULT_PAY_PARTNER_UDF_1);
        assertThat(testPayPartner.getPayPartnerUDF2()).isEqualTo(DEFAULT_PAY_PARTNER_UDF_2);
        assertThat(testPayPartner.getPayPartnerUDF3()).isEqualTo(DEFAULT_PAY_PARTNER_UDF_3);
        assertThat(testPayPartner.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createPayPartnerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = payPartnerRepository.findAll().size();

        // Create the PayPartner with an existing ID
        payPartner.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayPartnerMockMvc.perform(post("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartner)))
            .andExpect(status().isBadRequest());

        // Validate the PayPartner in the database
        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPayPartnerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = payPartnerRepository.findAll().size();
        // set the field null
        payPartner.setPayPartnerCode(null);

        // Create the PayPartner, which fails.

        restPayPartnerMockMvc.perform(post("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartner)))
            .andExpect(status().isBadRequest());

        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPayPartnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = payPartnerRepository.findAll().size();
        // set the field null
        payPartner.setPayPartnerName(null);

        // Create the PayPartner, which fails.

        restPayPartnerMockMvc.perform(post("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartner)))
            .andExpect(status().isBadRequest());

        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = payPartnerRepository.findAll().size();
        // set the field null
        payPartner.setIsDeleted(null);

        // Create the PayPartner, which fails.

        restPayPartnerMockMvc.perform(post("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartner)))
            .andExpect(status().isBadRequest());

        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPayPartners() throws Exception {
        // Initialize the database
        payPartnerRepository.saveAndFlush(payPartner);

        // Get all the payPartnerList
        restPayPartnerMockMvc.perform(get("/api/pay-partners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payPartner.getId().intValue())))
            .andExpect(jsonPath("$.[*].payPartnerCode").value(hasItem(DEFAULT_PAY_PARTNER_CODE.toString())))
            .andExpect(jsonPath("$.[*].payPartnerName").value(hasItem(DEFAULT_PAY_PARTNER_NAME.toString())))
            .andExpect(jsonPath("$.[*].payPartnerDesc").value(hasItem(DEFAULT_PAY_PARTNER_DESC.toString())))
            .andExpect(jsonPath("$.[*].payPartnerUsername").value(hasItem(DEFAULT_PAY_PARTNER_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].payPartnerSecretKey").value(hasItem(DEFAULT_PAY_PARTNER_SECRET_KEY.toString())))
            .andExpect(jsonPath("$.[*].payPartnerStatus").value(hasItem(DEFAULT_PAY_PARTNER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].payPartnerUDF1").value(hasItem(DEFAULT_PAY_PARTNER_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].payPartnerUDF2").value(hasItem(DEFAULT_PAY_PARTNER_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].payPartnerUDF3").value(hasItem(DEFAULT_PAY_PARTNER_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPayPartner() throws Exception {
        // Initialize the database
        payPartnerRepository.saveAndFlush(payPartner);

        // Get the payPartner
        restPayPartnerMockMvc.perform(get("/api/pay-partners/{id}", payPartner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(payPartner.getId().intValue()))
            .andExpect(jsonPath("$.payPartnerCode").value(DEFAULT_PAY_PARTNER_CODE.toString()))
            .andExpect(jsonPath("$.payPartnerName").value(DEFAULT_PAY_PARTNER_NAME.toString()))
            .andExpect(jsonPath("$.payPartnerDesc").value(DEFAULT_PAY_PARTNER_DESC.toString()))
            .andExpect(jsonPath("$.payPartnerUsername").value(DEFAULT_PAY_PARTNER_USERNAME.toString()))
            .andExpect(jsonPath("$.payPartnerSecretKey").value(DEFAULT_PAY_PARTNER_SECRET_KEY.toString()))
            .andExpect(jsonPath("$.payPartnerStatus").value(DEFAULT_PAY_PARTNER_STATUS.toString()))
            .andExpect(jsonPath("$.payPartnerUDF1").value(DEFAULT_PAY_PARTNER_UDF_1.toString()))
            .andExpect(jsonPath("$.payPartnerUDF2").value(DEFAULT_PAY_PARTNER_UDF_2.toString()))
            .andExpect(jsonPath("$.payPartnerUDF3").value(DEFAULT_PAY_PARTNER_UDF_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPayPartner() throws Exception {
        // Get the payPartner
        restPayPartnerMockMvc.perform(get("/api/pay-partners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePayPartner() throws Exception {
        // Initialize the database
        payPartnerRepository.saveAndFlush(payPartner);

        int databaseSizeBeforeUpdate = payPartnerRepository.findAll().size();

        // Update the payPartner
        PayPartner updatedPayPartner = payPartnerRepository.findById(payPartner.getId()).get();
        // Disconnect from session so that the updates on updatedPayPartner are not directly saved in db
        em.detach(updatedPayPartner);
        updatedPayPartner
            .payPartnerCode(UPDATED_PAY_PARTNER_CODE)
            .payPartnerName(UPDATED_PAY_PARTNER_NAME)
            .payPartnerDesc(UPDATED_PAY_PARTNER_DESC)
            .payPartnerUsername(UPDATED_PAY_PARTNER_USERNAME)
            .payPartnerSecretKey(UPDATED_PAY_PARTNER_SECRET_KEY)
            .payPartnerStatus(UPDATED_PAY_PARTNER_STATUS)
            .payPartnerUDF1(UPDATED_PAY_PARTNER_UDF_1)
            .payPartnerUDF2(UPDATED_PAY_PARTNER_UDF_2)
            .payPartnerUDF3(UPDATED_PAY_PARTNER_UDF_3)
            .isDeleted(UPDATED_IS_DELETED);

        restPayPartnerMockMvc.perform(put("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPayPartner)))
            .andExpect(status().isOk());

        // Validate the PayPartner in the database
        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeUpdate);
        PayPartner testPayPartner = payPartnerList.get(payPartnerList.size() - 1);
        assertThat(testPayPartner.getPayPartnerCode()).isEqualTo(UPDATED_PAY_PARTNER_CODE);
        assertThat(testPayPartner.getPayPartnerName()).isEqualTo(UPDATED_PAY_PARTNER_NAME);
        assertThat(testPayPartner.getPayPartnerDesc()).isEqualTo(UPDATED_PAY_PARTNER_DESC);
        assertThat(testPayPartner.getPayPartnerUsername()).isEqualTo(UPDATED_PAY_PARTNER_USERNAME);
        assertThat(testPayPartner.getPayPartnerSecretKey()).isEqualTo(UPDATED_PAY_PARTNER_SECRET_KEY);
        assertThat(testPayPartner.getPayPartnerStatus()).isEqualTo(UPDATED_PAY_PARTNER_STATUS);
        assertThat(testPayPartner.getPayPartnerUDF1()).isEqualTo(UPDATED_PAY_PARTNER_UDF_1);
        assertThat(testPayPartner.getPayPartnerUDF2()).isEqualTo(UPDATED_PAY_PARTNER_UDF_2);
        assertThat(testPayPartner.getPayPartnerUDF3()).isEqualTo(UPDATED_PAY_PARTNER_UDF_3);
        assertThat(testPayPartner.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingPayPartner() throws Exception {
        int databaseSizeBeforeUpdate = payPartnerRepository.findAll().size();

        // Create the PayPartner

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayPartnerMockMvc.perform(put("/api/pay-partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(payPartner)))
            .andExpect(status().isBadRequest());

        // Validate the PayPartner in the database
        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePayPartner() throws Exception {
        // Initialize the database
        payPartnerRepository.saveAndFlush(payPartner);

        int databaseSizeBeforeDelete = payPartnerRepository.findAll().size();

        // Get the payPartner
        restPayPartnerMockMvc.perform(delete("/api/pay-partners/{id}", payPartner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PayPartner> payPartnerList = payPartnerRepository.findAll();
        assertThat(payPartnerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayPartner.class);
        PayPartner payPartner1 = new PayPartner();
        payPartner1.setId(1L);
        PayPartner payPartner2 = new PayPartner();
        payPartner2.setId(payPartner1.getId());
        assertThat(payPartner1).isEqualTo(payPartner2);
        payPartner2.setId(2L);
        assertThat(payPartner1).isNotEqualTo(payPartner2);
        payPartner1.setId(null);
        assertThat(payPartner1).isNotEqualTo(payPartner2);
    }
}
