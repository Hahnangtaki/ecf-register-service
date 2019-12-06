package id.tech.cakra.ecfregistersvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountBankRegisterMapperTest {

    private AccountBankRegisterMapper accountBankRegisterMapper;

    @BeforeEach
    public void setUp() {
        accountBankRegisterMapper = new AccountBankRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountBankRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountBankRegisterMapper.fromId(null)).isNull();
    }
}
