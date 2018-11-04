package vn.vpay.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.vpay.domain.PayPartner;
import vn.vpay.repository.PayPartnerRepository;
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
 * REST controller for managing PayPartner.
 */
@RestController
@RequestMapping("/api")
public class PayPartnerResource {

    private final Logger log = LoggerFactory.getLogger(PayPartnerResource.class);

    private static final String ENTITY_NAME = "payPartner";

    private final PayPartnerRepository payPartnerRepository;

    public PayPartnerResource(PayPartnerRepository payPartnerRepository) {
        this.payPartnerRepository = payPartnerRepository;
    }

    /**
     * POST  /pay-partners : Create a new payPartner.
     *
     * @param payPartner the payPartner to create
     * @return the ResponseEntity with status 201 (Created) and with body the new payPartner, or with status 400 (Bad Request) if the payPartner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pay-partners")
    @Timed
    public ResponseEntity<PayPartner> createPayPartner(@Valid @RequestBody PayPartner payPartner) throws URISyntaxException {
        log.debug("REST request to save PayPartner : {}", payPartner);
        if (payPartner.getId() != null) {
            throw new BadRequestAlertException("A new payPartner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayPartner result = payPartnerRepository.save(payPartner);
        return ResponseEntity.created(new URI("/api/pay-partners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pay-partners : Updates an existing payPartner.
     *
     * @param payPartner the payPartner to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated payPartner,
     * or with status 400 (Bad Request) if the payPartner is not valid,
     * or with status 500 (Internal Server Error) if the payPartner couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pay-partners")
    @Timed
    public ResponseEntity<PayPartner> updatePayPartner(@Valid @RequestBody PayPartner payPartner) throws URISyntaxException {
        log.debug("REST request to update PayPartner : {}", payPartner);
        if (payPartner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PayPartner result = payPartnerRepository.save(payPartner);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, payPartner.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pay-partners : get all the payPartners.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of payPartners in body
     */
    @GetMapping("/pay-partners")
    @Timed
    public ResponseEntity<List<PayPartner>> getAllPayPartners(Pageable pageable) {
        log.debug("REST request to get a page of PayPartners");
        Page<PayPartner> page = payPartnerRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pay-partners");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pay-partners/:id : get the "id" payPartner.
     *
     * @param id the id of the payPartner to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the payPartner, or with status 404 (Not Found)
     */
    @GetMapping("/pay-partners/{id}")
    @Timed
    public ResponseEntity<PayPartner> getPayPartner(@PathVariable Long id) {
        log.debug("REST request to get PayPartner : {}", id);
        Optional<PayPartner> payPartner = payPartnerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(payPartner);
    }

    /**
     * DELETE  /pay-partners/:id : delete the "id" payPartner.
     *
     * @param id the id of the payPartner to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pay-partners/{id}")
    @Timed
    public ResponseEntity<Void> deletePayPartner(@PathVariable Long id) {
        log.debug("REST request to delete PayPartner : {}", id);

        payPartnerRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
