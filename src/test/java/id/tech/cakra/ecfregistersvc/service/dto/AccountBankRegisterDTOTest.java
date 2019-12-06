package id.tech.cakra.ecfregistersvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfregistersvc.web.rest.TestUtil;

public class AccountBankRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountBankRegisterDTO.class);
        AccountBankRegisterDTO accountBankRegisterDTO1 = new AccountBankRegisterDTO();
        accountBankRegisterDTO1.setId(1L);
        AccountBankRegisterDTO accountBankRegisterDTO2 = new AccountBankRegisterDTO();
        assertThat(accountBankRegisterDTO1).isNotEqualTo(accountBankRegisterDTO2);
        accountBankRegisterDTO2.setId(accountBankRegisterDTO1.getId());
        assertThat(accountBankRegisterDTO1).isEqualTo(accountBankRegisterDTO2);
        accountBankRegisterDTO2.setId(2L);
        assertThat(accountBankRegisterDTO1).isNotEqualTo(accountBankRegisterDTO2);
        accountBankRegisterDTO1.setId(null);
        assertThat(accountBankRegisterDTO1).isNotEqualTo(accountBankRegisterDTO2);
    }
}
