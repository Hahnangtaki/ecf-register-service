package id.tech.cakra.ecfregistersvc.service.mapper;

import id.tech.cakra.ecfregistersvc.domain.*;
import id.tech.cakra.ecfregistersvc.service.dto.CampaignRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignRegister} and its DTO {@link CampaignRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CampaignRegisterMapper extends EntityMapper<CampaignRegisterDTO, CampaignRegister> {



    default CampaignRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignRegister campaignRegister = new CampaignRegister();
        campaignRegister.setId(id);
        return campaignRegister;
    }
}
