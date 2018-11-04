package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.WalletRule;
import vn.vpay.repository.WalletRuleRepository;
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
 * REST controller for managing WalletRule.
 */
@RestController
@RequestMapping("/api")
public class WalletRuleResource {

    private final Logger log = LoggerFactory.getLogger(WalletRuleResource.class);

    private static final String ENTITY_NAME = "walletRule";

    private final WalletRuleRepository walletRuleRepository;

    public WalletRuleResource(WalletRuleRepository walletRuleRepository) {
        this.walletRuleRepository = walletRuleRepository;
    }

    /**
     * POST  /wallet-rules : Create a new walletRule.
     *
     * @param walletRule the walletRule to create
     * @return the ResponseEntity with status 201 (Created) and with body the new walletRule, or with status 400 (Bad Request) if the walletRule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wallet-rules")
    @Timed
    public ResponseEntity<WalletRule> createWalletRule(@Valid @RequestBody WalletRule walletRule) throws URISyntaxException {
        log.debug("REST request to save WalletRule : {}", walletRule);
        if (walletRule.getId() != null) {
            throw new BadRequestAlertException("A new walletRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletRule result = walletRuleRepository.save(walletRule);
        return ResponseEntity.created(new URI("/api/wallet-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wallet-rules : Updates an existing walletRule.
     *
     * @param walletRule the walletRule to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated walletRule,
     * or with status 400 (Bad Request) if the walletRule is not valid,
     * or with status 500 (Internal Server Error) if the walletRule couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wallet-rules")
    @Timed
    public ResponseEntity<WalletRule> updateWalletRule(@Valid @RequestBody WalletRule walletRule) throws URISyntaxException {
        log.debug("REST request to update WalletRule : {}", walletRule);
        if (walletRule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletRule result = walletRuleRepository.save(walletRule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, walletRule.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wallet-rules : get all the walletRules.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of walletRules in body
     */
    @GetMapping("/wallet-rules")
    @Timed
    public ResponseEntity<List<WalletRule>> getAllWalletRules(Pageable pageable) {
        log.debug("REST request to get a page of WalletRules");
        Page<WalletRule> page = walletRuleRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wallet-rules");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /wallet-rules/:id : get the "id" walletRule.
     *
     * @param id the id of the walletRule to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the walletRule, or with status 404 (Not Found)
     */
    @GetMapping("/wallet-rules/{id}")
    @Timed
    public ResponseEntity<WalletRule> getWalletRule(@PathVariable Long id) {
        log.debug("REST request to get WalletRule : {}", id);
        Optional<WalletRule> walletRule = walletRuleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(walletRule);
    }

    /**
     * DELETE  /wallet-rules/:id : delete the "id" walletRule.
     *
     * @param id the id of the walletRule to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wallet-rules/{id}")
    @Timed
    public ResponseEntity<Void> deleteWalletRule(@PathVariable Long id) {
        log.debug("REST request to delete WalletRule : {}", id);

        walletRuleRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
