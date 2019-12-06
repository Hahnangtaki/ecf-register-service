package id.tech.cakra.ecfregistersvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfregistersvc.web.rest.TestUtil;

public class CampaignRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignRegisterDTO.class);
        CampaignRegisterDTO campaignRegisterDTO1 = new CampaignRegisterDTO();
        campaignRegisterDTO1.setId(1L);
        CampaignRegisterDTO campaignRegisterDTO2 = new CampaignRegisterDTO();
        assertThat(campaignRegisterDTO1).isNotEqualTo(campaignRegisterDTO2);
        campaignRegisterDTO2.setId(campaignRegisterDTO1.getId());
        assertThat(campaignRegisterDTO1).isEqualTo(campaignRegisterDTO2);
        campaignRegisterDTO2.setId(2L);
        assertThat(campaignRegisterDTO1).isNotEqualTo(campaignRegisterDTO2);
        campaignRegisterDTO1.setId(null);
        assertThat(campaignRegisterDTO1).isNotEqualTo(campaignRegisterDTO2);
    }
}
