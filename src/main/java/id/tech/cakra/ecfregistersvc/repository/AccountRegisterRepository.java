package id.tech.cakra.ecfregistersvc.repository;
import id.tech.cakra.ecfregistersvc.domain.AccountRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountRegisterRepository extends JpaRepository<AccountRegister, Long> {

}
