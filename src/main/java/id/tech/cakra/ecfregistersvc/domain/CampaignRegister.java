package id.tech.cakra.ecfregistersvc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A CampaignRegister.
 */
@Entity
@Table(name = "campaign_register")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "register_code", length = 10)
    private String registerCode;

    @Size(max = 100)
    @Column(name = "campaign_name", length = 100)
    private String campaignName;

    @Size(max = 500)
    @Column(name = "campaign_desc", length = 500)
    private String campaignDesc;

    @Size(max = 10)
    @Column(name = "investment_type", length = 10)
    private String investmentType;

    @Column(name = "offer_date")
    private LocalDate offerDate;

    @Column(name = "deadline_date")
    private LocalDate deadlineDate;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "fund_target", precision = 21, scale = 2)
    private BigDecimal fundTarget;

    @Column(name = "fund_raised", precision = 21, scale = 2)
    private BigDecimal fundRaised;

    @Column(name = "fund_paid", precision = 21, scale = 2)
    private BigDecimal fundPaid;

    @Column(name = "qty_target", precision = 21, scale = 2)
    private BigDecimal qtyTarget;

    @Column(name = "qty_raised", precision = 21, scale = 2)
    private BigDecimal qtyRaised;

    @Column(name = "est_roi_min", precision = 21, scale = 2)
    private BigDecimal estRoiMin;

    @Column(name = "est_roi_max", precision = 21, scale = 2)
    private BigDecimal estRoiMax;

    @Lob
    @Column(name = "prospectus_file")
    private byte[] prospectusFile;

    @Column(name = "prospectus_file_content_type")
    private String prospectusFileContentType;

    @Lob
    @Column(name = "campaign_logo_1")
    private byte[] campaignLogo1;

    @Column(name = "campaign_logo_1_content_type")
    private String campaignLogo1ContentType;

    @Lob
    @Column(name = "campaign_logo_2")
    private byte[] campaignLogo2;

    @Column(name = "campaign_logo_2_content_type")
    private String campaignLogo2ContentType;

    @Lob
    @Column(name = "campaign_logo_3")
    private byte[] campaignLogo3;

    @Column(name = "campaign_logo_3_content_type")
    private String campaignLogo3ContentType;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "company_bank_id")
    private Long companyBankId;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "create_system_date")
    private LocalDate createSystemDate;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "last_modification_system_date")
    private LocalDate lastModificationSystemDate;

    @Column(name = "last_modification_date")
    private ZonedDateTime lastModificationDate;

    @Column(name = "last_modification_user_id")
    private Long lastModificationUserId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public CampaignRegister registerCode(String registerCode) {
        this.registerCode = registerCode;
        return this;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public CampaignRegister campaignName(String campaignName) {
        this.campaignName = campaignName;
        return this;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDesc() {
        return campaignDesc;
    }

    public CampaignRegister campaignDesc(String campaignDesc) {
        this.campaignDesc = campaignDesc;
        return this;
    }

    public void setCampaignDesc(String campaignDesc) {
        this.campaignDesc = campaignDesc;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public CampaignRegister investmentType(String investmentType) {
        this.investmentType = investmentType;
        return this;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public CampaignRegister offerDate(LocalDate offerDate) {
        this.offerDate = offerDate;
        return this;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public CampaignRegister deadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
        return this;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CampaignRegister price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFundTarget() {
        return fundTarget;
    }

    public CampaignRegister fundTarget(BigDecimal fundTarget) {
        this.fundTarget = fundTarget;
        return this;
    }

    public void setFundTarget(BigDecimal fundTarget) {
        this.fundTarget = fundTarget;
    }

    public BigDecimal getFundRaised() {
        return fundRaised;
    }

    public CampaignRegister fundRaised(BigDecimal fundRaised) {
        this.fundRaised = fundRaised;
        return this;
    }

    public void setFundRaised(BigDecimal fundRaised) {
        this.fundRaised = fundRaised;
    }

    public BigDecimal getFundPaid() {
        return fundPaid;
    }

    public CampaignRegister fundPaid(BigDecimal fundPaid) {
        this.fundPaid = fundPaid;
        return this;
    }

    public void setFundPaid(BigDecimal fundPaid) {
        this.fundPaid = fundPaid;
    }

    public BigDecimal getQtyTarget() {
        return qtyTarget;
    }

    public CampaignRegister qtyTarget(BigDecimal qtyTarget) {
        this.qtyTarget = qtyTarget;
        return this;
    }

    public void setQtyTarget(BigDecimal qtyTarget) {
        this.qtyTarget = qtyTarget;
    }

    public BigDecimal getQtyRaised() {
        return qtyRaised;
    }

    public CampaignRegister qtyRaised(BigDecimal qtyRaised) {
        this.qtyRaised = qtyRaised;
        return this;
    }

    public void setQtyRaised(BigDecimal qtyRaised) {
        this.qtyRaised = qtyRaised;
    }

    public BigDecimal getEstRoiMin() {
        return estRoiMin;
    }

    public CampaignRegister estRoiMin(BigDecimal estRoiMin) {
        this.estRoiMin = estRoiMin;
        return this;
    }

    public void setEstRoiMin(BigDecimal estRoiMin) {
        this.estRoiMin = estRoiMin;
    }

    public BigDecimal getEstRoiMax() {
        return estRoiMax;
    }

    public CampaignRegister estRoiMax(BigDecimal estRoiMax) {
        this.estRoiMax = estRoiMax;
        return this;
    }

    public void setEstRoiMax(BigDecimal estRoiMax) {
        this.estRoiMax = estRoiMax;
    }

    public byte[] getProspectusFile() {
        return prospectusFile;
    }

    public CampaignRegister prospectusFile(byte[] prospectusFile) {
        this.prospectusFile = prospectusFile;
        return this;
    }

    public void setProspectusFile(byte[] prospectusFile) {
        this.prospectusFile = prospectusFile;
    }

    public String getProspectusFileContentType() {
        return prospectusFileContentType;
    }

    public CampaignRegister prospectusFileContentType(String prospectusFileContentType) {
        this.prospectusFileContentType = prospectusFileContentType;
        return this;
    }

    public void setProspectusFileContentType(String prospectusFileContentType) {
        this.prospectusFileContentType = prospectusFileContentType;
    }

    public byte[] getCampaignLogo1() {
        return campaignLogo1;
    }

    public CampaignRegister campaignLogo1(byte[] campaignLogo1) {
        this.campaignLogo1 = campaignLogo1;
        return this;
    }

    public void setCampaignLogo1(byte[] campaignLogo1) {
        this.campaignLogo1 = campaignLogo1;
    }

    public String getCampaignLogo1ContentType() {
        return campaignLogo1ContentType;
    }

    public CampaignRegister campaignLogo1ContentType(String campaignLogo1ContentType) {
        this.campaignLogo1ContentType = campaignLogo1ContentType;
        return this;
    }

    public void setCampaignLogo1ContentType(String campaignLogo1ContentType) {
        this.campaignLogo1ContentType = campaignLogo1ContentType;
    }

    public byte[] getCampaignLogo2() {
        return campaignLogo2;
    }

    public CampaignRegister campaignLogo2(byte[] campaignLogo2) {
        this.campaignLogo2 = campaignLogo2;
        return this;
    }

    public void setCampaignLogo2(byte[] campaignLogo2) {
        this.campaignLogo2 = campaignLogo2;
    }

    public String getCampaignLogo2ContentType() {
        return campaignLogo2ContentType;
    }

    public CampaignRegister campaignLogo2ContentType(String campaignLogo2ContentType) {
        this.campaignLogo2ContentType = campaignLogo2ContentType;
        return this;
    }

    public void setCampaignLogo2ContentType(String campaignLogo2ContentType) {
        this.campaignLogo2ContentType = campaignLogo2ContentType;
    }

    public byte[] getCampaignLogo3() {
        return campaignLogo3;
    }

    public CampaignRegister campaignLogo3(byte[] campaignLogo3) {
        this.campaignLogo3 = campaignLogo3;
        return this;
    }

    public void setCampaignLogo3(byte[] campaignLogo3) {
        this.campaignLogo3 = campaignLogo3;
    }

    public String getCampaignLogo3ContentType() {
        return campaignLogo3ContentType;
    }

    public CampaignRegister campaignLogo3ContentType(String campaignLogo3ContentType) {
        this.campaignLogo3ContentType = campaignLogo3ContentType;
        return this;
    }

    public void setCampaignLogo3ContentType(String campaignLogo3ContentType) {
        this.campaignLogo3ContentType = campaignLogo3ContentType;
    }

    public String getStatus() {
        return status;
    }

    public CampaignRegister status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCompanyBankId() {
        return companyBankId;
    }

    public CampaignRegister companyBankId(Long companyBankId) {
        this.companyBankId = companyBankId;
        return this;
    }

    public void setCompanyBankId(Long companyBankId) {
        this.companyBankId = companyBankId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public CampaignRegister campaignId(Long campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public CampaignRegister accountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public CampaignRegister currencyId(Long currencyId) {
        this.currencyId = currencyId;
        return this;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public CampaignRegister createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public CampaignRegister createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public CampaignRegister createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public CampaignRegister lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public CampaignRegister lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public CampaignRegister lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignRegister)) {
            return false;
        }
        return id != null && id.equals(((CampaignRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignRegister{" +
            "id=" + getId() +
            ", registerCode='" + getRegisterCode() + "'" +
            ", campaignName='" + getCampaignName() + "'" +
            ", campaignDesc='" + getCampaignDesc() + "'" +
            ", investmentType='" + getInvestmentType() + "'" +
            ", offerDate='" + getOfferDate() + "'" +
            ", deadlineDate='" + getDeadlineDate() + "'" +
            ", price=" + getPrice() +
            ", fundTarget=" + getFundTarget() +
            ", fundRaised=" + getFundRaised() +
            ", fundPaid=" + getFundPaid() +
            ", qtyTarget=" + getQtyTarget() +
            ", qtyRaised=" + getQtyRaised() +
            ", estRoiMin=" + getEstRoiMin() +
            ", estRoiMax=" + getEstRoiMax() +
            ", prospectusFile='" + getProspectusFile() + "'" +
            ", prospectusFileContentType='" + getProspectusFileContentType() + "'" +
            ", campaignLogo1='" + getCampaignLogo1() + "'" +
            ", campaignLogo1ContentType='" + getCampaignLogo1ContentType() + "'" +
            ", campaignLogo2='" + getCampaignLogo2() + "'" +
            ", campaignLogo2ContentType='" + getCampaignLogo2ContentType() + "'" +
            ", campaignLogo3='" + getCampaignLogo3() + "'" +
            ", campaignLogo3ContentType='" + getCampaignLogo3ContentType() + "'" +
            ", status='" + getStatus() + "'" +
            ", companyBankId=" + getCompanyBankId() +
            ", campaignId=" + getCampaignId() +
            ", accountId=" + getAccountId() +
            ", currencyId=" + getCurrencyId() +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
