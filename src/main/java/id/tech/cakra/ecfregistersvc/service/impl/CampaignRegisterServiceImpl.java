package id.tech.cakra.ecfregistersvc.service.impl;

import id.tech.cakra.ecfregistersvc.service.CampaignRegisterService;
import id.tech.cakra.ecfregistersvc.domain.CampaignRegister;
import id.tech.cakra.ecfregistersvc.repository.CampaignRegisterRepository;
import id.tech.cakra.ecfregistersvc.service.dto.CampaignRegisterDTO;
import id.tech.cakra.ecfregistersvc.service.mapper.CampaignRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CampaignRegister}.
 */
@Service
@Transactional
public class CampaignRegisterServiceImpl implements CampaignRegisterService {

    private final Logger log = LoggerFactory.getLogger(CampaignRegisterServiceImpl.class);

    private final CampaignRegisterRepository campaignRegisterRepository;

    private final CampaignRegisterMapper campaignRegisterMapper;

    public CampaignRegisterServiceImpl(CampaignRegisterRepository campaignRegisterRepository, CampaignRegisterMapper campaignRegisterMapper) {
        this.campaignRegisterRepository = campaignRegisterRepository;
        this.campaignRegisterMapper = campaignRegisterMapper;
    }

    /**
     * Save a campaignRegister.
     *
     * @param campaignRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampaignRegisterDTO save(CampaignRegisterDTO campaignRegisterDTO) {
        log.debug("Request to save CampaignRegister : {}", campaignRegisterDTO);
        CampaignRegister campaignRegister = campaignRegisterMapper.toEntity(campaignRegisterDTO);
        campaignRegister = campaignRegisterRepository.save(campaignRegister);
        return campaignRegisterMapper.toDto(campaignRegister);
    }

    /**
     * Get all the campaignRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampaignRegisterDTO> findAll() {
        log.debug("Request to get all CampaignRegisters");
        return campaignRegisterRepository.findAll().stream()
            .map(campaignRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campaignRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignRegisterDTO> findOne(Long id) {
        log.debug("Request to get CampaignRegister : {}", id);
        return campaignRegisterRepository.findById(id)
            .map(campaignRegisterMapper::toDto);
    }

    /**
     * Delete the campaignRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampaignRegister : {}", id);
        campaignRegisterRepository.deleteById(id);
    }
}
