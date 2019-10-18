package id.tech.cakra.ecfregistersvc.service;

import id.tech.cakra.ecfregistersvc.service.dto.VisitorRegisterAccountDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfregistersvc.domain.VisitorRegisterAccount}.
 */
public interface VisitorRegisterAccountService {

    /**
     * Save a visitorRegisterAccount.
     *
     * @param visitorRegisterAccountDTO the entity to save.
     * @return the persisted entity.
     */
    VisitorRegisterAccountDTO save(VisitorRegisterAccountDTO visitorRegisterAccountDTO);

    /**
     * Get all the visitorRegisterAccounts.
     *
     * @return the list of entities.
     */
    List<VisitorRegisterAccountDTO> findAll();
    /**
     * Get all the VisitorRegisterAccountDTO where Visitor is {@code null}.
     *
     * @return the list of entities.
     */
    List<VisitorRegisterAccountDTO> findAllWhereVisitorIsNull();


    /**
     * Get the "id" visitorRegisterAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VisitorRegisterAccountDTO> findOne(Long id);

    /**
     * Delete the "id" visitorRegisterAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
