package id.tech.cakra.ecfregistersvc.web.rest;

import id.tech.cakra.ecfregistersvc.EcfregistersvcApp;
import id.tech.cakra.ecfregistersvc.domain.CampaignRegister;
import id.tech.cakra.ecfregistersvc.repository.CampaignRegisterRepository;
import id.tech.cakra.ecfregistersvc.service.CampaignRegisterService;
import id.tech.cakra.ecfregistersvc.service.dto.CampaignRegisterDTO;
import id.tech.cakra.ecfregistersvc.service.mapper.CampaignRegisterMapper;
import id.tech.cakra.ecfregistersvc.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.ecfregistersvc.web.rest.TestUtil.sameInstant;
import static id.tech.cakra.ecfregistersvc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CampaignRegisterResource} REST controller.
 */
@SpringBootTest(classes = EcfregistersvcApp.class)
public class CampaignRegisterResourceIT {

    private static final String DEFAULT_REGISTER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPAIGN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPAIGN_DESC = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OFFER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DEADLINE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEADLINE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_FUND_TARGET = 1D;
    private static final Double UPDATED_FUND_TARGET = 2D;

    private static final Double DEFAULT_FUND_RAISED = 1D;
    private static final Double UPDATED_FUND_RAISED = 2D;

    private static final Double DEFAULT_FUND_PAID = 1D;
    private static final Double UPDATED_FUND_PAID = 2D;

    private static final Long DEFAULT_QTY_TARGET = 1L;
    private static final Long UPDATED_QTY_TARGET = 2L;

    private static final Long DEFAULT_QTY_RAISED = 1L;
    private static final Long UPDATED_QTY_RAISED = 2L;

    private static final Double DEFAULT_EST_ROI_MIN = 1D;
    private static final Double UPDATED_EST_ROI_MIN = 2D;

    private static final Double DEFAULT_EST_ROI_MAX = 1D;
    private static final Double UPDATED_EST_ROI_MAX = 2D;

    private static final byte[] DEFAULT_PROSPECTUS_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROSPECTUS_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROSPECTUS_FILE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CAMPAIGN_LOGO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CAMPAIGN_LOGO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CAMPAIGN_LOGO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CAMPAIGN_LOGO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CAMPAIGN_LOGO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CAMPAIGN_LOGO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CAMPAIGN_LOGO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CAMPAIGN_LOGO_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CAMPAIGN_LOGO_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CAMPAIGN_LOGO_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CAMPAIGN_LOGO_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CAMPAIGN_LOGO_3_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final Long DEFAULT_COMPANY_BANK_ID = 1L;
    private static final Long UPDATED_COMPANY_BANK_ID = 2L;

    private static final Long DEFAULT_CAMPAIGN_ID = 1L;
    private static final Long UPDATED_CAMPAIGN_ID = 2L;

    private static final Long DEFAULT_ACCOUNT_ID = 1L;
    private static final Long UPDATED_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_CURRENCY_ID = 1L;
    private static final Long UPDATED_CURRENCY_ID = 2L;

    private static final LocalDate DEFAULT_CREATE_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_CREATE_USER_ID = 1L;
    private static final Long UPDATED_CREATE_USER_ID = 2L;

    private static final LocalDate DEFAULT_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_LAST_MODIFICATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFICATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_LAST_MODIFICATION_USER_ID = 1L;
    private static final Long UPDATED_LAST_MODIFICATION_USER_ID = 2L;

    @Autowired
    private CampaignRegisterRepository campaignRegisterRepository;

    @Autowired
    private CampaignRegisterMapper campaignRegisterMapper;

    @Autowired
    private CampaignRegisterService campaignRegisterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCampaignRegisterMockMvc;

