package PageObjects;

public class HomePage {
    public static String headerLink = "//a[normalize-space()='Play sample application — Computer database']";
    public static String buttonAddNewComputer = "//a[text()='Add a new computer']";
    public static String buttonFilterByName = "//input[@id='searchsubmit']";
    public static String buttonNext = "//a[contains(text(),'Next')]";
    public static String buttonPrevious = "//a[contains(text(),'Previous')]";
    public static String textboxSearch = "//input[@id='searchbox']";
    public static String displayText = "//li[@class='current']";
    public static String tableHeaderCompany = "//thead/tr/th/a[text()='Company']";
    public static String tableHeaderComputerName = "//thead/tr/th/a[text()='Computer name']";
    public static String tableHeaderIntroduced = "//thead/tr/th/a[text()='Introduced']";
    public static String tableHeaderDiscontinued = "//thead/tr/th/a[text()='Discontinued']";
    public static String headerText = "//h1[contains(text(),'computers found')]";

}
