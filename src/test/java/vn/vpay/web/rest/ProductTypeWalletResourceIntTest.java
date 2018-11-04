package vn.vpay.web.rest;

import vn.vpay.Vpay03App;

import vn.vpay.domain.ProductTypeWallet;
import vn.vpay.repository.ProductTypeWalletRepository;
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
 * Test class for the ProductTypeWalletResource REST controller.
 *
 * @see ProductTypeWalletResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vpay03App.class)
public class ProductTypeWalletResourceIntTest {

    private static final String DEFAULT_PRODUCT_TYPE_WALLET_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_WALLET_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE_WALLET_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_WALLET_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TYPE_WALLET_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE_WALLET_UDF_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ProductTypeWalletRepository productTypeWalletRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductTypeWalletMockMvc;

    private ProductTypeWallet productTypeWallet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductTypeWalletResource productTypeWalletResource = new ProductTypeWalletResource(productTypeWalletRepository);
        this.restProductTypeWalletMockMvc = MockMvcBuilders.standaloneSetup(productTypeWalletResource)
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
    public static ProductTypeWallet createEntity(EntityManager em) {
        ProductTypeWallet productTypeWallet = new ProductTypeWallet()
            .productTypeWalletUDF1(DEFAULT_PRODUCT_TYPE_WALLET_UDF_1)
            .productTypeWalletUDF2(DEFAULT_PRODUCT_TYPE_WALLET_UDF_2)
            .productTypeWalletUDF3(DEFAULT_PRODUCT_TYPE_WALLET_UDF_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return productTypeWallet;
    }

    @Before
    public void initTest() {
        productTypeWallet = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductTypeWallet() throws Exception {
        int databaseSizeBeforeCreate = productTypeWalletRepository.findAll().size();

        // Create the ProductTypeWallet
        restProductTypeWalletMockMvc.perform(post("/api/product-type-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTypeWallet)))
            .andExpect(status().isCreated());

        // Validate the ProductTypeWallet in the database
        List<ProductTypeWallet> productTypeWalletList = productTypeWalletRepository.findAll();
        assertThat(productTypeWalletList).hasSize(databaseSizeBeforeCreate + 1);
        ProductTypeWallet testProductTypeWallet = productTypeWalletList.get(productTypeWalletList.size() - 1);
        assertThat(testProductTypeWallet.getProductTypeWalletUDF1()).isEqualTo(DEFAULT_PRODUCT_TYPE_WALLET_UDF_1);
        assertThat(testProductTypeWallet.getProductTypeWalletUDF2()).isEqualTo(DEFAULT_PRODUCT_TYPE_WALLET_UDF_2);
        assertThat(testProductTypeWallet.getProductTypeWalletUDF3()).isEqualTo(DEFAULT_PRODUCT_TYPE_WALLET_UDF_3);
        assertThat(testProductTypeWallet.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createProductTypeWalletWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productTypeWalletRepository.findAll().size();

        // Create the ProductTypeWallet with an existing ID
        productTypeWallet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductTypeWalletMockMvc.perform(post("/api/product-type-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTypeWallet)))
            .andExpect(status().isBadRequest());

        // Validate the ProductTypeWallet in the database
        List<ProductTypeWallet> productTypeWalletList = productTypeWalletRepository.findAll();
        assertThat(productTypeWalletList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIsDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = productTypeWalletRepository.findAll().size();
        // set the field null
        productTypeWallet.setIsDeleted(null);

        // Create the ProductTypeWallet, which fails.

        restProductTypeWalletMockMvc.perform(post("/api/product-type-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTypeWallet)))
            .andExpect(status().isBadRequest());

        List<ProductTypeWallet> productTypeWalletList = productTypeWalletRepository.findAll();
        assertThat(productTypeWalletList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProductTypeWallets() throws Exception {
        // Initialize the database
        productTypeWalletRepository.saveAndFlush(productTypeWallet);

        // Get all the productTypeWalletList
        restProductTypeWalletMockMvc.perform(get("/api/product-type-wallets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productTypeWallet.getId().intValue())))
            .andExpect(jsonPath("$.[*].productTypeWalletUDF1").value(hasItem(DEFAULT_PRODUCT_TYPE_WALLET_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].productTypeWalletUDF2").value(hasItem(DEFAULT_PRODUCT_TYPE_WALLET_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].productTypeWalletUDF3").value(hasItem(DEFAULT_PRODUCT_TYPE_WALLET_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProductTypeWallet() throws Exception {
        // Initialize the database
        productTypeWalletRepository.saveAndFlush(productTypeWallet);

        // Get the productTypeWallet
        restProductTypeWalletMockMvc.perform(get("/api/product-type-wallets/{id}", productTypeWallet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productTypeWallet.getId().intValue()))
            .andExpect(jsonPath("$.productTypeWalletUDF1").value(DEFAULT_PRODUCT_TYPE_WALLET_UDF_1.toString()))
            .andExpect(jsonPath("$.productTypeWalletUDF2").value(DEFAULT_PRODUCT_TYPE_WALLET_UDF_2.toString()))
            .andExpect(jsonPath("$.productTypeWalletUDF3").value(DEFAULT_PRODUCT_TYPE_WALLET_UDF_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProductTypeWallet() throws Exception {
        // Get the productTypeWallet
        restProductTypeWalletMockMvc.perform(get("/api/product-type-wallets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductTypeWallet() throws Exception {
        // Initialize the database
        productTypeWalletRepository.saveAndFlush(productTypeWallet);

        int databaseSizeBeforeUpdate = productTypeWalletRepository.findAll().size();

        // Update the productTypeWallet
        ProductTypeWallet updatedProductTypeWallet = productTypeWalletRepository.findById(productTypeWallet.getId()).get();
        // Disconnect from session so that the updates on updatedProductTypeWallet are not directly saved in db
        em.detach(updatedProductTypeWallet);
        updatedProductTypeWallet
            .productTypeWalletUDF1(UPDATED_PRODUCT_TYPE_WALLET_UDF_1)
            .productTypeWalletUDF2(UPDATED_PRODUCT_TYPE_WALLET_UDF_2)
            .productTypeWalletUDF3(UPDATED_PRODUCT_TYPE_WALLET_UDF_3)
            .isDeleted(UPDATED_IS_DELETED);

        restProductTypeWalletMockMvc.perform(put("/api/product-type-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductTypeWallet)))
            .andExpect(status().isOk());

        // Validate the ProductTypeWallet in the database
        List<ProductTypeWallet> productTypeWalletList = productTypeWalletRepository.findAll();
        assertThat(productTypeWalletList).hasSize(databaseSizeBeforeUpdate);
        ProductTypeWallet testProductTypeWallet = productTypeWalletList.get(productTypeWalletList.size() - 1);
        assertThat(testProductTypeWallet.getProductTypeWalletUDF1()).isEqualTo(UPDATED_PRODUCT_TYPE_WALLET_UDF_1);
        assertThat(testProductTypeWallet.getProductTypeWalletUDF2()).isEqualTo(UPDATED_PRODUCT_TYPE_WALLET_UDF_2);
        assertThat(testProductTypeWallet.getProductTypeWalletUDF3()).isEqualTo(UPDATED_PRODUCT_TYPE_WALLET_UDF_3);
        assertThat(testProductTypeWallet.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingProductTypeWallet() throws Exception {
        int databaseSizeBeforeUpdate = productTypeWalletRepository.findAll().size();

        // Create the ProductTypeWallet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTypeWalletMockMvc.perform(put("/api/product-type-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTypeWallet)))
            .andExpect(status().isBadRequest());

        // Validate the ProductTypeWallet in the database
        List<ProductTypeWallet> productTypeWalletList = productTypeWalletRepository.findAll();
        assertThat(productTypeWalletList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductTypeWallet() throws Exception {
        // Initialize the database
        productTypeWalletRepository.saveAndFlush(productTypeWallet);

        int databaseSizeBeforeDelete = productTypeWalletRepository.findAll().size();

        // Get the productTypeWallet
        restProductTypeWalletMockMvc.perform(delete("/api/product-type-wallets/{id}", productTypeWallet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductTypeWallet> productTypeWalletList = productTypeWalletRepository.findAll();
        assertThat(productTypeWalletList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductTypeWallet.class);
        ProductTypeWallet productTypeWallet1 = new ProductTypeWallet();
        productTypeWallet1.setId(1L);
        ProductTypeWallet productTypeWallet2 = new ProductTypeWallet();
        productTypeWallet2.setId(productTypeWallet1.getId());
        assertThat(productTypeWallet1).isEqualTo(productTypeWallet2);
        productTypeWallet2.setId(2L);
        assertThat(productTypeWallet1).isNotEqualTo(productTypeWallet2);
        productTypeWallet1.setId(null);
        assertThat(productTypeWallet1).isNotEqualTo(productTypeWallet2);
    }
}
