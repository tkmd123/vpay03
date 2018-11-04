package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.WalletTransactionType;
import vn.vpay.repository.WalletTransactionTypeRepository;
import vn.vpay.web.rest.errors.BadRequestAlertException;
import vn.vpay.web.rest.util.HeaderUtil;
import vn.vpay.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WalletTransactionType.
 */
@RestController
@RequestMapping("/api")
public class WalletTransactionTypeResource {

    private final Logger log = LoggerFactory.getLogger(WalletTransactionTypeResource.class);

    private static final String ENTITY_NAME = "walletTransactionType";

    private final WalletTransactionTypeRepository walletTransactionTypeRepository;

    public WalletTransactionTypeResource(WalletTransactionTypeRepository walletTransactionTypeRepository) {
        this.walletTransactionTypeRepository = walletTransactionTypeRepository;
    }

    /**
     * POST  /wallet-transaction-types : Create a new walletTransactionType.
     *
     * @param walletTransactionType the walletTransactionType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new walletTransactionType, or with status 400 (Bad Request) if the walletTransactionType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wallet-transaction-types")
    @Timed
    public ResponseEntity<WalletTransactionType> createWalletTransactionType(@Valid @RequestBody WalletTransactionType walletTransactionType) throws URISyntaxException {
        log.debug("REST request to save WalletTransactionType : {}", walletTransactionType);
        if (walletTransactionType.getId() != null) {
            throw new BadRequestAlertException("A new walletTransactionType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletTransactionType result = walletTransactionTypeRepository.save(walletTransactionType);
        return ResponseEntity.created(new URI("/api/wallet-transaction-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wallet-transaction-types : Updates an existing walletTransactionType.
     *
     * @param walletTransactionType the walletTransactionType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated walletTransactionType,
     * or with status 400 (Bad Request) if the walletTransactionType is not valid,
     * or with status 500 (Internal Server Error) if the walletTransactionType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wallet-transaction-types")
    @Timed
    public ResponseEntity<WalletTransactionType> updateWalletTransactionType(@Valid @RequestBody WalletTransactionType walletTransactionType) throws URISyntaxException {
        log.debug("REST request to update WalletTransactionType : {}", walletTransactionType);
        if (walletTransactionType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletTransactionType result = walletTransactionTypeRepository.save(walletTransactionType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, walletTransactionType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wallet-transaction-types : get all the walletTransactionTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of walletTransactionTypes in body
     */
    @GetMapping("/wallet-transaction-types")
    @Timed
    public ResponseEntity<List<WalletTransactionType>> getAllWalletTransactionTypes(Pageable pageable) {
        log.debug("REST request to get a page of WalletTransactionTypes");
        Page<WalletTransactionType> page = walletTransactionTypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wallet-transaction-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /wallet-transaction-types/:id : get the "id" walletTransactionType.
     *
     * @param id the id of the walletTransactionType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the walletTransactionType, or with status 404 (Not Found)
     */
    @GetMapping("/wallet-transaction-types/{id}")
    @Timed
    public ResponseEntity<WalletTransactionType> getWalletTransactionType(@PathVariable Long id) {
        log.debug("REST request to get WalletTransactionType : {}", id);
        Optional<WalletTransactionType> walletTransactionType = walletTransactionTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(walletTransactionType);
    }

    /**
     * DELETE  /wallet-transaction-types/:id : delete the "id" walletTransactionType.
     *
     * @param id the id of the walletTransactionType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wallet-transaction-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteWalletTransactionType(@PathVariable Long id) {
        log.debug("REST request to delete WalletTransactionType : {}", id);

        walletTransactionTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
