package com.jumper.hospital.entity;

import java.io.Serializable;
import java.util.Date;

public class UserHistoryTakingInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer archiveId;

    private Integer menophaniaAge;//初潮年龄

    private String menstrualCycle;//月经周期

    private Date firstAffirmPregnancyDate;//初次确诊怀孕时间

    private String pregnancyTime;//怀孕周期

    private Integer morningSicknessState;//0:无；1:有

    private String morningSicknessTime;//早孕反应周期

    private Integer quickening;//初感胎动0:无;1:有
    
    private Integer quickeningTime;//初感胎动周期

    private Integer isAbnormal;//0:无;1:有

    private String abnormalContent;//异常内容

    private Integer takingFolicAcid;//叶酸服用0:无;1:有

    private Integer folicAcidProgestation;//孕前0:无;1:1月;2:2月;3:3月

    private Integer folicAcidMorningSickness;//孕早期0:无;1:1月;2:2月;3:3月

    private Integer firstExamineAfterMenelipsis;//第一次检查尿检阳性是停经后的天数

    private Integer feverState;//发热0:无;1:有

    private String feverDevelop;//发热伴随症状

    private Integer colporrhagiaState;//阴道出血0:无;1:有

    private String colporrhagiaDevelop;//阴道出血伴随状态

    private Integer animalContact;//猫狗等动物接触史0:无;1:有

    private String animalContactTime;//接触时间:格式1m1d;2m2d

    private Integer diabetesMellitus;//糖尿病0:无;1:有

    private Integer takeTeratogenicDrug;//服用可能致畸药物0:无;1:有

    private String takeTeratogenicDrugContent;//药物及时间格式：阿莫西林+2016-08-04+2016-08-04;

    private Integer contactPhysicsHarmful;//接触物理性有害因素0:无;1:有

    private String contactPhysicsHarmfulContent;//有害因素

    private Integer contactIndustrialToxicant;//接触高浓度工业毒物0:无;1:有

    private String contactIndustrialToxicantContent;//工业毒物

    private Integer environmentalNewDecoration;//环境新装修0:无;1:有

    private Integer takeDrugs;//吸毒0:无;1:有

    private Integer drinkHistoryState;//饮酒史0:无;1:有

    private Date drinkStartTime;//开始时间

    private Float whiteSpirit;//白酒

    private Float redSpirit;//红酒

    private Float beer;//啤酒

    private Integer drinkFrequency;//饮酒频率

    private String drinkDescription;//饮酒描述

    private Date abstinenceStartTime;//戒酒开始时间

    private Date abstinenceEndTime;//戒酒结束时间

    private Integer smokeHistoryState;//吸烟0:无;1:被动;2:主动

    private Date smokeStartTime;//开始时间

    private Date smokeEndTime;//结束时间

    private Integer smokeAmount;//吸烟数量（支/天）

    private String smokeDescription;//吸烟描述

    private Integer virusInfectionState;//病毒感染0:无;1:有

    private String virusInfection;//病毒感染

    private Date virusInfectionTime;//感染时间

    private String otherHarmfulFactors;//其他有害因素

    private Integer pregnancyCount;//孕产次数

    private Integer pregnancyHistoryState;//0：无；1：有

    private Integer anamnesisState;//既往病史0：无；1：有
    
    private Integer familyHistoryState;//家族史0：无；1：有

    private Date addTime;//创建时间
    
    private String chiefComplaint;//主诉

    private String presentHistory;//现病史

    private String otherAnamnesis;//其他病史

    private String fatherFamilyHistory;//父亲家族史

    private String motherFamilyHistory;//母亲家族史

    private String brotherFamilyHistory;//兄弟姐妹其他家族史

    private String chirdFamilyHistory;//子女其他家族史

    private Integer operationHistoryState;//手术史

    private Date operationTime;//手术时间

    private String operationName;//手术名称

    private Integer bloodTransfusionHistoryState;//输血史0:无；1：有

    private Integer allergicHistoryState;//过敏史0：无；1：有
    
    private Integer infertilityHistoryState;//不孕不育史0:无；1:有

    private String infertilityHistory;//不孕不育史

    private Integer vaginaProduced;//阴道产

    private Integer vaginaOperationProduced;//阴道手术产

    private Integer caesareanSection;//剖宫产

    private Integer obstetricForceps;//产钳

    private Integer ectopicPregnancy;//宫外产

    private Integer otherDeliver;//其他助产

    private Date operationLastTime;//末次手术时间

    private String operationIndication;//手术指征

    private Integer complicationState;//并发症0：无；1：有

    private Integer spontaneousAbortion;//自然流产（次）

    private Integer inducedAbortion;//人工流产（次）

    private Integer inducedLabour;//引产（次）

    private Integer prematureLabour;//早产（次）

    private Integer stillbirthLabour;//死胎产（次）

    private Integer embryoDamage;//胚胎停育（次）

    private Integer lastAbortionState;//末次流产0：无；1：有

    private String abortionType;//流产类型

    private Date abortionTime;//流产时间

    private Date lastPregnancyTime;//末次妊娠时间

    private Integer perinatalMortalityState;//新生儿死亡0：无；1：有

    private Integer perinatalMortality;//新生儿死亡（次）

    private Date deathTime;//死亡时间

    private String deathReason;//死亡原因

    private String nowSurviverChildren;//现有存活子女

    private Integer alwaysBirthDefectsState;//既往出生缺陷0：无；1：有

    private Date birthday;//出生时间

    private String defectsDescription;//缺陷描述

    private Integer ending;//1：存活；2：矫治痊愈；3：残障；4：夭折

    private Integer alwaysPregnancyHistoryState;//既往妊娠合并症史0：无；1：有

    private String symptomDescription;//症状描述

    private String otherPergnancyHistory;//其他孕产史
    
    private String anamnesis;//既往病史
    
    private String familyHistory;//家族病史
    
    private String allergicType;//过敏类型
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(Integer archiveId) {
		this.archiveId = archiveId;
	}

	public Integer getMenophaniaAge() {
		return menophaniaAge;
	}

	public void setMenophaniaAge(Integer menophaniaAge) {
		this.menophaniaAge = menophaniaAge;
	}

	public String getMenstrualCycle() {
		return menstrualCycle;
	}

	public void setMenstrualCycle(String menstrualCycle) {
		this.menstrualCycle = menstrualCycle;
	}

	public Date getFirstAffirmPregnancyDate() {
		return firstAffirmPregnancyDate;
	}

	public void setFirstAffirmPregnancyDate(Date firstAffirmPregnancyDate) {
		this.firstAffirmPregnancyDate = firstAffirmPregnancyDate;
	}

	public String getPregnancyTime() {
		return pregnancyTime;
	}

	public void setPregnancyTime(String pregnancyTime) {
		this.pregnancyTime = pregnancyTime;
	}

	public Integer getMorningSicknessState() {
		return morningSicknessState;
	}

	public void setMorningSicknessState(Integer morningSicknessState) {
		this.morningSicknessState = morningSicknessState;
	}

	public String getMorningSicknessTime() {
		return morningSicknessTime;
	}

	public void setMorningSicknessTime(String morningSicknessTime) {
		this.morningSicknessTime = morningSicknessTime;
	}

	public Integer getQuickening() {
		return quickening;
	}

	public void setQuickening(Integer quickening) {
		this.quickening = quickening;
	}

	public Integer getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(Integer isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getAbnormalContent() {
		return abnormalContent;
	}

	public void setAbnormalContent(String abnormalContent) {
		this.abnormalContent = abnormalContent;
	}

	public Integer getTakingFolicAcid() {
		return takingFolicAcid;
	}

	public void setTakingFolicAcid(Integer takingFolicAcid) {
		this.takingFolicAcid = takingFolicAcid;
	}

	public Integer getFolicAcidProgestation() {
		return folicAcidProgestation;
	}

	public void setFolicAcidProgestation(Integer folicAcidProgestation) {
		this.folicAcidProgestation = folicAcidProgestation;
	}

	public Integer getFolicAcidMorningSickness() {
		return folicAcidMorningSickness;
	}

	public void setFolicAcidMorningSickness(Integer folicAcidMorningSickness) {
		this.folicAcidMorningSickness = folicAcidMorningSickness;
	}

	public Integer getFirstExamineAfterMenelipsis() {
		return firstExamineAfterMenelipsis;
	}

	public void setFirstExamineAfterMenelipsis(Integer firstExamineAfterMenelipsis) {
		this.firstExamineAfterMenelipsis = firstExamineAfterMenelipsis;
	}

	public Integer getFeverState() {
		return feverState;
	}

	public void setFeverState(Integer feverState) {
		this.feverState = feverState;
	}

	public String getFeverDevelop() {
		return feverDevelop;
	}

	public void setFeverDevelop(String feverDevelop) {
		this.feverDevelop = feverDevelop;
	}

	public Integer getColporrhagiaState() {
		return colporrhagiaState;
	}

	public void setColporrhagiaState(Integer colporrhagiaState) {
		this.colporrhagiaState = colporrhagiaState;
	}

	public String getColporrhagiaDevelop() {
		return colporrhagiaDevelop;
	}

	public void setColporrhagiaDevelop(String colporrhagiaDevelop) {
		this.colporrhagiaDevelop = colporrhagiaDevelop;
	}

	public Integer getAnimalContact() {
		return animalContact;
	}

	public void setAnimalContact(Integer animalContact) {
		this.animalContact = animalContact;
	}

	public String getAnimalContactTime() {
		return animalContactTime;
	}

	public void setAnimalContactTime(String animalContactTime) {
		this.animalContactTime = animalContactTime;
	}

	public Integer getDiabetesMellitus() {
		return diabetesMellitus;
	}

	public void setDiabetesMellitus(Integer diabetesMellitus) {
		this.diabetesMellitus = diabetesMellitus;
	}

	public Integer getTakeTeratogenicDrug() {
		return takeTeratogenicDrug;
	}

	public void setTakeTeratogenicDrug(Integer takeTeratogenicDrug) {
		this.takeTeratogenicDrug = takeTeratogenicDrug;
	}

	public String getTakeTeratogenicDrugContent() {
		return takeTeratogenicDrugContent;
	}

	public void setTakeTeratogenicDrugContent(String takeTeratogenicDrugContent) {
		this.takeTeratogenicDrugContent = takeTeratogenicDrugContent;
	}

	public Integer getContactPhysicsHarmful() {
		return contactPhysicsHarmful;
	}

	public void setContactPhysicsHarmful(Integer contactPhysicsHarmful) {
		this.contactPhysicsHarmful = contactPhysicsHarmful;
	}

	public String getContactPhysicsHarmfulContent() {
		return contactPhysicsHarmfulContent;
	}

	public void setContactPhysicsHarmfulContent(String contactPhysicsHarmfulContent) {
		this.contactPhysicsHarmfulContent = contactPhysicsHarmfulContent;
	}

	public Integer getContactIndustrialToxicant() {
		return contactIndustrialToxicant;
	}

	public void setContactIndustrialToxicant(Integer contactIndustrialToxicant) {
		this.contactIndustrialToxicant = contactIndustrialToxicant;
	}

	public String getContactIndustrialToxicantContent() {
		return contactIndustrialToxicantContent;
	}

	public void setContactIndustrialToxicantContent(
			String contactIndustrialToxicantContent) {
		this.contactIndustrialToxicantContent = contactIndustrialToxicantContent;
	}

	public Integer getEnvironmentalNewDecoration() {
		return environmentalNewDecoration;
	}

	public void setEnvironmentalNewDecoration(Integer environmentalNewDecoration) {
		this.environmentalNewDecoration = environmentalNewDecoration;
	}

	public Integer getTakeDrugs() {
		return takeDrugs;
	}

	public void setTakeDrugs(Integer takeDrugs) {
		this.takeDrugs = takeDrugs;
	}

	public Integer getDrinkHistoryState() {
		return drinkHistoryState;
	}

	public void setDrinkHistoryState(Integer drinkHistoryState) {
		this.drinkHistoryState = drinkHistoryState;
	}

	public Date getDrinkStartTime() {
		return drinkStartTime;
	}

	public void setDrinkStartTime(Date drinkStartTime) {
		this.drinkStartTime = drinkStartTime;
	}

	public Float getWhiteSpirit() {
		return whiteSpirit;
	}

	public void setWhiteSpirit(Float whiteSpirit) {
		this.whiteSpirit = whiteSpirit;
	}

	public Float getRedSpirit() {
		return redSpirit;
	}

	public void setRedSpirit(Float redSpirit) {
		this.redSpirit = redSpirit;
	}

	public Float getBeer() {
		return beer;
	}

	public void setBeer(Float beer) {
		this.beer = beer;
	}

	public Integer getDrinkFrequency() {
		return drinkFrequency;
	}

	public void setDrinkFrequency(Integer drinkFrequency) {
		this.drinkFrequency = drinkFrequency;
	}

	public String getDrinkDescription() {
		return drinkDescription;
	}

	public void setDrinkDescription(String drinkDescription) {
		this.drinkDescription = drinkDescription;
	}

	public Date getAbstinenceStartTime() {
		return abstinenceStartTime;
	}

	public void setAbstinenceStartTime(Date abstinenceStartTime) {
		this.abstinenceStartTime = abstinenceStartTime;
	}

	public Date getAbstinenceEndTime() {
		return abstinenceEndTime;
	}

	public void setAbstinenceEndTime(Date abstinenceEndTime) {
		this.abstinenceEndTime = abstinenceEndTime;
	}

	public Integer getSmokeHistoryState() {
		return smokeHistoryState;
	}

	public void setSmokeHistoryState(Integer smokeHistoryState) {
		this.smokeHistoryState = smokeHistoryState;
	}

	public Date getSmokeStartTime() {
		return smokeStartTime;
	}

	public void setSmokeStartTime(Date smokeStartTime) {
		this.smokeStartTime = smokeStartTime;
	}

	public Date getSmokeEndTime() {
		return smokeEndTime;
	}

	public void setSmokeEndTime(Date smokeEndTime) {
		this.smokeEndTime = smokeEndTime;
	}

	public Integer getSmokeAmount() {
		return smokeAmount;
	}

	public void setSmokeAmount(Integer smokeAmount) {
		this.smokeAmount = smokeAmount;
	}

	public String getSmokeDescription() {
		return smokeDescription;
	}

	public void setSmokeDescription(String smokeDescription) {
		this.smokeDescription = smokeDescription;
	}

	public Integer getVirusInfectionState() {
		return virusInfectionState;
	}

	public void setVirusInfectionState(Integer virusInfectionState) {
		this.virusInfectionState = virusInfectionState;
	}

	public String getVirusInfection() {
		return virusInfection;
	}

	public void setVirusInfection(String virusInfection) {
		this.virusInfection = virusInfection;
	}

	public Date getVirusInfectionTime() {
		return virusInfectionTime;
	}

	public void setVirusInfectionTime(Date virusInfectionTime) {
		this.virusInfectionTime = virusInfectionTime;
	}

	public String getOtherHarmfulFactors() {
		return otherHarmfulFactors;
	}

	public void setOtherHarmfulFactors(String otherHarmfulFactors) {
		this.otherHarmfulFactors = otherHarmfulFactors;
	}

	public Integer getPregnancyCount() {
		return pregnancyCount;
	}

	public void setPregnancyCount(Integer pregnancyCount) {
		this.pregnancyCount = pregnancyCount;
	}

	public Integer getPregnancyHistoryState() {
		return pregnancyHistoryState;
	}

	public void setPregnancyHistoryState(Integer pregnancyHistoryState) {
		this.pregnancyHistoryState = pregnancyHistoryState;
	}

	public Integer getAnamnesisState() {
		return anamnesisState;
	}

	public void setAnamnesisState(Integer anamnesisState) {
		this.anamnesisState = anamnesisState;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getChiefComplaint() {
		return chiefComplaint;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getPresentHistory() {
		return presentHistory;
	}

	public void setPresentHistory(String presentHistory) {
		this.presentHistory = presentHistory;
	}

	public String getOtherAnamnesis() {
		return otherAnamnesis;
	}

	public void setOtherAnamnesis(String otherAnamnesis) {
		this.otherAnamnesis = otherAnamnesis;
	}

	public String getFatherFamilyHistory() {
		return fatherFamilyHistory;
	}

	public void setFatherFamilyHistory(String fatherFamilyHistory) {
		this.fatherFamilyHistory = fatherFamilyHistory;
	}

	public String getMotherFamilyHistory() {
		return motherFamilyHistory;
	}

	public void setMotherFamilyHistory(String motherFamilyHistory) {
		this.motherFamilyHistory = motherFamilyHistory;
	}

	public String getBrotherFamilyHistory() {
		return brotherFamilyHistory;
	}

	public void setBrotherFamilyHistory(String brotherFamilyHistory) {
		this.brotherFamilyHistory = brotherFamilyHistory;
	}

	public String getChirdFamilyHistory() {
		return chirdFamilyHistory;
	}

	public void setChirdFamilyHistory(String chirdFamilyHistory) {
		this.chirdFamilyHistory = chirdFamilyHistory;
	}

	public Integer getOperationHistoryState() {
		return operationHistoryState;
	}

	public void setOperationHistoryState(Integer operationHistoryState) {
		this.operationHistoryState = operationHistoryState;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Integer getBloodTransfusionHistoryState() {
		return bloodTransfusionHistoryState;
	}

	public void setBloodTransfusionHistoryState(Integer bloodTransfusionHistoryState) {
		this.bloodTransfusionHistoryState = bloodTransfusionHistoryState;
	}

	public Integer getAllergicHistoryState() {
		return allergicHistoryState;
	}

	public void setAllergicHistoryState(Integer allergicHistoryState) {
		this.allergicHistoryState = allergicHistoryState;
	}

	public Integer getInfertilityHistoryState() {
		return infertilityHistoryState;
	}

	public void setInfertilityHistoryState(Integer infertilityHistoryState) {
		this.infertilityHistoryState = infertilityHistoryState;
	}

	public String getInfertilityHistory() {
		return infertilityHistory;
	}

	public void setInfertilityHistory(String infertilityHistory) {
		this.infertilityHistory = infertilityHistory;
	}

	public Integer getVaginaProduced() {
		return vaginaProduced;
	}

	public void setVaginaProduced(Integer vaginaProduced) {
		this.vaginaProduced = vaginaProduced;
	}

	public Integer getVaginaOperationProduced() {
		return vaginaOperationProduced;
	}

	public void setVaginaOperationProduced(Integer vaginaOperationProduced) {
		this.vaginaOperationProduced = vaginaOperationProduced;
	}

	public Integer getCaesareanSection() {
		return caesareanSection;
	}

	public void setCaesareanSection(Integer caesareanSection) {
		this.caesareanSection = caesareanSection;
	}

	public Integer getObstetricForceps() {
		return obstetricForceps;
	}

	public void setObstetricForceps(Integer obstetricForceps) {
		this.obstetricForceps = obstetricForceps;
	}

	public Integer getEctopicPregnancy() {
		return ectopicPregnancy;
	}

	public void setEctopicPregnancy(Integer ectopicPregnancy) {
		this.ectopicPregnancy = ectopicPregnancy;
	}

	public Integer getOtherDeliver() {
		return otherDeliver;
	}

	public void setOtherDeliver(Integer otherDeliver) {
		this.otherDeliver = otherDeliver;
	}

	public Date getOperationLastTime() {
		return operationLastTime;
	}

	public void setOperationLastTime(Date operationLastTime) {
		this.operationLastTime = operationLastTime;
	}

	public String getOperationIndication() {
		return operationIndication;
	}

	public void setOperationIndication(String operationIndication) {
		this.operationIndication = operationIndication;
	}

	public Integer getComplicationState() {
		return complicationState;
	}

	public void setComplicationState(Integer complicationState) {
		this.complicationState = complicationState;
	}

	public Integer getSpontaneousAbortion() {
		return spontaneousAbortion;
	}

	public void setSpontaneousAbortion(Integer spontaneousAbortion) {
		this.spontaneousAbortion = spontaneousAbortion;
	}

	public Integer getInducedAbortion() {
		return inducedAbortion;
	}

	public void setInducedAbortion(Integer inducedAbortion) {
		this.inducedAbortion = inducedAbortion;
	}

	public Integer getInducedLabour() {
		return inducedLabour;
	}

	public void setInducedLabour(Integer inducedLabour) {
		this.inducedLabour = inducedLabour;
	}

	public Integer getPrematureLabour() {
		return prematureLabour;
	}

	public void setPrematureLabour(Integer prematureLabour) {
		this.prematureLabour = prematureLabour;
	}

	public Integer getStillbirthLabour() {
		return stillbirthLabour;
	}

	public void setStillbirthLabour(Integer stillbirthLabour) {
		this.stillbirthLabour = stillbirthLabour;
	}

	public Integer getEmbryoDamage() {
		return embryoDamage;
	}

	public void setEmbryoDamage(Integer embryoDamage) {
		this.embryoDamage = embryoDamage;
	}

	public Integer getLastAbortionState() {
		return lastAbortionState;
	}

	public void setLastAbortionState(Integer lastAbortionState) {
		this.lastAbortionState = lastAbortionState;
	}

	public String getAbortionType() {
		return abortionType;
	}

	public void setAbortionType(String abortionType) {
		this.abortionType = abortionType;
	}

	public Date getAbortionTime() {
		return abortionTime;
	}

	public void setAbortionTime(Date abortionTime) {
		this.abortionTime = abortionTime;
	}

	public Date getLastPregnancyTime() {
		return lastPregnancyTime;
	}

	public void setLastPregnancyTime(Date lastPregnancyTime) {
		this.lastPregnancyTime = lastPregnancyTime;
	}

	public Integer getPerinatalMortalityState() {
		return perinatalMortalityState;
	}

	public void setPerinatalMortalityState(Integer perinatalMortalityState) {
		this.perinatalMortalityState = perinatalMortalityState;
	}

	public Integer getPerinatalMortality() {
		return perinatalMortality;
	}

	public void setPerinatalMortality(Integer perinatalMortality) {
		this.perinatalMortality = perinatalMortality;
	}

	public Date getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(Date deathTime) {
		this.deathTime = deathTime;
	}

	public String getDeathReason() {
		return deathReason;
	}

	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}

	public String getNowSurviverChildren() {
		return nowSurviverChildren;
	}

	public void setNowSurviverChildren(String nowSurviverChildren) {
		this.nowSurviverChildren = nowSurviverChildren;
	}

	public Integer getAlwaysBirthDefectsState() {
		return alwaysBirthDefectsState;
	}

	public void setAlwaysBirthDefectsState(Integer alwaysBirthDefectsState) {
		this.alwaysBirthDefectsState = alwaysBirthDefectsState;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDefectsDescription() {
		return defectsDescription;
	}

	public void setDefectsDescription(String defectsDescription) {
		this.defectsDescription = defectsDescription;
	}

	public Integer getEnding() {
		return ending;
	}

	public void setEnding(Integer ending) {
		this.ending = ending;
	}

	public Integer getAlwaysPregnancyHistoryState() {
		return alwaysPregnancyHistoryState;
	}

	public void setAlwaysPregnancyHistoryState(Integer alwaysPregnancyHistoryState) {
		this.alwaysPregnancyHistoryState = alwaysPregnancyHistoryState;
	}

	public String getSymptomDescription() {
		return symptomDescription;
	}

	public void setSymptomDescription(String symptomDescription) {
		this.symptomDescription = symptomDescription;
	}

	public String getOtherPergnancyHistory() {
		return otherPergnancyHistory;
	}

	public void setOtherPergnancyHistory(String otherPergnancyHistory) {
		this.otherPergnancyHistory = otherPergnancyHistory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
	}

	public String getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getAllergicType() {
		return allergicType;
	}

	public void setAllergicType(String allergicType) {
		this.allergicType = allergicType;
	}

	public Integer getQuickeningTime() {
		return quickeningTime;
	}

	public void setQuickeningTime(Integer quickeningTime) {
		this.quickeningTime = quickeningTime;
	}

	public Integer getFamilyHistoryState() {
		return familyHistoryState;
	}

	public void setFamilyHistoryState(Integer familyHistoryState) {
		this.familyHistoryState = familyHistoryState;
	}
    
    
    
}
