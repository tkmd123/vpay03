package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.ProductType;
import vn.vpay.repository.ProductTypeRepository;
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
 * Test class for the ProductTypeResource REST controller.
 *
 * @see ProductTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class ProductTypeResourceIntTest {

    private static final String DEFAULT_PRODUCT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UDF_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductTypeMockMvc;

    private ProductType productType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductTypeResource productTypeResource = new ProductTypeResource(productTypeRepository);
        this.restProductTypeMockMvc = MockMvcBuilders.standaloneSetup(productTypeResource)
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
    public static ProductType createEntity(EntityManager em) {
        ProductType productType = new ProductType()
            .productTypeCode(DEFAULT_PRODUCT_TYPE_CODE)
            .productTypeName(DEFAULT_PRODUCT_TYPE_NAME)
            .productTypeDesc(DEFAULT_PRODUCT_TYPE_DESC)
            .productTypeCategory(DEFAULT_PRODUCT_TYPE_CATEGORY)
            .productUDF1(DEFAULT_PRODUCT_UDF_1)
            .productUDF2(DEFAULT_PRODUCT_UDF_2)
            .productUDF3(DEFAULT_PRODUCT_UDF_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return productType;
    }

    @Before
    public void initTest() {
        productType = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductType() throws Exception {
        int databaseSizeBeforeCreate = productTypeRepository.findAll().size();

        // Create the ProductType
        restProductTypeMockMvc.perform(post("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isCreated());

        // Validate the ProductType in the database
        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ProductType testProductType = productTypeList.get(productTypeList.size() - 1);
        assertThat(testProductType.getProductTypeCode()).isEqualTo(DEFAULT_PRODUCT_TYPE_CODE);
        assertThat(testProductType.getProductTypeName()).isEqualTo(DEFAULT_PRODUCT_TYPE_NAME);
        assertThat(testProductType.getProductTypeDesc()).isEqualTo(DEFAULT_PRODUCT_TYPE_DESC);
        assertThat(testProductType.getProductTypeCategory()).isEqualTo(DEFAULT_PRODUCT_TYPE_CATEGORY);
        assertThat(testProductType.getProductUDF1()).isEqualTo(DEFAULT_PRODUCT_UDF_1);
        assertThat(testProductType.getProductUDF2()).isEqualTo(DEFAULT_PRODUCT_UDF_2);
        assertThat(testProductType.getProductUDF3()).isEqualTo(DEFAULT_PRODUCT_UDF_3);
        assertThat(testProductType.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createProductTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productTypeRepository.findAll().size();

        // Create the ProductType with an existing ID
        productType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductTypeMockMvc.perform(post("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductTypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productTypeRepository.findAll().size();
        // set the field null
        productType.setProductTypeCode(null);

        // Create the ProductType, which fails.

        restProductTypeMockMvc.perform(post("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isBadRequest());

        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productTypeRepository.findAll().size();
        // set the field null
        productType.setProductTypeName(null);

        // Create the ProductType, which fails.

        restProductTypeMockMvc.perform(post("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isBadRequest());

        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductTypeCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = productTypeRepository.findAll().size();
        // set the field null
        productType.setProductTypeCategory(null);

        // Create the ProductType, which fails.

        restProductTypeMockMvc.perform(post("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isBadRequest());

        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = productTypeRepository.findAll().size();
        // set the field null
        productType.setIsDeleted(null);

        // Create the ProductType, which fails.

        restProductTypeMockMvc.perform(post("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isBadRequest());

        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProductTypes() throws Exception {
        // Initialize the database
        productTypeRepository.saveAndFlush(productType);

        // Get all the productTypeList
        restProductTypeMockMvc.perform(get("/api/product-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productType.getId().intValue())))
            .andExpect(jsonPath("$.[*].productTypeCode").value(hasItem(DEFAULT_PRODUCT_TYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].productTypeName").value(hasItem(DEFAULT_PRODUCT_TYPE_NAME.toString())))
            .andExpect(jsonPath("$.[*].productTypeDesc").value(hasItem(DEFAULT_PRODUCT_TYPE_DESC.toString())))
            .andExpect(jsonPath("$.[*].productTypeCategory").value(hasItem(DEFAULT_PRODUCT_TYPE_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].productUDF1").value(hasItem(DEFAULT_PRODUCT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].productUDF2").value(hasItem(DEFAULT_PRODUCT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].productUDF3").value(hasItem(DEFAULT_PRODUCT_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProductType() throws Exception {
        // Initialize the database
        productTypeRepository.saveAndFlush(productType);

        // Get the productType
        restProductTypeMockMvc.perform(get("/api/product-types/{id}", productType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productType.getId().intValue()))
            .andExpect(jsonPath("$.productTypeCode").value(DEFAULT_PRODUCT_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.productTypeName").value(DEFAULT_PRODUCT_TYPE_NAME.toString()))
            .andExpect(jsonPath("$.productTypeDesc").value(DEFAULT_PRODUCT_TYPE_DESC.toString()))
            .andExpect(jsonPath("$.productTypeCategory").value(DEFAULT_PRODUCT_TYPE_CATEGORY.toString()))
            .andExpect(jsonPath("$.productUDF1").value(DEFAULT_PRODUCT_UDF_1.toString()))
            .andExpect(jsonPath("$.productUDF2").value(DEFAULT_PRODUCT_UDF_2.toString()))
            .andExpect(jsonPath("$.productUDF3").value(DEFAULT_PRODUCT_UDF_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProductType() throws Exception {
        // Get the productType
        restProductTypeMockMvc.perform(get("/api/product-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductType() throws Exception {
        // Initialize the database
        productTypeRepository.saveAndFlush(productType);

        int databaseSizeBeforeUpdate = productTypeRepository.findAll().size();

        // Update the productType
        ProductType updatedProductType = productTypeRepository.findById(productType.getId()).get();
        // Disconnect from session so that the updates on updatedProductType are not directly saved in db
        em.detach(updatedProductType);
        updatedProductType
            .productTypeCode(UPDATED_PRODUCT_TYPE_CODE)
            .productTypeName(UPDATED_PRODUCT_TYPE_NAME)
            .productTypeDesc(UPDATED_PRODUCT_TYPE_DESC)
            .productTypeCategory(UPDATED_PRODUCT_TYPE_CATEGORY)
            .productUDF1(UPDATED_PRODUCT_UDF_1)
            .productUDF2(UPDATED_PRODUCT_UDF_2)
            .productUDF3(UPDATED_PRODUCT_UDF_3)
            .isDeleted(UPDATED_IS_DELETED);

        restProductTypeMockMvc.perform(put("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductType)))
            .andExpect(status().isOk());

        // Validate the ProductType in the database
        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeUpdate);
        ProductType testProductType = productTypeList.get(productTypeList.size() - 1);
        assertThat(testProductType.getProductTypeCode()).isEqualTo(UPDATED_PRODUCT_TYPE_CODE);
        assertThat(testProductType.getProductTypeName()).isEqualTo(UPDATED_PRODUCT_TYPE_NAME);
        assertThat(testProductType.getProductTypeDesc()).isEqualTo(UPDATED_PRODUCT_TYPE_DESC);
        assertThat(testProductType.getProductTypeCategory()).isEqualTo(UPDATED_PRODUCT_TYPE_CATEGORY);
        assertThat(testProductType.getProductUDF1()).isEqualTo(UPDATED_PRODUCT_UDF_1);
        assertThat(testProductType.getProductUDF2()).isEqualTo(UPDATED_PRODUCT_UDF_2);
        assertThat(testProductType.getProductUDF3()).isEqualTo(UPDATED_PRODUCT_UDF_3);
        assertThat(testProductType.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingProductType() throws Exception {
        int databaseSizeBeforeUpdate = productTypeRepository.findAll().size();

        // Create the ProductType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTypeMockMvc.perform(put("/api/product-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productType)))
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductType() throws Exception {
        // Initialize the database
        productTypeRepository.saveAndFlush(productType);

        int databaseSizeBeforeDelete = productTypeRepository.findAll().size();

        // Get the productType
        restProductTypeMockMvc.perform(delete("/api/product-types/{id}", productType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductType> productTypeList = productTypeRepository.findAll();
        assertThat(productTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductType.class);
        ProductType productType1 = new ProductType();
        productType1.setId(1L);
        ProductType productType2 = new ProductType();
        productType2.setId(productType1.getId());
        assertThat(productType1).isEqualTo(productType2);
        productType2.setId(2L);
        assertThat(productType1).isNotEqualTo(productType2);
        productType1.setId(null);
        assertThat(productType1).isNotEqualTo(productType2);
    }
}
