package id.tech.cakra.ecfregistersvc.service;

import id.tech.cakra.ecfregistersvc.service.dto.AccountRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfregistersvc.domain.AccountRegister}.
 */
public interface AccountRegisterService {

    /**
     * Save a accountRegister.
     *
     * @param accountRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    AccountRegisterDTO save(AccountRegisterDTO accountRegisterDTO);

    /**
     * Get all the accountRegisters.
     *
     * @return the list of entities.
     */
    List<AccountRegisterDTO> findAll();
    /**
     * Get all the AccountRegisterDTO where AccountIndividuRegister is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountRegisterDTO> findAllWhereAccountIndividuRegisterIsNull();
    /**
     * Get all the AccountRegisterDTO where AccountInstitutionRegister is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountRegisterDTO> findAllWhereAccountInstitutionRegisterIsNull();
    /**
     * Get all the AccountRegisterDTO where AccountBankRegister is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountRegisterDTO> findAllWhereAccountBankRegisterIsNull();


    /**
     * Get the "id" accountRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" accountRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
