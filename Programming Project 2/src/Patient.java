import java.util.ArrayList;

public class Patient 
{
    private int vaersID;
    private String date;
    private String state;
    private double ageYrs;
    private int cageYr;
    private String cageMo;
    private String sex;
    private String rptDate;
    private String symptomText;
    private String died;
    private String dateDied;
    private String lThreat;
    private String erVisit;
    private String hospital;
    private String hospdays;
    private String xStay;
    private String disable;
    private String recovd;
    private String vaxDate;
    private String onsetDate;
    private String numDays;
    private String labData;
    private String vAdminBy;
    private String vFundBy;
    private String otherMeds;
    private String curIll;
    private String history;
    private String priorVax;
    private String spltType;
    private String formVers;
    private String todaysDate;
    private String birthDefect;
    private String ofcVisit;
    private String erEDVisit;
    private String allergies;
    private ArrayList<Vaccine> vaccines;
    private int vaxCount;
    private ArrayList<Symptom> symptoms;

    public Patient(int vaersID)
    {
        this.vaersID = vaersID;
        this.vaccines = new ArrayList<Vaccine>();
        this.vaxCount = 0;
        this.symptoms = new ArrayList<Symptom>();
    }

    public int getVaersID()
    {
        return this.vaersID;
    }

    public void setVaersID(int vaersID)
    {
        this.vaersID = vaersID;
    }

    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public double getAgeYrs()
    {
        return this.ageYrs;
    }

    public void setAgeYrs(double ageYrs)
    {
        this.ageYrs = ageYrs;
    }

    public int getCageYr()
    {
        return this.cageYr;
    }

    public void setCageYr(int cageYr)
    {
        this.cageYr = cageYr;
    }

    public String getCageMo()
    {
        return this.cageMo;
    }

    public void setCageMo(String cageMo)
    {
        this.cageMo = cageMo;
    }

    public String getSex()
    {
        return this.sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getRptDate()
    {
        return this.rptDate;
    }

    public void setRptDate(String rptDate)
    {
        this.rptDate = rptDate;
    }

    public String getSymptomText()
    {
        return this.symptomText;
    }

    public void setSymptomText(String symptomText)
    {
        this.symptomText = symptomText;
    }

    public String getDied()
    {
        return this.died;
    }

    public void setDied(String died)
    {
        this.died = died;
    }

    public String getDateDied()
    {
        return this.dateDied;
    }

    public void setDateDied(String dateDied)
    {
        this.dateDied = dateDied;
    }

    public String getLThreat()
    {
        return this.lThreat;
    }

    public void setLThreat(String lThreat)
    {
        this.lThreat = lThreat;
    }

    public String getErVisit()
    {
        return this.erVisit;
    }

    public void setErVisit(String erVisit)
    {
        this.erVisit = erVisit;
    }

    public String getHospital()
    {
        return this.hospital;
    }

    public void setHospital(String hospital)
    {
        this.hospital = hospital;
    }

    public String getHospdays()
    {
        return this.hospdays;
    }

    public void setHospdays(String hospdays)
    {
        this.hospdays = hospdays;
    }

    public String getXStay()
    {
        return this.xStay;
    }

    public void setXStay(String xStay)
    {
        this.xStay = xStay;
    }

    public String getDisable()
    {
        return this.disable;
    }

    public void setDisable(String disable)
    {
        this.disable = disable;
    }

    public String getRecovd()
    {
        return this.recovd;
    }

    public void setRecovd(String recovd)
    {
        this.recovd = recovd;
    }

    public String getVaxDate()
    {
        return this.vaxDate;
    }

    public void setVaxDate(String vaxDate)
    {
        this.vaxDate = vaxDate;
    }

    public String getOnsetDate()
    {
        return this.onsetDate;
    }

    public void setOnsetDate(String onsetDate)
    {
        this.onsetDate = onsetDate;
    }

    public String getNumDays()
    {
        return this.numDays;
    }

    public void setNumDays(String numDays)
    {
        this.numDays = numDays;
    }

    public String getLabData()
    {
        return this.labData;
    }

    public void setLabData(String labData)
    {
        this.labData = labData;
    }

    public String getVAdminBy()
    {
        return this.vAdminBy;
    }

    public void setVAdminBy(String vAdminBy)
    {
        this.vAdminBy = vAdminBy;
    }

    public String getVFundBy()
    {
        return this.vFundBy;
    }

    public void setVFundBy(String vFundBy)
    {
        this.vFundBy = vFundBy;
    }

    public String getOtherMeds()
    {
        return this.otherMeds;
    }

    public void setOtherMeds(String otherMeds)
    {
        this.otherMeds = otherMeds;
    }

    public String getCurIll()
    {
        return this.curIll;
    }

    public void setCurIll(String curIll)
    {
        this.curIll = curIll;
    }

    public String getHistory()
    {
        return this.history;
    }

    public void setHistory(String history)
    {
        this.history = history;
    }

    public String getPriorVax()
    {
        return this.priorVax;
    }

    public void setPriorVax(String priorVax)
    {
        this.priorVax = priorVax;
    }

    public String getSpltType()
    {
        return this.spltType;
    }

    public void setSpltType(String spltType)
    {
        this.spltType = spltType;
    }

    public String getFormVers()
    {
        return this.formVers;
    }

    public void setFormVers(String formVers)
    {
        this.formVers = formVers;
    }

    public String getTodaysDate()
    {
        return this.todaysDate;
    }

    public void setTodaysDate(String todaysDate)
    {
        this.todaysDate = todaysDate;
    }

    public String getBirthDefect()
    {
        return this.birthDefect;
    }

    public void setBirthDefect(String birthDefect)
    {
        this.birthDefect = birthDefect;
    }

    public String getOfcVisit()
    {
        return this.ofcVisit;
    }

    public void setOfcVisit(String ofcVisit)
    {
        this.ofcVisit = ofcVisit;
    }

    public String getErEDVisit()
    {
        return this.erEDVisit;
    }

    public void setErEDVisit(String erEDVisit)
    {
        this.erEDVisit = erEDVisit;
    }

    public String getAllergies()
    {
        return this.allergies;
    }

    public void setAllergies(String allergies)
    {
        this.allergies = allergies;
    }

    public ArrayList<Vaccine> getVaccines()
    {
        return this.vaccines;
    }

    public Vaccine getVaccine(int index)
    {
        return this.vaccines.get(index);
    }

    public void addVaccine(Vaccine newVaccine)
    {
        this.vaccines.add(newVaccine);
        this.vaxCount++;
    }

    public int getVaxCount()
    {
        return this.vaxCount;
    }

    public ArrayList<Symptom> getSymptoms()
    {
        return this.symptoms;
    }

    public Symptom getSymptom(int index)
    {
        return this.symptoms.get(index);
    }

    public void addSymptom(String symptom, String symptomVersion)
    {
        Symptom newSymptom = new Symptom(symptom, symptomVersion);

        symptoms.add(newSymptom);
    }
}