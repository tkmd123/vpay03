package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.PayPartnerLog;
import vn.vpay.repository.PayPartnerLogRepository;
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
 * REST controller for managing PayPartnerLog.
 */
@RestController
@RequestMapping("/api")
public class PayPartnerLogResource {

    private final Logger log = LoggerFactory.getLogger(PayPartnerLogResource.class);

    private static final String ENTITY_NAME = "payPartnerLog";

    private final PayPartnerLogRepository payPartnerLogRepository;

    public PayPartnerLogResource(PayPartnerLogRepository payPartnerLogRepository) {
        this.payPartnerLogRepository = payPartnerLogRepository;
    }

    /**
     * POST  /pay-partner-logs : Create a new payPartnerLog.
     *
     * @param payPartnerLog the payPartnerLog to create
     * @return the ResponseEntity with status 201 (Created) and with body the new payPartnerLog, or with status 400 (Bad Request) if the payPartnerLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pay-partner-logs")
    @Timed
    public ResponseEntity<PayPartnerLog> createPayPartnerLog(@Valid @RequestBody PayPartnerLog payPartnerLog) throws URISyntaxException {
        log.debug("REST request to save PayPartnerLog : {}", payPartnerLog);
        if (payPartnerLog.getId() != null) {
            throw new BadRequestAlertException("A new payPartnerLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayPartnerLog result = payPartnerLogRepository.save(payPartnerLog);
        return ResponseEntity.created(new URI("/api/pay-partner-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pay-partner-logs : Updates an existing payPartnerLog.
     *
     * @param payPartnerLog the payPartnerLog to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated payPartnerLog,
     * or with status 400 (Bad Request) if the payPartnerLog is not valid,
     * or with status 500 (Internal Server Error) if the payPartnerLog couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pay-partner-logs")
    @Timed
    public ResponseEntity<PayPartnerLog> updatePayPartnerLog(@Valid @RequestBody PayPartnerLog payPartnerLog) throws URISyntaxException {
        log.debug("REST request to update PayPartnerLog : {}", payPartnerLog);
        if (payPartnerLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PayPartnerLog result = payPartnerLogRepository.save(payPartnerLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, payPartnerLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pay-partner-logs : get all the payPartnerLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of payPartnerLogs in body
     */
    @GetMapping("/pay-partner-logs")
    @Timed
    public ResponseEntity<List<PayPartnerLog>> getAllPayPartnerLogs(Pageable pageable) {
        log.debug("REST request to get a page of PayPartnerLogs");
        Page<PayPartnerLog> page = payPartnerLogRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pay-partner-logs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pay-partner-logs/:id : get the "id" payPartnerLog.
     *
     * @param id the id of the payPartnerLog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the payPartnerLog, or with status 404 (Not Found)
     */
    @GetMapping("/pay-partner-logs/{id}")
    @Timed
    public ResponseEntity<PayPartnerLog> getPayPartnerLog(@PathVariable Long id) {
        log.debug("REST request to get PayPartnerLog : {}", id);
        Optional<PayPartnerLog> payPartnerLog = payPartnerLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(payPartnerLog);
    }

    /**
     * DELETE  /pay-partner-logs/:id : delete the "id" payPartnerLog.
     *
     * @param id the id of the payPartnerLog to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pay-partner-logs/{id}")
    @Timed
    public ResponseEntity<Void> deletePayPartnerLog(@PathVariable Long id) {
        log.debug("REST request to delete PayPartnerLog : {}", id);

        payPartnerLogRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
