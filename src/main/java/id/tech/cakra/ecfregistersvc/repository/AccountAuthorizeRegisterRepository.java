package id.tech.cakra.ecfregistersvc.repository;
import id.tech.cakra.ecfregistersvc.domain.AccountAuthorizeRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountAuthorizeRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountAuthorizeRegisterRepository extends JpaRepository<AccountAuthorizeRegister, Long> {

}
