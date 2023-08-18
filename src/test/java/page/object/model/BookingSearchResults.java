package page.object.model;

import org.openqa.selenium.By;

public class BookingSearchResults implements ISetUpWebElements{

    private By destinationLabel, evaluationType, availability;

    @Override
    public void setUpWebElements() {
        destinationLabel = By.xpath("//h1[contains(text(), 'properties')]");
    }

    public void setEvaluationType(String evaluation){
        StringBuilder evaluationXpath = new StringBuilder();
        evaluationXpath = evaluationXpath.append("//div[text()='").append(evaluation).append("']");
        evaluationType = By.xpath(evaluationXpath.toString());
    }

    public void setAvailability(String evaluation){
        StringBuilder availabilityXpath = new StringBuilder();
        availabilityXpath = availabilityXpath.append("//div[text()='").append(evaluation).append("']/ancestor::a");
        availability = By.xpath(availabilityXpath.toString());
    }

    public By getEvaluationType() {
        return evaluationType;
    }

    public By getAvailability() {
        return availability;
    }

    public By getDestinationLabel(){
        return destinationLabel;
    }
}