    private CampaignRegister campaignRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignRegisterResource campaignRegisterResource = new CampaignRegisterResource(campaignRegisterService);
        this.restCampaignRegisterMockMvc = MockMvcBuilders.standaloneSetup(campaignRegisterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignRegister createEntity(EntityManager em) {
        CampaignRegister campaignRegister = new CampaignRegister()
            .registerCode(DEFAULT_REGISTER_CODE)
            .campaignName(DEFAULT_CAMPAIGN_NAME)
            .campaignDesc(DEFAULT_CAMPAIGN_DESC)
            .investmentType(DEFAULT_INVESTMENT_TYPE)
            .offerDate(DEFAULT_OFFER_DATE)
            .deadlineDate(DEFAULT_DEADLINE_DATE)
            .price(DEFAULT_PRICE)
            .fundTarget(DEFAULT_FUND_TARGET)
            .fundRaised(DEFAULT_FUND_RAISED)
            .fundPaid(DEFAULT_FUND_PAID)
            .qtyTarget(DEFAULT_QTY_TARGET)
            .qtyRaised(DEFAULT_QTY_RAISED)
            .estRoiMin(DEFAULT_EST_ROI_MIN)
            .estRoiMax(DEFAULT_EST_ROI_MAX)
            .prospectusFile(DEFAULT_PROSPECTUS_FILE)
            .prospectusFileContentType(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE)
            .campaignLogo1(DEFAULT_CAMPAIGN_LOGO_1)
            .campaignLogo1ContentType(DEFAULT_CAMPAIGN_LOGO_1_CONTENT_TYPE)
            .campaignLogo2(DEFAULT_CAMPAIGN_LOGO_2)
            .campaignLogo2ContentType(DEFAULT_CAMPAIGN_LOGO_2_CONTENT_TYPE)
            .campaignLogo3(DEFAULT_CAMPAIGN_LOGO_3)
            .campaignLogo3ContentType(DEFAULT_CAMPAIGN_LOGO_3_CONTENT_TYPE)
            .status(DEFAULT_STATUS)
            .companyBankId(DEFAULT_COMPANY_BANK_ID)
            .campaignId(DEFAULT_CAMPAIGN_ID)
            .accountId(DEFAULT_ACCOUNT_ID)
            .currencyId(DEFAULT_CURRENCY_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return campaignRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignRegister createUpdatedEntity(EntityManager em) {
        CampaignRegister campaignRegister = new CampaignRegister()
            .registerCode(UPDATED_REGISTER_CODE)
            .campaignName(UPDATED_CAMPAIGN_NAME)
            .campaignDesc(UPDATED_CAMPAIGN_DESC)
            .investmentType(UPDATED_INVESTMENT_TYPE)
            .offerDate(UPDATED_OFFER_DATE)
            .deadlineDate(UPDATED_DEADLINE_DATE)
            .price(UPDATED_PRICE)
            .fundTarget(UPDATED_FUND_TARGET)
            .fundRaised(UPDATED_FUND_RAISED)
            .fundPaid(UPDATED_FUND_PAID)
            .qtyTarget(UPDATED_QTY_TARGET)
            .qtyRaised(UPDATED_QTY_RAISED)
            .estRoiMin(UPDATED_EST_ROI_MIN)
            .estRoiMax(UPDATED_EST_ROI_MAX)
            .prospectusFile(UPDATED_PROSPECTUS_FILE)
            .prospectusFileContentType(UPDATED_PROSPECTUS_FILE_CONTENT_TYPE)
            .campaignLogo1(UPDATED_CAMPAIGN_LOGO_1)
            .campaignLogo1ContentType(UPDATED_CAMPAIGN_LOGO_1_CONTENT_TYPE)
            .campaignLogo2(UPDATED_CAMPAIGN_LOGO_2)
            .campaignLogo2ContentType(UPDATED_CAMPAIGN_LOGO_2_CONTENT_TYPE)
            .campaignLogo3(UPDATED_CAMPAIGN_LOGO_3)
            .campaignLogo3ContentType(UPDATED_CAMPAIGN_LOGO_3_CONTENT_TYPE)
            .status(UPDATED_STATUS)
            .companyBankId(UPDATED_COMPANY_BANK_ID)
            .campaignId(UPDATED_CAMPAIGN_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .currencyId(UPDATED_CURRENCY_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return campaignRegister;
    }

    @BeforeEach
    public void initTest() {
        campaignRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignRegister() throws Exception {
        int databaseSizeBeforeCreate = campaignRegisterRepository.findAll().size();

        // Create the CampaignRegister
        CampaignRegisterDTO campaignRegisterDTO = campaignRegisterMapper.toDto(campaignRegister);
        restCampaignRegisterMockMvc.perform(post("/api/campaign-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignRegister in the database
        List<CampaignRegister> campaignRegisterList = campaignRegisterRepository.findAll();
        assertThat(campaignRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignRegister testCampaignRegister = campaignRegisterList.get(campaignRegisterList.size() - 1);
        assertThat(testCampaignRegister.getRegisterCode()).isEqualTo(DEFAULT_REGISTER_CODE);
        assertThat(testCampaignRegister.getCampaignName()).isEqualTo(DEFAULT_CAMPAIGN_NAME);
        assertThat(testCampaignRegister.getCampaignDesc()).isEqualTo(DEFAULT_CAMPAIGN_DESC);
        assertThat(testCampaignRegister.getInvestmentType()).isEqualTo(DEFAULT_INVESTMENT_TYPE);
        assertThat(testCampaignRegister.getOfferDate()).isEqualTo(DEFAULT_OFFER_DATE);
        assertThat(testCampaignRegister.getDeadlineDate()).isEqualTo(DEFAULT_DEADLINE_DATE);
        assertThat(testCampaignRegister.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testCampaignRegister.getFundTarget()).isEqualTo(DEFAULT_FUND_TARGET);
        assertThat(testCampaignRegister.getFundRaised()).isEqualTo(DEFAULT_FUND_RAISED);
        assertThat(testCampaignRegister.getFundPaid()).isEqualTo(DEFAULT_FUND_PAID);
        assertThat(testCampaignRegister.getQtyTarget()).isEqualTo(DEFAULT_QTY_TARGET);
        assertThat(testCampaignRegister.getQtyRaised()).isEqualTo(DEFAULT_QTY_RAISED);
        assertThat(testCampaignRegister.getEstRoiMin()).isEqualTo(DEFAULT_EST_ROI_MIN);
        assertThat(testCampaignRegister.getEstRoiMax()).isEqualTo(DEFAULT_EST_ROI_MAX);
        assertThat(testCampaignRegister.getProspectusFile()).isEqualTo(DEFAULT_PROSPECTUS_FILE);
        assertThat(testCampaignRegister.getProspectusFileContentType()).isEqualTo(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE);
        assertThat(testCampaignRegister.getCampaignLogo1()).isEqualTo(DEFAULT_CAMPAIGN_LOGO_1);
        assertThat(testCampaignRegister.getCampaignLogo1ContentType()).isEqualTo(DEFAULT_CAMPAIGN_LOGO_1_CONTENT_TYPE);
        assertThat(testCampaignRegister.getCampaignLogo2()).isEqualTo(DEFAULT_CAMPAIGN_LOGO_2);
        assertThat(testCampaignRegister.getCampaignLogo2ContentType()).isEqualTo(DEFAULT_CAMPAIGN_LOGO_2_CONTENT_TYPE);
        assertThat(testCampaignRegister.getCampaignLogo3()).isEqualTo(DEFAULT_CAMPAIGN_LOGO_3);
        assertThat(testCampaignRegister.getCampaignLogo3ContentType()).isEqualTo(DEFAULT_CAMPAIGN_LOGO_3_CONTENT_TYPE);
        assertThat(testCampaignRegister.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCampaignRegister.getCompanyBankId()).isEqualTo(DEFAULT_COMPANY_BANK_ID);
        assertThat(testCampaignRegister.getCampaignId()).isEqualTo(DEFAULT_CAMPAIGN_ID);
        assertThat(testCampaignRegister.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
        assertThat(testCampaignRegister.getCurrencyId()).isEqualTo(DEFAULT_CURRENCY_ID);
        assertThat(testCampaignRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testCampaignRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCampaignRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testCampaignRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testCampaignRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createCampaignRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignRegisterRepository.findAll().size();

        // Create the CampaignRegister with an existing ID
        campaignRegister.setId(1L);
        CampaignRegisterDTO campaignRegisterDTO = campaignRegisterMapper.toDto(campaignRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignRegisterMockMvc.perform(post("/api/campaign-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignRegister in the database
        List<CampaignRegister> campaignRegisterList = campaignRegisterRepository.findAll();
        assertThat(campaignRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignRegisters() throws Exception {
        // Initialize the database
        campaignRegisterRepository.saveAndFlush(campaignRegister);

        // Get all the campaignRegisterList
        restCampaignRegisterMockMvc.perform(get("/api/campaign-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].registerCode").value(hasItem(DEFAULT_REGISTER_CODE)))
            .andExpect(jsonPath("$.[*].campaignName").value(hasItem(DEFAULT_CAMPAIGN_NAME)))
            .andExpect(jsonPath("$.[*].campaignDesc").value(hasItem(DEFAULT_CAMPAIGN_DESC)))
            .andExpect(jsonPath("$.[*].investmentType").value(hasItem(DEFAULT_INVESTMENT_TYPE)))
            .andExpect(jsonPath("$.[*].offerDate").value(hasItem(DEFAULT_OFFER_DATE.toString())))
            .andExpect(jsonPath("$.[*].deadlineDate").value(hasItem(DEFAULT_DEADLINE_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].fundTarget").value(hasItem(DEFAULT_FUND_TARGET.doubleValue())))
            .andExpect(jsonPath("$.[*].fundRaised").value(hasItem(DEFAULT_FUND_RAISED.doubleValue())))
            .andExpect(jsonPath("$.[*].fundPaid").value(hasItem(DEFAULT_FUND_PAID.doubleValue())))
            .andExpect(jsonPath("$.[*].qtyTarget").value(hasItem(DEFAULT_QTY_TARGET.intValue())))
            .andExpect(jsonPath("$.[*].qtyRaised").value(hasItem(DEFAULT_QTY_RAISED.intValue())))
            .andExpect(jsonPath("$.[*].estRoiMin").value(hasItem(DEFAULT_EST_ROI_MIN.doubleValue())))
            .andExpect(jsonPath("$.[*].estRoiMax").value(hasItem(DEFAULT_EST_ROI_MAX.doubleValue())))
            .andExpect(jsonPath("$.[*].prospectusFileContentType").value(hasItem(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].prospectusFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROSPECTUS_FILE))))
            .andExpect(jsonPath("$.[*].campaignLogo1ContentType").value(hasItem(DEFAULT_CAMPAIGN_LOGO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campaignLogo1").value(hasItem(Base64Utils.encodeToString(DEFAULT_CAMPAIGN_LOGO_1))))
            .andExpect(jsonPath("$.[*].campaignLogo2ContentType").value(hasItem(DEFAULT_CAMPAIGN_LOGO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campaignLogo2").value(hasItem(Base64Utils.encodeToString(DEFAULT_CAMPAIGN_LOGO_2))))
            .andExpect(jsonPath("$.[*].campaignLogo3ContentType").value(hasItem(DEFAULT_CAMPAIGN_LOGO_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campaignLogo3").value(hasItem(Base64Utils.encodeToString(DEFAULT_CAMPAIGN_LOGO_3))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].companyBankId").value(hasItem(DEFAULT_COMPANY_BANK_ID.intValue())))
            .andExpect(jsonPath("$.[*].campaignId").value(hasItem(DEFAULT_CAMPAIGN_ID.intValue())))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID.intValue())))
            .andExpect(jsonPath("$.[*].currencyId").value(hasItem(DEFAULT_CURRENCY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCampaignRegister() throws Exception {
        // Initialize the database
        campaignRegisterRepository.saveAndFlush(campaignRegister);

        // Get the campaignRegister
        restCampaignRegisterMockMvc.perform(get("/api/campaign-registers/{id}", campaignRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignRegister.getId().intValue()))
            .andExpect(jsonPath("$.registerCode").value(DEFAULT_REGISTER_CODE))
            .andExpect(jsonPath("$.campaignName").value(DEFAULT_CAMPAIGN_NAME))
            .andExpect(jsonPath("$.campaignDesc").value(DEFAULT_CAMPAIGN_DESC))
            .andExpect(jsonPath("$.investmentType").value(DEFAULT_INVESTMENT_TYPE))
            .andExpect(jsonPath("$.offerDate").value(DEFAULT_OFFER_DATE.toString()))
            .andExpect(jsonPath("$.deadlineDate").value(DEFAULT_DEADLINE_DATE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.fundTarget").value(DEFAULT_FUND_TARGET.doubleValue()))
            .andExpect(jsonPath("$.fundRaised").value(DEFAULT_FUND_RAISED.doubleValue()))
            .andExpect(jsonPath("$.fundPaid").value(DEFAULT_FUND_PAID.doubleValue()))
            .andExpect(jsonPath("$.qtyTarget").value(DEFAULT_QTY_TARGET.intValue()))
            .andExpect(jsonPath("$.qtyRaised").value(DEFAULT_QTY_RAISED.intValue()))
            .andExpect(jsonPath("$.estRoiMin").value(DEFAULT_EST_ROI_MIN.doubleValue()))
            .andExpect(jsonPath("$.estRoiMax").value(DEFAULT_EST_ROI_MAX.doubleValue()))
            .andExpect(jsonPath("$.prospectusFileContentType").value(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.prospectusFile").value(Base64Utils.encodeToString(DEFAULT_PROSPECTUS_FILE)))
            .andExpect(jsonPath("$.campaignLogo1ContentType").value(DEFAULT_CAMPAIGN_LOGO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.campaignLogo1").value(Base64Utils.encodeToString(DEFAULT_CAMPAIGN_LOGO_1)))
            .andExpect(jsonPath("$.campaignLogo2ContentType").value(DEFAULT_CAMPAIGN_LOGO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.campaignLogo2").value(Base64Utils.encodeToString(DEFAULT_CAMPAIGN_LOGO_2)))
            .andExpect(jsonPath("$.campaignLogo3ContentType").value(DEFAULT_CAMPAIGN_LOGO_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.campaignLogo3").value(Base64Utils.encodeToString(DEFAULT_CAMPAIGN_LOGO_3)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.companyBankId").value(DEFAULT_COMPANY_BANK_ID.intValue()))
            .andExpect(jsonPath("$.campaignId").value(DEFAULT_CAMPAIGN_ID.intValue()))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.currencyId").value(DEFAULT_CURRENCY_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignRegister() throws Exception {
        // Get the campaignRegister
        restCampaignRegisterMockMvc.perform(get("/api/campaign-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignRegister() throws Exception {
        // Initialize the database
        campaignRegisterRepository.saveAndFlush(campaignRegister);

        int databaseSizeBeforeUpdate = campaignRegisterRepository.findAll().size();

        // Update the campaignRegister
        CampaignRegister updatedCampaignRegister = campaignRegisterRepository.findById(campaignRegister.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignRegister are not directly saved in db
        em.detach(updatedCampaignRegister);
        updatedCampaignRegister
            .registerCode(UPDATED_REGISTER_CODE)
            .campaignName(UPDATED_CAMPAIGN_NAME)
            .campaignDesc(UPDATED_CAMPAIGN_DESC)
            .investmentType(UPDATED_INVESTMENT_TYPE)
            .offerDate(UPDATED_OFFER_DATE)
            .deadlineDate(UPDATED_DEADLINE_DATE)
            .price(UPDATED_PRICE)
            .fundTarget(UPDATED_FUND_TARGET)
            .fundRaised(UPDATED_FUND_RAISED)
            .fundPaid(UPDATED_FUND_PAID)
            .qtyTarget(UPDATED_QTY_TARGET)
            .qtyRaised(UPDATED_QTY_RAISED)
            .estRoiMin(UPDATED_EST_ROI_MIN)
            .estRoiMax(UPDATED_EST_ROI_MAX)
            .prospectusFile(UPDATED_PROSPECTUS_FILE)
            .prospectusFileContentType(UPDATED_PROSPECTUS_FILE_CONTENT_TYPE)
            .campaignLogo1(UPDATED_CAMPAIGN_LOGO_1)
            .campaignLogo1ContentType(UPDATED_CAMPAIGN_LOGO_1_CONTENT_TYPE)
            .campaignLogo2(UPDATED_CAMPAIGN_LOGO_2)
            .campaignLogo2ContentType(UPDATED_CAMPAIGN_LOGO_2_CONTENT_TYPE)
            .campaignLogo3(UPDATED_CAMPAIGN_LOGO_3)
            .campaignLogo3ContentType(UPDATED_CAMPAIGN_LOGO_3_CONTENT_TYPE)
            .status(UPDATED_STATUS)
            .companyBankId(UPDATED_COMPANY_BANK_ID)
            .campaignId(UPDATED_CAMPAIGN_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .currencyId(UPDATED_CURRENCY_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        CampaignRegisterDTO campaignRegisterDTO = campaignRegisterMapper.toDto(updatedCampaignRegister);

        restCampaignRegisterMockMvc.perform(put("/api/campaign-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignRegister in the database
        List<CampaignRegister> campaignRegisterList = campaignRegisterRepository.findAll();
        assertThat(campaignRegisterList).hasSize(databaseSizeBeforeUpdate);
        CampaignRegister testCampaignRegister = campaignRegisterList.get(campaignRegisterList.size() - 1);
        assertThat(testCampaignRegister.getRegisterCode()).isEqualTo(UPDATED_REGISTER_CODE);
        assertThat(testCampaignRegister.getCampaignName()).isEqualTo(UPDATED_CAMPAIGN_NAME);
        assertThat(testCampaignRegister.getCampaignDesc()).isEqualTo(UPDATED_CAMPAIGN_DESC);
        assertThat(testCampaignRegister.getInvestmentType()).isEqualTo(UPDATED_INVESTMENT_TYPE);
        assertThat(testCampaignRegister.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testCampaignRegister.getDeadlineDate()).isEqualTo(UPDATED_DEADLINE_DATE);
        assertThat(testCampaignRegister.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testCampaignRegister.getFundTarget()).isEqualTo(UPDATED_FUND_TARGET);
        assertThat(testCampaignRegister.getFundRaised()).isEqualTo(UPDATED_FUND_RAISED);
        assertThat(testCampaignRegister.getFundPaid()).isEqualTo(UPDATED_FUND_PAID);
        assertThat(testCampaignRegister.getQtyTarget()).isEqualTo(UPDATED_QTY_TARGET);
        assertThat(testCampaignRegister.getQtyRaised()).isEqualTo(UPDATED_QTY_RAISED);
        assertThat(testCampaignRegister.getEstRoiMin()).isEqualTo(UPDATED_EST_ROI_MIN);
        assertThat(testCampaignRegister.getEstRoiMax()).isEqualTo(UPDATED_EST_ROI_MAX);
        assertThat(testCampaignRegister.getProspectusFile()).isEqualTo(UPDATED_PROSPECTUS_FILE);
        assertThat(testCampaignRegister.getProspectusFileContentType()).isEqualTo(UPDATED_PROSPECTUS_FILE_CONTENT_TYPE);
        assertThat(testCampaignRegister.getCampaignLogo1()).isEqualTo(UPDATED_CAMPAIGN_LOGO_1);
        assertThat(testCampaignRegister.getCampaignLogo1ContentType()).isEqualTo(UPDATED_CAMPAIGN_LOGO_1_CONTENT_TYPE);
        assertThat(testCampaignRegister.getCampaignLogo2()).isEqualTo(UPDATED_CAMPAIGN_LOGO_2);
        assertThat(testCampaignRegister.getCampaignLogo2ContentType()).isEqualTo(UPDATED_CAMPAIGN_LOGO_2_CONTENT_TYPE);
        assertThat(testCampaignRegister.getCampaignLogo3()).isEqualTo(UPDATED_CAMPAIGN_LOGO_3);
        assertThat(testCampaignRegister.getCampaignLogo3ContentType()).isEqualTo(UPDATED_CAMPAIGN_LOGO_3_CONTENT_TYPE);
        assertThat(testCampaignRegister.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCampaignRegister.getCompanyBankId()).isEqualTo(UPDATED_COMPANY_BANK_ID);
        assertThat(testCampaignRegister.getCampaignId()).isEqualTo(UPDATED_CAMPAIGN_ID);
        assertThat(testCampaignRegister.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
        assertThat(testCampaignRegister.getCurrencyId()).isEqualTo(UPDATED_CURRENCY_ID);
        assertThat(testCampaignRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testCampaignRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCampaignRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testCampaignRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testCampaignRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignRegister() throws Exception {
        int databaseSizeBeforeUpdate = campaignRegisterRepository.findAll().size();

        // Create the CampaignRegister
        CampaignRegisterDTO campaignRegisterDTO = campaignRegisterMapper.toDto(campaignRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignRegisterMockMvc.perform(put("/api/campaign-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignRegister in the database
        List<CampaignRegister> campaignRegisterList = campaignRegisterRepository.findAll();
        assertThat(campaignRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignRegister() throws Exception {
        // Initialize the database
        campaignRegisterRepository.saveAndFlush(campaignRegister);

        int databaseSizeBeforeDelete = campaignRegisterRepository.findAll().size();

        // Delete the campaignRegister
        restCampaignRegisterMockMvc.perform(delete("/api/campaign-registers/{id}", campaignRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignRegister> campaignRegisterList = campaignRegisterRepository.findAll();
        assertThat(campaignRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
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

    @Test
    @Transactional
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

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignRegisterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignRegisterMapper.fromId(null)).isNull();
    }
}
