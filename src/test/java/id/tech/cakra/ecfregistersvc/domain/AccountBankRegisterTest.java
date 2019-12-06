package id.tech.cakra.ecfregistersvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfregistersvc.web.rest.TestUtil;

public class AccountBankRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountBankRegister.class);
        AccountBankRegister accountBankRegister1 = new AccountBankRegister();
        accountBankRegister1.setId(1L);
        AccountBankRegister accountBankRegister2 = new AccountBankRegister();
        accountBankRegister2.setId(accountBankRegister1.getId());
        assertThat(accountBankRegister1).isEqualTo(accountBankRegister2);
        accountBankRegister2.setId(2L);
        assertThat(accountBankRegister1).isNotEqualTo(accountBankRegister2);
        accountBankRegister1.setId(null);
        assertThat(accountBankRegister1).isNotEqualTo(accountBankRegister2);
    }
}
