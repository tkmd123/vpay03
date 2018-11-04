package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.Status;
import vn.vpay.repository.StatusRepository;
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
 * Test class for the StatusResource REST controller.
 *
 * @see StatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class StatusResourceIntTest {

    private static final String DEFAULT_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS_VALUE = 1;
    private static final Integer UPDATED_STATUS_VALUE = 2;

    private static final String DEFAULT_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DESC = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_UDF_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStatusMockMvc;

    private Status status;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatusResource statusResource = new StatusResource(statusRepository);
        this.restStatusMockMvc = MockMvcBuilders.standaloneSetup(statusResource)
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
    public static Status createEntity(EntityManager em) {
        Status status = new Status()
            .statusCode(DEFAULT_STATUS_CODE)
            .statusValue(DEFAULT_STATUS_VALUE)
            .statusName(DEFAULT_STATUS_NAME)
            .statusDesc(DEFAULT_STATUS_DESC)
            .statusUDF1(DEFAULT_STATUS_UDF_1)
            .statusUDF2(DEFAULT_STATUS_UDF_2)
            .statusUDF3(DEFAULT_STATUS_UDF_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return status;
    }

    @Before
    public void initTest() {
        status = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatus() throws Exception {
        int databaseSizeBeforeCreate = statusRepository.findAll().size();

        // Create the Status
        restStatusMockMvc.perform(post("/api/statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(status)))
            .andExpect(status().isCreated());

        // Validate the Status in the database
        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeCreate + 1);
        Status testStatus = statusList.get(statusList.size() - 1);
        assertThat(testStatus.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testStatus.getStatusValue()).isEqualTo(DEFAULT_STATUS_VALUE);
        assertThat(testStatus.getStatusName()).isEqualTo(DEFAULT_STATUS_NAME);
        assertThat(testStatus.getStatusDesc()).isEqualTo(DEFAULT_STATUS_DESC);
        assertThat(testStatus.getStatusUDF1()).isEqualTo(DEFAULT_STATUS_UDF_1);
        assertThat(testStatus.getStatusUDF2()).isEqualTo(DEFAULT_STATUS_UDF_2);
        assertThat(testStatus.getStatusUDF3()).isEqualTo(DEFAULT_STATUS_UDF_3);
        assertThat(testStatus.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusRepository.findAll().size();

        // Create the Status with an existing ID
        status.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusMockMvc.perform(post("/api/statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(status)))
            .andExpect(status().isBadRequest());

        // Validate the Status in the database
        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = statusRepository.findAll().size();
        // set the field null
        status.setStatusCode(null);

        // Create the Status, which fails.

        restStatusMockMvc.perform(post("/api/statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(status)))
            .andExpect(status().isBadRequest());

        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = statusRepository.findAll().size();
        // set the field null
        status.setIsDeleted(null);

        // Create the Status, which fails.

        restStatusMockMvc.perform(post("/api/statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(status)))
            .andExpect(status().isBadRequest());

        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStatuses() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        // Get all the statusList
        restStatusMockMvc.perform(get("/api/statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(status.getId().intValue())))
            .andExpect(jsonPath("$.[*].statusCode").value(hasItem(DEFAULT_STATUS_CODE.toString())))
            .andExpect(jsonPath("$.[*].statusValue").value(hasItem(DEFAULT_STATUS_VALUE)))
            .andExpect(jsonPath("$.[*].statusName").value(hasItem(DEFAULT_STATUS_NAME.toString())))
            .andExpect(jsonPath("$.[*].statusDesc").value(hasItem(DEFAULT_STATUS_DESC.toString())))
            .andExpect(jsonPath("$.[*].statusUDF1").value(hasItem(DEFAULT_STATUS_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].statusUDF2").value(hasItem(DEFAULT_STATUS_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].statusUDF3").value(hasItem(DEFAULT_STATUS_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getStatus() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        // Get the status
        restStatusMockMvc.perform(get("/api/statuses/{id}", status.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(status.getId().intValue()))
            .andExpect(jsonPath("$.statusCode").value(DEFAULT_STATUS_CODE.toString()))
            .andExpect(jsonPath("$.statusValue").value(DEFAULT_STATUS_VALUE))
            .andExpect(jsonPath("$.statusName").value(DEFAULT_STATUS_NAME.toString()))
            .andExpect(jsonPath("$.statusDesc").value(DEFAULT_STATUS_DESC.toString()))
            .andExpect(jsonPath("$.statusUDF1").value(DEFAULT_STATUS_UDF_1.toString()))
            .andExpect(jsonPath("$.statusUDF2").value(DEFAULT_STATUS_UDF_2.toString()))
            .andExpect(jsonPath("$.statusUDF3").value(DEFAULT_STATUS_UDF_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStatus() throws Exception {
        // Get the status
        restStatusMockMvc.perform(get("/api/statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatus() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        int databaseSizeBeforeUpdate = statusRepository.findAll().size();

        // Update the status
        Status updatedStatus = statusRepository.findById(status.getId()).get();
        // Disconnect from session so that the updates on updatedStatus are not directly saved in db
        em.detach(updatedStatus);
        updatedStatus
            .statusCode(UPDATED_STATUS_CODE)
            .statusValue(UPDATED_STATUS_VALUE)
            .statusName(UPDATED_STATUS_NAME)
            .statusDesc(UPDATED_STATUS_DESC)
            .statusUDF1(UPDATED_STATUS_UDF_1)
            .statusUDF2(UPDATED_STATUS_UDF_2)
            .statusUDF3(UPDATED_STATUS_UDF_3)
            .isDeleted(UPDATED_IS_DELETED);

        restStatusMockMvc.perform(put("/api/statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatus)))
            .andExpect(status().isOk());

        // Validate the Status in the database
        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeUpdate);
        Status testStatus = statusList.get(statusList.size() - 1);
        assertThat(testStatus.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testStatus.getStatusValue()).isEqualTo(UPDATED_STATUS_VALUE);
        assertThat(testStatus.getStatusName()).isEqualTo(UPDATED_STATUS_NAME);
        assertThat(testStatus.getStatusDesc()).isEqualTo(UPDATED_STATUS_DESC);
        assertThat(testStatus.getStatusUDF1()).isEqualTo(UPDATED_STATUS_UDF_1);
        assertThat(testStatus.getStatusUDF2()).isEqualTo(UPDATED_STATUS_UDF_2);
        assertThat(testStatus.getStatusUDF3()).isEqualTo(UPDATED_STATUS_UDF_3);
        assertThat(testStatus.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingStatus() throws Exception {
        int databaseSizeBeforeUpdate = statusRepository.findAll().size();

        // Create the Status

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatusMockMvc.perform(put("/api/statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(status)))
            .andExpect(status().isBadRequest());

        // Validate the Status in the database
        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatus() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        int databaseSizeBeforeDelete = statusRepository.findAll().size();

        // Get the status
        restStatusMockMvc.perform(delete("/api/statuses/{id}", status.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Status> statusList = statusRepository.findAll();
        assertThat(statusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Status.class);
        Status status1 = new Status();
        status1.setId(1L);
        Status status2 = new Status();
        status2.setId(status1.getId());
        assertThat(status1).isEqualTo(status2);
        status2.setId(2L);
        assertThat(status1).isNotEqualTo(status2);
        status1.setId(null);
        assertThat(status1).isNotEqualTo(status2);
    }
}
