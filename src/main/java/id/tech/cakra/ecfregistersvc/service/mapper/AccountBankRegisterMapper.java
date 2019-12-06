package id.tech.cakra.ecfregistersvc.service.mapper;

import id.tech.cakra.ecfregistersvc.domain.*;
import id.tech.cakra.ecfregistersvc.service.dto.AccountBankRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountBankRegister} and its DTO {@link AccountBankRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountRegisterMapper.class})
public interface AccountBankRegisterMapper extends EntityMapper<AccountBankRegisterDTO, AccountBankRegister> {

    @Mapping(source = "accountRegister.id", target = "accountRegisterId")
    AccountBankRegisterDTO toDto(AccountBankRegister accountBankRegister);

    @Mapping(source = "accountRegisterId", target = "accountRegister")
    AccountBankRegister toEntity(AccountBankRegisterDTO accountBankRegisterDTO);

    default AccountBankRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountBankRegister accountBankRegister = new AccountBankRegister();
        accountBankRegister.setId(id);
        return accountBankRegister;
    }
}
