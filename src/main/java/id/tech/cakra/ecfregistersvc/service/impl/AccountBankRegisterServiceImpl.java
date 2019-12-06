package id.tech.cakra.ecfregistersvc.service.impl;

import id.tech.cakra.ecfregistersvc.service.AccountBankRegisterService;
import id.tech.cakra.ecfregistersvc.domain.AccountBankRegister;
import id.tech.cakra.ecfregistersvc.repository.AccountBankRegisterRepository;
import id.tech.cakra.ecfregistersvc.service.dto.AccountBankRegisterDTO;
import id.tech.cakra.ecfregistersvc.service.mapper.AccountBankRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountBankRegister}.
 */
@Service
@Transactional
public class AccountBankRegisterServiceImpl implements AccountBankRegisterService {

    private final Logger log = LoggerFactory.getLogger(AccountBankRegisterServiceImpl.class);

    private final AccountBankRegisterRepository accountBankRegisterRepository;

    private final AccountBankRegisterMapper accountBankRegisterMapper;

    public AccountBankRegisterServiceImpl(AccountBankRegisterRepository accountBankRegisterRepository, AccountBankRegisterMapper accountBankRegisterMapper) {
        this.accountBankRegisterRepository = accountBankRegisterRepository;
        this.accountBankRegisterMapper = accountBankRegisterMapper;
    }

    /**
     * Save a accountBankRegister.
     *
     * @param accountBankRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountBankRegisterDTO save(AccountBankRegisterDTO accountBankRegisterDTO) {
        log.debug("Request to save AccountBankRegister : {}", accountBankRegisterDTO);
        AccountBankRegister accountBankRegister = accountBankRegisterMapper.toEntity(accountBankRegisterDTO);
        accountBankRegister = accountBankRegisterRepository.save(accountBankRegister);
        return accountBankRegisterMapper.toDto(accountBankRegister);
    }

    /**
     * Get all the accountBankRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountBankRegisterDTO> findAll() {
        log.debug("Request to get all AccountBankRegisters");
        return accountBankRegisterRepository.findAll().stream()
            .map(accountBankRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountBankRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountBankRegisterDTO> findOne(Long id) {
        log.debug("Request to get AccountBankRegister : {}", id);
        return accountBankRegisterRepository.findById(id)
            .map(accountBankRegisterMapper::toDto);
    }

    /**
     * Delete the accountBankRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountBankRegister : {}", id);
        accountBankRegisterRepository.deleteById(id);
    }
}
