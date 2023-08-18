package page.object.model;

import org.openqa.selenium.By;

public class BookingCheckAccomodation implements ISetUpWebElements{

    private By detailsLabel, checkBooking, detailsForm;

    @Override
    public void setUpWebElements() {
        detailsLabel = By.xpath("//h2[text()='Your booking details']");
        checkBooking = By.name("review");
        detailsForm = By.name("book");
    }

    public By getDetailsLabel() {
        return detailsLabel;
    }

    public By getCheckBooking(){
        return checkBooking;
    }

    public By getDetailsForm(){
        return detailsForm;
    }
    
}
