package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.PartnerTransaction;
import vn.vpay.repository.PartnerTransactionRepository;
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
 * REST controller for managing PartnerTransaction.
 */
@RestController
@RequestMapping("/api")
public class PartnerTransactionResource {

    private final Logger log = LoggerFactory.getLogger(PartnerTransactionResource.class);

    private static final String ENTITY_NAME = "partnerTransaction";

    private final PartnerTransactionRepository partnerTransactionRepository;

    public PartnerTransactionResource(PartnerTransactionRepository partnerTransactionRepository) {
        this.partnerTransactionRepository = partnerTransactionRepository;
    }

    /**
     * POST  /partner-transactions : Create a new partnerTransaction.
     *
     * @param partnerTransaction the partnerTransaction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new partnerTransaction, or with status 400 (Bad Request) if the partnerTransaction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/partner-transactions")
    @Timed
    public ResponseEntity<PartnerTransaction> createPartnerTransaction(@Valid @RequestBody PartnerTransaction partnerTransaction) throws URISyntaxException {
        log.debug("REST request to save PartnerTransaction : {}", partnerTransaction);
        if (partnerTransaction.getId() != null) {
            throw new BadRequestAlertException("A new partnerTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartnerTransaction result = partnerTransactionRepository.save(partnerTransaction);
        return ResponseEntity.created(new URI("/api/partner-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /partner-transactions : Updates an existing partnerTransaction.
     *
     * @param partnerTransaction the partnerTransaction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated partnerTransaction,
     * or with status 400 (Bad Request) if the partnerTransaction is not valid,
     * or with status 500 (Internal Server Error) if the partnerTransaction couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/partner-transactions")
    @Timed
    public ResponseEntity<PartnerTransaction> updatePartnerTransaction(@Valid @RequestBody PartnerTransaction partnerTransaction) throws URISyntaxException {
        log.debug("REST request to update PartnerTransaction : {}", partnerTransaction);
        if (partnerTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PartnerTransaction result = partnerTransactionRepository.save(partnerTransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, partnerTransaction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /partner-transactions : get all the partnerTransactions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of partnerTransactions in body
     */
    @GetMapping("/partner-transactions")
    @Timed
    public ResponseEntity<List<PartnerTransaction>> getAllPartnerTransactions(Pageable pageable) {
        log.debug("REST request to get a page of PartnerTransactions");
        Page<PartnerTransaction> page = partnerTransactionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partner-transactions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /partner-transactions/:id : get the "id" partnerTransaction.
     *
     * @param id the id of the partnerTransaction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the partnerTransaction, or with status 404 (Not Found)
     */
    @GetMapping("/partner-transactions/{id}")
    @Timed
    public ResponseEntity<PartnerTransaction> getPartnerTransaction(@PathVariable Long id) {
        log.debug("REST request to get PartnerTransaction : {}", id);
        Optional<PartnerTransaction> partnerTransaction = partnerTransactionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(partnerTransaction);
    }

    /**
     * DELETE  /partner-transactions/:id : delete the "id" partnerTransaction.
     *
     * @param id the id of the partnerTransaction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/partner-transactions/{id}")
    @Timed
    public ResponseEntity<Void> deletePartnerTransaction(@PathVariable Long id) {
        log.debug("REST request to delete PartnerTransaction : {}", id);

        partnerTransactionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
