package id.tech.cakra.ecfregistersvc.service.mapper;

import id.tech.cakra.ecfregistersvc.domain.*;
import id.tech.cakra.ecfregistersvc.service.dto.AccountInstitutionRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountInstitutionRegister} and its DTO {@link AccountInstitutionRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountRegisterMapper.class})
public interface AccountInstitutionRegisterMapper extends EntityMapper<AccountInstitutionRegisterDTO, AccountInstitutionRegister> {

    @Mapping(source = "accountRegister.id", target = "accountRegisterId")
    AccountInstitutionRegisterDTO toDto(AccountInstitutionRegister accountInstitutionRegister);

    @Mapping(source = "accountRegisterId", target = "accountRegister")
    AccountInstitutionRegister toEntity(AccountInstitutionRegisterDTO accountInstitutionRegisterDTO);

    default AccountInstitutionRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountInstitutionRegister accountInstitutionRegister = new AccountInstitutionRegister();
        accountInstitutionRegister.setId(id);
        return accountInstitutionRegister;
    }
}
