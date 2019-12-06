package id.tech.cakra.ecfregistersvc.service;

import id.tech.cakra.ecfregistersvc.service.dto.AccountBankRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfregistersvc.domain.AccountBankRegister}.
 */
public interface AccountBankRegisterService {

    /**
     * Save a accountBankRegister.
     *
     * @param accountBankRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    AccountBankRegisterDTO save(AccountBankRegisterDTO accountBankRegisterDTO);

    /**
     * Get all the accountBankRegisters.
     *
     * @return the list of entities.
     */
    List<AccountBankRegisterDTO> findAll();


    /**
     * Get the "id" accountBankRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountBankRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" accountBankRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
