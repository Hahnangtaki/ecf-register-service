package id.tech.cakra.ecfregistersvc.service.mapper;

import id.tech.cakra.ecfregistersvc.domain.*;
import id.tech.cakra.ecfregistersvc.service.dto.OtpHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OtpHistory} and its DTO {@link OtpHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {VisitorMapper.class})
public interface OtpHistoryMapper extends EntityMapper<OtpHistoryDTO, OtpHistory> {

    @Mapping(source = "visitor.id", target = "visitorId")
    OtpHistoryDTO toDto(OtpHistory otpHistory);

    @Mapping(source = "visitorId", target = "visitor")
    OtpHistory toEntity(OtpHistoryDTO otpHistoryDTO);

    default OtpHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        OtpHistory otpHistory = new OtpHistory();
        otpHistory.setId(id);
        return otpHistory;
    }
}
