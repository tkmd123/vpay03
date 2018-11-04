package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.ProductTypeWallet;
import vn.vpay.repository.ProductTypeWalletRepository;
import vn.vpay.web.rest.errors.BadRequestAlertException;
import vn.vpay.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductTypeWallet.
 */
@RestController
@RequestMapping("/api")
public class ProductTypeWalletResource {

    private final Logger log = LoggerFactory.getLogger(ProductTypeWalletResource.class);

    private static final String ENTITY_NAME = "productTypeWallet";

    private final ProductTypeWalletRepository productTypeWalletRepository;

    public ProductTypeWalletResource(ProductTypeWalletRepository productTypeWalletRepository) {
        this.productTypeWalletRepository = productTypeWalletRepository;
    }

    /**
     * POST  /product-type-wallets : Create a new productTypeWallet.
     *
     * @param productTypeWallet the productTypeWallet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productTypeWallet, or with status 400 (Bad Request) if the productTypeWallet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-type-wallets")
    @Timed
    public ResponseEntity<ProductTypeWallet> createProductTypeWallet(@Valid @RequestBody ProductTypeWallet productTypeWallet) throws URISyntaxException {
        log.debug("REST request to save ProductTypeWallet : {}", productTypeWallet);
        if (productTypeWallet.getId() != null) {
            throw new BadRequestAlertException("A new productTypeWallet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductTypeWallet result = productTypeWalletRepository.save(productTypeWallet);
        return ResponseEntity.created(new URI("/api/product-type-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-type-wallets : Updates an existing productTypeWallet.
     *
     * @param productTypeWallet the productTypeWallet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productTypeWallet,
     * or with status 400 (Bad Request) if the productTypeWallet is not valid,
     * or with status 500 (Internal Server Error) if the productTypeWallet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-type-wallets")
    @Timed
    public ResponseEntity<ProductTypeWallet> updateProductTypeWallet(@Valid @RequestBody ProductTypeWallet productTypeWallet) throws URISyntaxException {
        log.debug("REST request to update ProductTypeWallet : {}", productTypeWallet);
        if (productTypeWallet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductTypeWallet result = productTypeWalletRepository.save(productTypeWallet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productTypeWallet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-type-wallets : get all the productTypeWallets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productTypeWallets in body
     */
    @GetMapping("/product-type-wallets")
    @Timed
    public List<ProductTypeWallet> getAllProductTypeWallets() {
        log.debug("REST request to get all ProductTypeWallets");
        return productTypeWalletRepository.findAll();
    }

    /**
     * GET  /product-type-wallets/:id : get the "id" productTypeWallet.
     *
     * @param id the id of the productTypeWallet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productTypeWallet, or with status 404 (Not Found)
     */
    @GetMapping("/product-type-wallets/{id}")
    @Timed
    public ResponseEntity<ProductTypeWallet> getProductTypeWallet(@PathVariable Long id) {
        log.debug("REST request to get ProductTypeWallet : {}", id);
        Optional<ProductTypeWallet> productTypeWallet = productTypeWalletRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productTypeWallet);
    }

    /**
     * DELETE  /product-type-wallets/:id : delete the "id" productTypeWallet.
     *
     * @param id the id of the productTypeWallet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-type-wallets/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductTypeWallet(@PathVariable Long id) {
        log.debug("REST request to delete ProductTypeWallet : {}", id);

        productTypeWalletRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
