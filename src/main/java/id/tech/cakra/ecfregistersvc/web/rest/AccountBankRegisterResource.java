package id.tech.cakra.ecfregistersvc.web.rest;

import id.tech.cakra.ecfregistersvc.service.AccountBankRegisterService;
import id.tech.cakra.ecfregistersvc.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.ecfregistersvc.service.dto.AccountBankRegisterDTO;

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
 * REST controller for managing {@link id.tech.cakra.ecfregistersvc.domain.AccountBankRegister}.
 */
@RestController
@RequestMapping("/api")
public class AccountBankRegisterResource {

    private final Logger log = LoggerFactory.getLogger(AccountBankRegisterResource.class);

    private static final String ENTITY_NAME = "ecfregistersvcAccountBankRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountBankRegisterService accountBankRegisterService;

    public AccountBankRegisterResource(AccountBankRegisterService accountBankRegisterService) {
        this.accountBankRegisterService = accountBankRegisterService;
    }

    /**
     * {@code POST  /account-bank-registers} : Create a new accountBankRegister.
     *
     * @param accountBankRegisterDTO the accountBankRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountBankRegisterDTO, or with status {@code 400 (Bad Request)} if the accountBankRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-bank-registers")
    public ResponseEntity<AccountBankRegisterDTO> createAccountBankRegister(@Valid @RequestBody AccountBankRegisterDTO accountBankRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save AccountBankRegister : {}", accountBankRegisterDTO);
        if (accountBankRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountBankRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountBankRegisterDTO result = accountBankRegisterService.save(accountBankRegisterDTO);
        return ResponseEntity.created(new URI("/api/account-bank-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-bank-registers} : Updates an existing accountBankRegister.
     *
     * @param accountBankRegisterDTO the accountBankRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountBankRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the accountBankRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountBankRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-bank-registers")
    public ResponseEntity<AccountBankRegisterDTO> updateAccountBankRegister(@Valid @RequestBody AccountBankRegisterDTO accountBankRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update AccountBankRegister : {}", accountBankRegisterDTO);
        if (accountBankRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountBankRegisterDTO result = accountBankRegisterService.save(accountBankRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountBankRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-bank-registers} : get all the accountBankRegisters.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountBankRegisters in body.
     */
    @GetMapping("/account-bank-registers")
    public List<AccountBankRegisterDTO> getAllAccountBankRegisters() {
        log.debug("REST request to get all AccountBankRegisters");
        return accountBankRegisterService.findAll();
    }

    /**
     * {@code GET  /account-bank-registers/:id} : get the "id" accountBankRegister.
     *
     * @param id the id of the accountBankRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountBankRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-bank-registers/{id}")
    public ResponseEntity<AccountBankRegisterDTO> getAccountBankRegister(@PathVariable Long id) {
        log.debug("REST request to get AccountBankRegister : {}", id);
        Optional<AccountBankRegisterDTO> accountBankRegisterDTO = accountBankRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountBankRegisterDTO);
    }

    /**
     * {@code DELETE  /account-bank-registers/:id} : delete the "id" accountBankRegister.
     *
     * @param id the id of the accountBankRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-bank-registers/{id}")
    public ResponseEntity<Void> deleteAccountBankRegister(@PathVariable Long id) {
        log.debug("REST request to delete AccountBankRegister : {}", id);
        accountBankRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
