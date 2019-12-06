package id.tech.cakra.ecfregistersvc.repository;
import id.tech.cakra.ecfregistersvc.domain.AccountBankRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountBankRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountBankRegisterRepository extends JpaRepository<AccountBankRegister, Long> {

}
