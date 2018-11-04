package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.PartnerLog;
import vn.vpay.repository.PartnerLogRepository;
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
 * REST controller for managing PartnerLog.
 */
@RestController
@RequestMapping("/api")
public class PartnerLogResource {

    private final Logger log = LoggerFactory.getLogger(PartnerLogResource.class);

    private static final String ENTITY_NAME = "partnerLog";

    private final PartnerLogRepository partnerLogRepository;

    public PartnerLogResource(PartnerLogRepository partnerLogRepository) {
        this.partnerLogRepository = partnerLogRepository;
    }

    /**
     * POST  /partner-logs : Create a new partnerLog.
     *
     * @param partnerLog the partnerLog to create
     * @return the ResponseEntity with status 201 (Created) and with body the new partnerLog, or with status 400 (Bad Request) if the partnerLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/partner-logs")
    @Timed
    public ResponseEntity<PartnerLog> createPartnerLog(@Valid @RequestBody PartnerLog partnerLog) throws URISyntaxException {
        log.debug("REST request to save PartnerLog : {}", partnerLog);
        if (partnerLog.getId() != null) {
            throw new BadRequestAlertException("A new partnerLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartnerLog result = partnerLogRepository.save(partnerLog);
        return ResponseEntity.created(new URI("/api/partner-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /partner-logs : Updates an existing partnerLog.
     *
     * @param partnerLog the partnerLog to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated partnerLog,
     * or with status 400 (Bad Request) if the partnerLog is not valid,
     * or with status 500 (Internal Server Error) if the partnerLog couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/partner-logs")
    @Timed
    public ResponseEntity<PartnerLog> updatePartnerLog(@Valid @RequestBody PartnerLog partnerLog) throws URISyntaxException {
        log.debug("REST request to update PartnerLog : {}", partnerLog);
        if (partnerLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PartnerLog result = partnerLogRepository.save(partnerLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, partnerLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /partner-logs : get all the partnerLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of partnerLogs in body
     */
    @GetMapping("/partner-logs")
    @Timed
    public ResponseEntity<List<PartnerLog>> getAllPartnerLogs(Pageable pageable) {
        log.debug("REST request to get a page of PartnerLogs");
        Page<PartnerLog> page = partnerLogRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partner-logs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /partner-logs/:id : get the "id" partnerLog.
     *
     * @param id the id of the partnerLog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the partnerLog, or with status 404 (Not Found)
     */
    @GetMapping("/partner-logs/{id}")
    @Timed
    public ResponseEntity<PartnerLog> getPartnerLog(@PathVariable Long id) {
        log.debug("REST request to get PartnerLog : {}", id);
        Optional<PartnerLog> partnerLog = partnerLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(partnerLog);
    }

    /**
     * DELETE  /partner-logs/:id : delete the "id" partnerLog.
     *
     * @param id the id of the partnerLog to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/partner-logs/{id}")
    @Timed
    public ResponseEntity<Void> deletePartnerLog(@PathVariable Long id) {
        log.debug("REST request to delete PartnerLog : {}", id);

        partnerLogRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
