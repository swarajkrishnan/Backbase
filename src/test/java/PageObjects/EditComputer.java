package PageObjects;

public class EditComputer {

    public static String labelCompanyName = "//label[text()='Computer name']";
    public static String labelIntroducedDate = "//label[text()='Introduced date']";
    public static String labelDiscontinuedDate = "//label[text()='Discontinued date']";
    public static String labelCompany = "//label[text()='Company']";

    public static String textboxCompanyName = "//input[@id='name']";
    public static String textboxIntroducedDate = "//input[@id='introduced']";
    public static String textboxDiscontinuedDate = "//input[@id='discontinued']";
    public static String dropdwnCompany = "//select[@id='company']";

    public static String btnCreateComputer = "//input[@value='Save this computer']";
    public static String btnDeleteComputer = "//input[@value='Delete this computer']";
    public static String btnCancel = "//a[text()='Cancel']";

    public static String headerText = "//h1[text()='Edit computer']";
}
