public class Symptom 
{
    private String symptom; 
    private String symptomVersion;

    public Symptom()
    {
        this.symptom = "";
        this.symptomVersion = "";
    }

    public Symptom(String symptom, String symptomVersion)
    {
        this.symptom = symptom;
        this.symptomVersion = symptomVersion;
    }

    public String getSymptom()
    {
        return this.symptom;
    }

    public void setSymptom(String symptom)
    {
        this.symptom = symptom;
    }

    public String getSymptomVersion()
    {
        return this.symptomVersion;
    }

    public void setSymptomVersion(String symptomVersion)
    {
        this.symptomVersion = symptomVersion;
    }
}