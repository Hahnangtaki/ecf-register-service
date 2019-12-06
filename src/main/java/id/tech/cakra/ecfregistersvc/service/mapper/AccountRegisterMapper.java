package id.tech.cakra.ecfregistersvc.service.mapper;

import id.tech.cakra.ecfregistersvc.domain.*;
import id.tech.cakra.ecfregistersvc.service.dto.AccountRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountRegister} and its DTO {@link AccountRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountRegisterMapper extends EntityMapper<AccountRegisterDTO, AccountRegister> {


    @Mapping(target = "accountAuthorizeRegisters", ignore = true)
    @Mapping(target = "removeAccountAuthorizeRegister", ignore = true)
    @Mapping(target = "investorAddressRegisters", ignore = true)
    @Mapping(target = "removeInvestorAddressRegister", ignore = true)
    @Mapping(target = "accountIndividuRegister", ignore = true)
    @Mapping(target = "accountInstitutionRegister", ignore = true)
    @Mapping(target = "accountBankRegister", ignore = true)
    AccountRegister toEntity(AccountRegisterDTO accountRegisterDTO);

    default AccountRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountRegister accountRegister = new AccountRegister();
        accountRegister.setId(id);
        return accountRegister;
    }
}
