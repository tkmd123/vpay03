package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.Partner;
import vn.vpay.repository.PartnerRepository;
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
 * Test class for the PartnerResource REST controller.
 *
 * @see PartnerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class PartnerResourceIntTest {

    private static final String DEFAULT_PARTNER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_DESC = "BBBBBBBBBB";

    private static final Integer DEFAULT_PARTNER_ORDER = 1;
    private static final Integer UPDATED_PARTNER_ORDER = 2;

    private static final String DEFAULT_PARTNER_API_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_API_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_API_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_API_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_URL = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_UDF_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPartnerMockMvc;

    private Partner partner;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PartnerResource partnerResource = new PartnerResource(partnerRepository);
        this.restPartnerMockMvc = MockMvcBuilders.standaloneSetup(partnerResource)
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
    public static Partner createEntity(EntityManager em) {
        Partner partner = new Partner()
            .partnerCode(DEFAULT_PARTNER_CODE)
            .partnerName(DEFAULT_PARTNER_NAME)
            .partnerDesc(DEFAULT_PARTNER_DESC)
            .partnerOrder(DEFAULT_PARTNER_ORDER)
            .partnerAPIUsername(DEFAULT_PARTNER_API_USERNAME)
            .partnerAPIPassword(DEFAULT_PARTNER_API_PASSWORD)
            .partnerUrl(DEFAULT_PARTNER_URL)
            .partnerStatus(DEFAULT_PARTNER_STATUS)
            .partnerUDF1(DEFAULT_PARTNER_UDF_1)
            .partnerUDF2(DEFAULT_PARTNER_UDF_2)
            .partnerUDF3(DEFAULT_PARTNER_UDF_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return partner;
    }

    @Before
    public void initTest() {
        partner = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartner() throws Exception {
        int databaseSizeBeforeCreate = partnerRepository.findAll().size();

        // Create the Partner
        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isCreated());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeCreate + 1);
        Partner testPartner = partnerList.get(partnerList.size() - 1);
        assertThat(testPartner.getPartnerCode()).isEqualTo(DEFAULT_PARTNER_CODE);
        assertThat(testPartner.getPartnerName()).isEqualTo(DEFAULT_PARTNER_NAME);
        assertThat(testPartner.getPartnerDesc()).isEqualTo(DEFAULT_PARTNER_DESC);
        assertThat(testPartner.getPartnerOrder()).isEqualTo(DEFAULT_PARTNER_ORDER);
        assertThat(testPartner.getPartnerAPIUsername()).isEqualTo(DEFAULT_PARTNER_API_USERNAME);
        assertThat(testPartner.getPartnerAPIPassword()).isEqualTo(DEFAULT_PARTNER_API_PASSWORD);
        assertThat(testPartner.getPartnerUrl()).isEqualTo(DEFAULT_PARTNER_URL);
        assertThat(testPartner.getPartnerStatus()).isEqualTo(DEFAULT_PARTNER_STATUS);
        assertThat(testPartner.getPartnerUDF1()).isEqualTo(DEFAULT_PARTNER_UDF_1);
        assertThat(testPartner.getPartnerUDF2()).isEqualTo(DEFAULT_PARTNER_UDF_2);
        assertThat(testPartner.getPartnerUDF3()).isEqualTo(DEFAULT_PARTNER_UDF_3);
        assertThat(testPartner.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createPartnerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partnerRepository.findAll().size();

        // Create the Partner with an existing ID
        partner.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPartnerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerRepository.findAll().size();
        // set the field null
        partner.setPartnerCode(null);

        // Create the Partner, which fails.

        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPartnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerRepository.findAll().size();
        // set the field null
        partner.setPartnerName(null);

        // Create the Partner, which fails.

        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPartnerOrderIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerRepository.findAll().size();
        // set the field null
        partner.setPartnerOrder(null);

        // Create the Partner, which fails.

        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = partnerRepository.findAll().size();
        // set the field null
        partner.setIsDeleted(null);

        // Create the Partner, which fails.

        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPartners() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        // Get all the partnerList
        restPartnerMockMvc.perform(get("/api/partners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partner.getId().intValue())))
            .andExpect(jsonPath("$.[*].partnerCode").value(hasItem(DEFAULT_PARTNER_CODE.toString())))
            .andExpect(jsonPath("$.[*].partnerName").value(hasItem(DEFAULT_PARTNER_NAME.toString())))
            .andExpect(jsonPath("$.[*].partnerDesc").value(hasItem(DEFAULT_PARTNER_DESC.toString())))
            .andExpect(jsonPath("$.[*].partnerOrder").value(hasItem(DEFAULT_PARTNER_ORDER)))
            .andExpect(jsonPath("$.[*].partnerAPIUsername").value(hasItem(DEFAULT_PARTNER_API_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].partnerAPIPassword").value(hasItem(DEFAULT_PARTNER_API_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].partnerUrl").value(hasItem(DEFAULT_PARTNER_URL.toString())))
            .andExpect(jsonPath("$.[*].partnerStatus").value(hasItem(DEFAULT_PARTNER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].partnerUDF1").value(hasItem(DEFAULT_PARTNER_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].partnerUDF2").value(hasItem(DEFAULT_PARTNER_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].partnerUDF3").value(hasItem(DEFAULT_PARTNER_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        // Get the partner
        restPartnerMockMvc.perform(get("/api/partners/{id}", partner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(partner.getId().intValue()))
            .andExpect(jsonPath("$.partnerCode").value(DEFAULT_PARTNER_CODE.toString()))
            .andExpect(jsonPath("$.partnerName").value(DEFAULT_PARTNER_NAME.toString()))
            .andExpect(jsonPath("$.partnerDesc").value(DEFAULT_PARTNER_DESC.toString()))
            .andExpect(jsonPath("$.partnerOrder").value(DEFAULT_PARTNER_ORDER))
            .andExpect(jsonPath("$.partnerAPIUsername").value(DEFAULT_PARTNER_API_USERNAME.toString()))
            .andExpect(jsonPath("$.partnerAPIPassword").value(DEFAULT_PARTNER_API_PASSWORD.toString()))
            .andExpect(jsonPath("$.partnerUrl").value(DEFAULT_PARTNER_URL.toString()))
            .andExpect(jsonPath("$.partnerStatus").value(DEFAULT_PARTNER_STATUS.toString()))
            .andExpect(jsonPath("$.partnerUDF1").value(DEFAULT_PARTNER_UDF_1.toString()))
            .andExpect(jsonPath("$.partnerUDF2").value(DEFAULT_PARTNER_UDF_2.toString()))
            .andExpect(jsonPath("$.partnerUDF3").value(DEFAULT_PARTNER_UDF_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPartner() throws Exception {
        // Get the partner
        restPartnerMockMvc.perform(get("/api/partners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();

        // Update the partner
        Partner updatedPartner = partnerRepository.findById(partner.getId()).get();
        // Disconnect from session so that the updates on updatedPartner are not directly saved in db
        em.detach(updatedPartner);
        updatedPartner
            .partnerCode(UPDATED_PARTNER_CODE)
            .partnerName(UPDATED_PARTNER_NAME)
            .partnerDesc(UPDATED_PARTNER_DESC)
            .partnerOrder(UPDATED_PARTNER_ORDER)
            .partnerAPIUsername(UPDATED_PARTNER_API_USERNAME)
            .partnerAPIPassword(UPDATED_PARTNER_API_PASSWORD)
            .partnerUrl(UPDATED_PARTNER_URL)
            .partnerStatus(UPDATED_PARTNER_STATUS)
            .partnerUDF1(UPDATED_PARTNER_UDF_1)
            .partnerUDF2(UPDATED_PARTNER_UDF_2)
            .partnerUDF3(UPDATED_PARTNER_UDF_3)
            .isDeleted(UPDATED_IS_DELETED);

        restPartnerMockMvc.perform(put("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPartner)))
            .andExpect(status().isOk());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
        Partner testPartner = partnerList.get(partnerList.size() - 1);
        assertThat(testPartner.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
        assertThat(testPartner.getPartnerName()).isEqualTo(UPDATED_PARTNER_NAME);
        assertThat(testPartner.getPartnerDesc()).isEqualTo(UPDATED_PARTNER_DESC);
        assertThat(testPartner.getPartnerOrder()).isEqualTo(UPDATED_PARTNER_ORDER);
        assertThat(testPartner.getPartnerAPIUsername()).isEqualTo(UPDATED_PARTNER_API_USERNAME);
        assertThat(testPartner.getPartnerAPIPassword()).isEqualTo(UPDATED_PARTNER_API_PASSWORD);
        assertThat(testPartner.getPartnerUrl()).isEqualTo(UPDATED_PARTNER_URL);
        assertThat(testPartner.getPartnerStatus()).isEqualTo(UPDATED_PARTNER_STATUS);
        assertThat(testPartner.getPartnerUDF1()).isEqualTo(UPDATED_PARTNER_UDF_1);
        assertThat(testPartner.getPartnerUDF2()).isEqualTo(UPDATED_PARTNER_UDF_2);
        assertThat(testPartner.getPartnerUDF3()).isEqualTo(UPDATED_PARTNER_UDF_3);
        assertThat(testPartner.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingPartner() throws Exception {
        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();

        // Create the Partner

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartnerMockMvc.perform(put("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        int databaseSizeBeforeDelete = partnerRepository.findAll().size();

        // Get the partner
        restPartnerMockMvc.perform(delete("/api/partners/{id}", partner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Partner.class);
        Partner partner1 = new Partner();
        partner1.setId(1L);
        Partner partner2 = new Partner();
        partner2.setId(partner1.getId());
        assertThat(partner1).isEqualTo(partner2);
        partner2.setId(2L);
        assertThat(partner1).isNotEqualTo(partner2);
        partner1.setId(null);
        assertThat(partner1).isNotEqualTo(partner2);
    }
}
