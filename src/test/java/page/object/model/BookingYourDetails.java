package page.object.model;

import org.openqa.selenium.By;

public class BookingYourDetails implements ISetUpWebElements{

    private By detailsLabel, detailsForm, firstName, lastName, email, finalDetails;

    @Override
    public void setUpWebElements() {
        detailsLabel = By.xpath("//h2[text()='Your booking details']");
        detailsForm = By.cssSelector("div.bui-card__content");
        firstName = By.id("firstname");
        lastName = By.id("lastname");
        email = By.id("email");
        finalDetails = By.name("book");
    }

    public By getDetailsLabel() {
        return detailsLabel;
    }
    
    public By getDetailsForm() {
        return detailsForm;
    }
    
    public By getFirstName() {
        return firstName;
    }
    
    public By getLastName() {
        return lastName;
    }
    
    public By getEmail() {
        return email;
    }
    
    public By getFinalDetails() {
        return finalDetails;
    }    
    
}
