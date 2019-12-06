package id.tech.cakra.ecfregistersvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CampaignRegisterMapperTest {

    private CampaignRegisterMapper campaignRegisterMapper;

    @BeforeEach
    public void setUp() {
        campaignRegisterMapper = new CampaignRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(campaignRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campaignRegisterMapper.fromId(null)).isNull();
    }
}
