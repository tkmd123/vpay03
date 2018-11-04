package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.Product;
import vn.vpay.repository.ProductRepository;
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
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class ProductResourceIntTest {

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCT_VALUE = 1;
    private static final Integer UPDATED_PRODUCT_VALUE = 2;

    private static final String DEFAULT_PRODUCT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UDF_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductMockMvc;

    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productRepository);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
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
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .productCode(DEFAULT_PRODUCT_CODE)
            .productName(DEFAULT_PRODUCT_NAME)
            .productValue(DEFAULT_PRODUCT_VALUE)
            .productDesc(DEFAULT_PRODUCT_DESC)
            .productUDF1(DEFAULT_PRODUCT_UDF_1)
            .productUDF2(DEFAULT_PRODUCT_UDF_2)
            .productUDF3(DEFAULT_PRODUCT_UDF_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return product;
    }

    @Before
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProduct.getProductValue()).isEqualTo(DEFAULT_PRODUCT_VALUE);
        assertThat(testProduct.getProductDesc()).isEqualTo(DEFAULT_PRODUCT_DESC);
        assertThat(testProduct.getProductUDF1()).isEqualTo(DEFAULT_PRODUCT_UDF_1);
        assertThat(testProduct.getProductUDF2()).isEqualTo(DEFAULT_PRODUCT_UDF_2);
        assertThat(testProduct.getProductUDF3()).isEqualTo(DEFAULT_PRODUCT_UDF_3);
        assertThat(testProduct.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProductCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductCode(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductName(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIsDeleted(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].productCode").value(hasItem(DEFAULT_PRODUCT_CODE.toString())))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].productValue").value(hasItem(DEFAULT_PRODUCT_VALUE)))
            .andExpect(jsonPath("$.[*].productDesc").value(hasItem(DEFAULT_PRODUCT_DESC.toString())))
            .andExpect(jsonPath("$.[*].productUDF1").value(hasItem(DEFAULT_PRODUCT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].productUDF2").value(hasItem(DEFAULT_PRODUCT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].productUDF3").value(hasItem(DEFAULT_PRODUCT_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.productCode").value(DEFAULT_PRODUCT_CODE.toString()))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.productValue").value(DEFAULT_PRODUCT_VALUE))
            .andExpect(jsonPath("$.productDesc").value(DEFAULT_PRODUCT_DESC.toString()))
            .andExpect(jsonPath("$.productUDF1").value(DEFAULT_PRODUCT_UDF_1.toString()))
            .andExpect(jsonPath("$.productUDF2").value(DEFAULT_PRODUCT_UDF_2.toString()))
            .andExpect(jsonPath("$.productUDF3").value(DEFAULT_PRODUCT_UDF_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .productCode(UPDATED_PRODUCT_CODE)
            .productName(UPDATED_PRODUCT_NAME)
            .productValue(UPDATED_PRODUCT_VALUE)
            .productDesc(UPDATED_PRODUCT_DESC)
            .productUDF1(UPDATED_PRODUCT_UDF_1)
            .productUDF2(UPDATED_PRODUCT_UDF_2)
            .productUDF3(UPDATED_PRODUCT_UDF_3)
            .isDeleted(UPDATED_IS_DELETED);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getProductValue()).isEqualTo(UPDATED_PRODUCT_VALUE);
        assertThat(testProduct.getProductDesc()).isEqualTo(UPDATED_PRODUCT_DESC);
        assertThat(testProduct.getProductUDF1()).isEqualTo(UPDATED_PRODUCT_UDF_1);
        assertThat(testProduct.getProductUDF2()).isEqualTo(UPDATED_PRODUCT_UDF_2);
        assertThat(testProduct.getProductUDF3()).isEqualTo(UPDATED_PRODUCT_UDF_3);
        assertThat(testProduct.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId(2L);
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
