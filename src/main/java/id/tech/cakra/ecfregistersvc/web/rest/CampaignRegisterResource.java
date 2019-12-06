package id.tech.cakra.ecfregistersvc.web.rest;

import id.tech.cakra.ecfregistersvc.service.CampaignRegisterService;
import id.tech.cakra.ecfregistersvc.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.ecfregistersvc.service.dto.CampaignRegisterDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.ecfregistersvc.domain.CampaignRegister}.
 */
@RestController
@RequestMapping("/api")
public class CampaignRegisterResource {

    private final Logger log = LoggerFactory.getLogger(CampaignRegisterResource.class);

    private static final String ENTITY_NAME = "ecfregistersvcCampaignRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignRegisterService campaignRegisterService;

    public CampaignRegisterResource(CampaignRegisterService campaignRegisterService) {
        this.campaignRegisterService = campaignRegisterService;
    }

    /**
     * {@code POST  /campaign-registers} : Create a new campaignRegister.
     *
     * @param campaignRegisterDTO the campaignRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignRegisterDTO, or with status {@code 400 (Bad Request)} if the campaignRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaign-registers")
    public ResponseEntity<CampaignRegisterDTO> createCampaignRegister(@Valid @RequestBody CampaignRegisterDTO campaignRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save CampaignRegister : {}", campaignRegisterDTO);
        if (campaignRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new campaignRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignRegisterDTO result = campaignRegisterService.save(campaignRegisterDTO);
        return ResponseEntity.created(new URI("/api/campaign-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaign-registers} : Updates an existing campaignRegister.
     *
     * @param campaignRegisterDTO the campaignRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the campaignRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaign-registers")
    public ResponseEntity<CampaignRegisterDTO> updateCampaignRegister(@Valid @RequestBody CampaignRegisterDTO campaignRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update CampaignRegister : {}", campaignRegisterDTO);
        if (campaignRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignRegisterDTO result = campaignRegisterService.save(campaignRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, campaignRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaign-registers} : get all the campaignRegisters.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignRegisters in body.
     */
    @GetMapping("/campaign-registers")
    public List<CampaignRegisterDTO> getAllCampaignRegisters() {
        log.debug("REST request to get all CampaignRegisters");
        return campaignRegisterService.findAll();
    }

    /**
     * {@code GET  /campaign-registers/:id} : get the "id" campaignRegister.
     *
     * @param id the id of the campaignRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaign-registers/{id}")
    public ResponseEntity<CampaignRegisterDTO> getCampaignRegister(@PathVariable Long id) {
        log.debug("REST request to get CampaignRegister : {}", id);
        Optional<CampaignRegisterDTO> campaignRegisterDTO = campaignRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignRegisterDTO);
    }

    /**
     * {@code DELETE  /campaign-registers/:id} : delete the "id" campaignRegister.
     *
     * @param id the id of the campaignRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaign-registers/{id}")
    public ResponseEntity<Void> deleteCampaignRegister(@PathVariable Long id) {
        log.debug("REST request to delete CampaignRegister : {}", id);
        campaignRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
