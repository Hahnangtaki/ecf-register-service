package id.tech.cakra.ecfregistersvc.repository;
import id.tech.cakra.ecfregistersvc.domain.CampaignRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampaignRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRegisterRepository extends JpaRepository<CampaignRegister, Long> {

}
