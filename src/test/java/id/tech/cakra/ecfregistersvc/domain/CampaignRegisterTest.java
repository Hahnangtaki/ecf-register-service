package id.tech.cakra.ecfregistersvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfregistersvc.web.rest.TestUtil;

public class CampaignRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignRegister.class);
        CampaignRegister campaignRegister1 = new CampaignRegister();
        campaignRegister1.setId(1L);
        CampaignRegister campaignRegister2 = new CampaignRegister();
        campaignRegister2.setId(campaignRegister1.getId());
        assertThat(campaignRegister1).isEqualTo(campaignRegister2);
        campaignRegister2.setId(2L);
        assertThat(campaignRegister1).isNotEqualTo(campaignRegister2);
        campaignRegister1.setId(null);
        assertThat(campaignRegister1).isNotEqualTo(campaignRegister2);
    }
}
