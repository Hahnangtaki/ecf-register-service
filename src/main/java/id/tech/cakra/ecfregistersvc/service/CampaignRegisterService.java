package id.tech.cakra.ecfregistersvc.service;

import id.tech.cakra.ecfregistersvc.service.dto.CampaignRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfregistersvc.domain.CampaignRegister}.
 */
public interface CampaignRegisterService {

    /**
     * Save a campaignRegister.
     *
     * @param campaignRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    CampaignRegisterDTO save(CampaignRegisterDTO campaignRegisterDTO);

    /**
     * Get all the campaignRegisters.
     *
     * @return the list of entities.
     */
    List<CampaignRegisterDTO> findAll();


    /**
     * Get the "id" campaignRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" campaignRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
