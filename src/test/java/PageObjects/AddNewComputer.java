package PageObjects;

public class AddNewComputer {
    public static String labelComputerName = "//label[text()='Computer name']";
    public static String labelIntroducedDate = "//label[text()='Introduced date']";
    public static String labelDiscontinuedDate = "//label[text()='Discontinued date']";
    public static String labelCompany = "//label[text()='Company']";

    public static String textboxComputerName = "//input[@id='name']";
    public static String textboxIntroducedDate = "//input[@id='introduced']";
    public static String textboxDiscontinuedDate = "//input[@id='discontinued']";
    public static String dropdwnCompany = "//select[@id='company']";

    public static String btnCreateComputer = "//input[@value='Create this computer']";
    public static String btnCancel = "//a[text()='Cancel']";

    public static String headerText = "//h1[text()='Add a computer']";
    public static String msgErrorComputerName="//label[text()='Computer name']/parent::div[@class='clearfix error']";
    public static String msgErrorIntroducedDate="//label[text()='Introduced date']/parent::div[@class='clearfix error']";
    public static String msgErrorDiscontinuedDate="//label[text()='Discontinued date']/parent::div[@class='clearfix error']";
}
