import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int totalCount = 0;

        BPlusTree tree = new BPlusTree();
        int degree = 0;

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter B+ Tree Degree: ");
        degree = scan.nextInt();

        tree.setDegree(degree);

        String firstFile = "VAERS_COVID_DataAugust2023.csv";

        list = readOldFiles(firstFile, tree, list);

        String secondFileVax = "2023VAERSVAX.csv";
        String secondFileData = "2023VAERSDATA.csv";
        String secondFileSymptom = "2023VAERSSYMPTOMS.csv";

        list = readNewFiles(secondFileVax, secondFileData, secondFileSymptom, tree, list);

        String thirdFileVax = "NonDomesticVAERSVAX.csv";
        String thirdFileData = "NonDomesticVAERSDATA.csv";
        String thirdFileSymptom = "NonDomesticVAERSSYMPTOMS.csv";

        list = readNewFiles(thirdFileVax, thirdFileData, thirdFileSymptom, tree, list);

        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(tree.searchPatient(list.get(i)).getVaersID());
            totalCount++;
        }

        System.out.println("Total Count: " + totalCount);

        scan.close();
    }

    public static ArrayList<Integer> readOldFiles(String file, BPlusTree myTree, ArrayList<Integer> myList)
    {
        int curID = 0;
        String curVaxManu = "";
        String curVaxLot = "";
        String curVaxDoseSeries = "";
        String curVaxRoute = "";
        String curVaxSite = "";
        String curVaxName = "";

        BufferedReader fileReader = null;
        String fileLine = "";

        try
        {
            fileReader = new BufferedReader(new FileReader(file));
        
            fileLine = fileReader.readLine();
            fileLine = fileReader.readLine();

            while(fileLine != null)
            {
                String[] row = fileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if(Integer.parseInt(row[0]) != curID)
                {
                    Patient newPatient = new Patient(Integer.parseInt(row[0]));
                    myList.add(newPatient.getVaersID());

                    myTree.insertPatient(newPatient.getVaersID(), newPatient);

                    Vaccine newVaccine = new Vaccine();
                    myTree.searchPatient(newPatient.getVaersID()).addVaccine(newVaccine);

                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxType(row[35]);
                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxManu(row[36]);
                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxLot(row[37]);
                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(row[38]);
                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(row[39]);
                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxSite(row[40]);
                    myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxName(row[41]);

                    curID = Integer.parseInt(row[0]);
                    curVaxManu = row[36];
                    curVaxLot = row[37];
                    curVaxDoseSeries = row[38];
                    curVaxRoute = row[39];
                    curVaxSite = row[40];
                    curVaxName = row[41];

                    myTree.searchPatient(newPatient.getVaersID()).setDate(row[1]);
                    myTree.searchPatient(newPatient.getVaersID()).setState(row[2]);

                    if(row[3].equals(""))
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setAgeYrs(-1);
                    }

                    else
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setAgeYrs(Double.parseDouble(row[3]));
                    }

                    if(row[4].equals(""))
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setCageYr(-1);
                    }

                    else
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setCageYr(Integer.parseInt(row[4]));
                    }

                    myTree.searchPatient(newPatient.getVaersID()).setCageMo(row[5]);
                    myTree.searchPatient(newPatient.getVaersID()).setSex(row[6]);
                    myTree.searchPatient(newPatient.getVaersID()).setRptDate(row[7]);
                    myTree.searchPatient(newPatient.getVaersID()).setSymptomText(row[8]);
                    myTree.searchPatient(newPatient.getVaersID()).setDied(row[9]);
                    myTree.searchPatient(newPatient.getVaersID()).setDateDied(row[10]);
                    myTree.searchPatient(newPatient.getVaersID()).setLThreat(row[11]);
                    myTree.searchPatient(newPatient.getVaersID()).setErVisit(row[12]);
                    myTree.searchPatient(newPatient.getVaersID()).setHospital(row[13]);
                    myTree.searchPatient(newPatient.getVaersID()).setHospdays(row[14]);
                    myTree.searchPatient(newPatient.getVaersID()).setXStay(row[15]);
                    myTree.searchPatient(newPatient.getVaersID()).setDisable(row[16]);
                    myTree.searchPatient(newPatient.getVaersID()).setRecovd(row[17]);
                    myTree.searchPatient(newPatient.getVaersID()).setVaxDate(row[18]);
                    myTree.searchPatient(newPatient.getVaersID()).setDate(row[19]);
                    myTree.searchPatient(newPatient.getVaersID()).setOnsetDate(row[20]);
                    myTree.searchPatient(newPatient.getVaersID()).setNumDays(row[21]);
                    myTree.searchPatient(newPatient.getVaersID()).setLabData(row[22]);
                    myTree.searchPatient(newPatient.getVaersID()).setVAdminBy(row[23]);
                    myTree.searchPatient(newPatient.getVaersID()).setVFundBy(row[24]);
                    myTree.searchPatient(newPatient.getVaersID()).setOtherMeds(row[25]);
                    myTree.searchPatient(newPatient.getVaersID()).setCurIll(row[26]);
                    myTree.searchPatient(newPatient.getVaersID()).setHistory(row[27]);
                    myTree.searchPatient(newPatient.getVaersID()).setSpltType(row[28]);
                    myTree.searchPatient(newPatient.getVaersID()).setFormVers(row[29]);

                    if(row.length == 30)
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setTodaysDate("");
                        myTree.searchPatient(newPatient.getVaersID()).setBirthDefect("");
                        myTree.searchPatient(newPatient.getVaersID()).setOfcVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setErEDVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setAllergies("");
                    }

                    else if(row.length == 31)
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setTodaysDate(row[30]);
                        myTree.searchPatient(newPatient.getVaersID()).setBirthDefect("");
                        myTree.searchPatient(newPatient.getVaersID()).setOfcVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setErEDVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setAllergies("");
                    }

                    else if(row.length == 32)
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setTodaysDate(row[30]);
                        myTree.searchPatient(newPatient.getVaersID()).setBirthDefect(row[31]);
                        myTree.searchPatient(newPatient.getVaersID()).setOfcVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setErEDVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setAllergies("");
                    }

                    else if(row.length == 33)
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setTodaysDate(row[30]);
                        myTree.searchPatient(newPatient.getVaersID()).setBirthDefect(row[31]);
                        myTree.searchPatient(newPatient.getVaersID()).setOfcVisit(row[32]);
                        myTree.searchPatient(newPatient.getVaersID()).setErEDVisit("");
                        myTree.searchPatient(newPatient.getVaersID()).setAllergies("");
                    }

                    else if(row.length == 34)
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setTodaysDate(row[30]);
                        myTree.searchPatient(newPatient.getVaersID()).setBirthDefect(row[31]);
                        myTree.searchPatient(newPatient.getVaersID()).setOfcVisit(row[32]);
                        myTree.searchPatient(newPatient.getVaersID()).setErEDVisit(row[33]);
                        myTree.searchPatient(newPatient.getVaersID()).setAllergies("");
                    }

                    else
                    {
                        myTree.searchPatient(newPatient.getVaersID()).setTodaysDate(row[30]);
                        myTree.searchPatient(newPatient.getVaersID()).setBirthDefect(row[31]);
                        myTree.searchPatient(newPatient.getVaersID()).setOfcVisit(row[32]);
                        myTree.searchPatient(newPatient.getVaersID()).setErEDVisit(row[33]);
                        myTree.searchPatient(newPatient.getVaersID()).setAllergies(row[34]);
                    }
                }

                else
                {
                    if(!(myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxManu().equals(curVaxManu)) || !(myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxLot().equals(curVaxLot)) || !(myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxDoseSeries().equals(curVaxDoseSeries)) || !(myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxRoute().equals(curVaxRoute)) || !(myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxSite().equals(curVaxSite)) || !(myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxName().equals(curVaxName)))
                    {
                        Vaccine newVaccine = new Vaccine();
                        myTree.searchPatient(Integer.parseInt(row[0])).addVaccine(newVaccine);

                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxType(row[35]);
                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxManu(row[36]);
                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxLot(row[37]);
                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxDoseSeries(row[38]);
                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxRoute(row[39]);
                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxSite(row[40]);
                        myTree.searchPatient(Integer.parseInt(row[0])).getVaccine(myTree.searchPatient(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxName(row[41]);
                    }
                }

                for(int i = 42; i < row.length; i = i + 2)
                {
                    myTree.searchPatient(Integer.parseInt(row[0])).addSymptom(row[i], row[i + 1]);
                }
                
                fileLine = fileReader.readLine();
            }

            fileReader.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return myList;
    }

    public static ArrayList<Integer> readNewFiles(String vaxFile, String dataFile, String symptomFile, BPlusTree myTree, ArrayList<Integer> myList)
    {
        BufferedReader vaxFileReader = null;
        String vaxFileLine = "";

        BufferedReader dataFileReader = null;
        String dataFileLine = "";

        BufferedReader symptomFileReader = null;
        String symptomFileLine = "";

        try
        {
            vaxFileReader = new BufferedReader(new FileReader(vaxFile));
            dataFileReader = new BufferedReader(new FileReader(dataFile));
            symptomFileReader = new BufferedReader(new FileReader(symptomFile));

            vaxFileLine = vaxFileReader.readLine();
            vaxFileLine = vaxFileReader.readLine();

            while(vaxFileLine != null)
            {
                String[] vaxRow = vaxFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if(vaxRow[1].contains("COVID19"))
                {
                    if(myList.contains(Integer.parseInt(vaxRow[0])))
                    {
                        Patient curPatient = myTree.searchPatient(Integer.parseInt(vaxRow[0]));

                        Vaccine newVaccine = new Vaccine();
                        myTree.searchPatient(curPatient.getVaersID()).addVaccine(newVaccine);

                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxType(vaxRow[1]);
                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxManu(vaxRow[2]);
                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxLot(vaxRow[3]);
                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(vaxRow[4]);
                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(vaxRow[5]);
                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxSite(vaxRow[6]);
                        myTree.searchPatient(curPatient.getVaersID()).getVaccine(myTree.searchPatient(curPatient.getVaersID()).getVaxCount() - 1).setVaxName(vaxRow[7]);
                    }

                    else
                    {
                        Patient newPatient = new Patient(Integer.parseInt(vaxRow[0]));
                        myTree.insertPatient(newPatient.getVaersID(), newPatient);
                        myList.add(newPatient.getVaersID());
                        
                        Vaccine newVaccine = new Vaccine();
                        myTree.searchPatient(newPatient.getVaersID()).addVaccine(newVaccine);

                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxType(vaxRow[1]);
                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxManu(vaxRow[2]);
                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxLot(vaxRow[3]);
                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(vaxRow[4]);
                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(vaxRow[5]);
                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxSite(vaxRow[6]);
                        myTree.searchPatient(newPatient.getVaersID()).getVaccine(myTree.searchPatient(newPatient.getVaersID()).getVaxCount() - 1).setVaxName(vaxRow[7]);
                    }
                }

                vaxFileLine = vaxFileReader.readLine();
            }

            dataFileLine = dataFileReader.readLine();
            dataFileLine = dataFileReader.readLine();

            while(dataFileLine != null)
            {
                String[] dataRow = dataFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        
                if(myList.contains(Integer.parseInt(dataRow[0])))
                {
                    int curKey = Integer.parseInt(dataRow[0]);

                    myTree.searchPatient(curKey).setDate(dataRow[1]);
                    myTree.searchPatient(curKey).setState(dataRow[2]);

                    if(dataRow[3].equals(""))
                    {
                        myTree.searchPatient(curKey).setAgeYrs(-1);
                    }

                    else
                    {
                        myTree.searchPatient(curKey).setAgeYrs(Double.parseDouble(dataRow[3]));
                    }

                    if(dataRow[4].equals(""))
                    {
                        myTree.searchPatient(curKey).setCageYr(-1);
                    }

                    else
                    {
                        myTree.searchPatient(curKey).setCageYr(Integer.parseInt(dataRow[4]));
                    }

                    myTree.searchPatient(curKey).setCageMo(dataRow[5]);
                    myTree.searchPatient(curKey).setSex(dataRow[6]);
                    myTree.searchPatient(curKey).setRptDate(dataRow[7]);
                    myTree.searchPatient(curKey).setSymptomText(dataRow[8]);
                    myTree.searchPatient(curKey).setDied(dataRow[9]);
                    myTree.searchPatient(curKey).setDateDied(dataRow[10]);
                    myTree.searchPatient(curKey).setLThreat(dataRow[11]);
                    myTree.searchPatient(curKey).setErVisit(dataRow[12]);
                    myTree.searchPatient(curKey).setHospital(dataRow[13]);
                    myTree.searchPatient(curKey).setHospdays(dataRow[14]);
                    myTree.searchPatient(curKey).setXStay(dataRow[15]);
                    myTree.searchPatient(curKey).setDisable(dataRow[16]);
                    myTree.searchPatient(curKey).setRecovd(dataRow[17]);
                    myTree.searchPatient(curKey).setVaxDate(dataRow[18]);
                    myTree.searchPatient(curKey).setDate(dataRow[19]);
                    myTree.searchPatient(curKey).setOnsetDate(dataRow[20]);
                    myTree.searchPatient(curKey).setNumDays(dataRow[21]);
                    myTree.searchPatient(curKey).setLabData(dataRow[22]);
                    myTree.searchPatient(curKey).setVAdminBy(dataRow[23]);
                    myTree.searchPatient(curKey).setVFundBy(dataRow[24]);
                    myTree.searchPatient(curKey).setOtherMeds(dataRow[25]);
                    myTree.searchPatient(curKey).setCurIll(dataRow[26]);
                    myTree.searchPatient(curKey).setHistory(dataRow[27]);
                    myTree.searchPatient(curKey).setSpltType(dataRow[28]);
                    myTree.searchPatient(curKey).setFormVers(dataRow[29]);

                    if(dataRow.length == 30)
                    {
                        myTree.searchPatient(curKey).setTodaysDate("");
                        myTree.searchPatient(curKey).setBirthDefect("");
                        myTree.searchPatient(curKey).setOfcVisit("");
                        myTree.searchPatient(curKey).setErEDVisit("");
                        myTree.searchPatient(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 31)
                    {
                        myTree.searchPatient(curKey).setTodaysDate(dataRow[30]);
                        myTree.searchPatient(curKey).setBirthDefect("");
                        myTree.searchPatient(curKey).setOfcVisit("");
                        myTree.searchPatient(curKey).setErEDVisit("");
                        myTree.searchPatient(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 32)
                    {
                        myTree.searchPatient(curKey).setTodaysDate(dataRow[30]);
                        myTree.searchPatient(curKey).setBirthDefect(dataRow[31]);
                        myTree.searchPatient(curKey).setOfcVisit("");
                        myTree.searchPatient(curKey).setErEDVisit("");
                        myTree.searchPatient(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 33)
                    {
                        myTree.searchPatient(curKey).setTodaysDate(dataRow[30]);
                        myTree.searchPatient(curKey).setBirthDefect(dataRow[31]);
                        myTree.searchPatient(curKey).setOfcVisit(dataRow[32]);
                        myTree.searchPatient(curKey).setErEDVisit("");
                        myTree.searchPatient(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 34)
                    {
                        myTree.searchPatient(curKey).setTodaysDate(dataRow[30]);
                        myTree.searchPatient(curKey).setBirthDefect(dataRow[31]);
                        myTree.searchPatient(curKey).setOfcVisit(dataRow[32]);
                        myTree.searchPatient(curKey).setErEDVisit(dataRow[33]);
                        myTree.searchPatient(curKey).setAllergies("");
                    }

                    else
                    {
                        myTree.searchPatient(curKey).setTodaysDate(dataRow[30]);
                        myTree.searchPatient(curKey).setBirthDefect(dataRow[31]);
                        myTree.searchPatient(curKey).setOfcVisit(dataRow[32]);
                        myTree.searchPatient(curKey).setErEDVisit(dataRow[33]);
                        myTree.searchPatient(curKey).setAllergies(dataRow[34]);
                    }
                }

                dataFileLine = dataFileReader.readLine();
            }

            symptomFileLine = symptomFileReader.readLine();
            symptomFileLine = symptomFileReader.readLine();

            while(symptomFileLine != null)
            {
                String[] symptomRow = symptomFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        
                if(myList.contains(Integer.parseInt(symptomRow[0])))
                {
                    for(int i = 1; i < symptomRow.length - 1; i = i + 2)
                    {
                        myTree.searchPatient(Integer.parseInt(symptomRow[0])).addSymptom(symptomRow[i], symptomRow[i + 1]);
                    }
                }

                symptomFileLine = symptomFileReader.readLine();
            }

            vaxFileReader.close();
            dataFileReader.close();
            symptomFileReader.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return myList;
    }
}
