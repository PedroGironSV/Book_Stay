package stepdefinitions;

import java.io.File;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import actions.BrowserActions;
import actions.ReservationType;
import driver.Driver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.object.model.BookingCheckAccomodation;
import page.object.model.BookingHome;
import page.object.model.BookingSearchResults;
import page.object.model.BookingStayDetails;
import page.object.model.BookingYourDetails;

public class Tests extends BrowserActions {

    BookingHome home = new BookingHome();
    BookingSearchResults searchResults = new BookingSearchResults();
    BookingStayDetails stayDetails = new BookingStayDetails();
    BookingYourDetails yourDetails = new BookingYourDetails();
    BookingCheckAccomodation accomodation = new BookingCheckAccomodation();
    Scenario scenario;

    @Before
    public void setUpScenario(Scenario sc) {
        try {
            this.scenario = sc;
            FileUtils.cleanDirectory(new File("Execution_Report/features/"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Given("I navigate to Booking.com home page")
    public void navigateToBookingHomePage() {
        Driver.getDriver();
        home.setUpWebElements();
        fluentWait(home.getGeniusExit());
        click(home.getGeniusExit());
        takeScreenShot(scenario);
    }

    @When("I choose {string} as destination")
    public void chooseDestination(String destination) {
        explicitWait(home.getDestination());
        type(home.getDestination(), destination);
        markElement(home.getDestination());
        takeScreenShot(scenario);
    }

    @Then("I set the period of stay from {string} to {string}")
    public void setThePeriodOfStay(String checkInDate, String checkOutDate) {
        StringBuilder dataDateIn = new StringBuilder();
        StringBuilder dataDateOut = new StringBuilder();
        StringBuilder monthAndYearIn = new StringBuilder();
        StringBuilder monthAndYearOut = new StringBuilder();
        String currentMonthAndYear, dayIn, monthIn, dayOut, monthOut;
        try {
            SimpleDateFormat checkIn, checkOut;
            checkIn = new SimpleDateFormat("dd/MM/yyyy");
            checkOut = new SimpleDateFormat("dd/MM/yyyy");
            Calendar date = Calendar.getInstance();
            date.setTime(checkIn.parse(checkInDate));
            dayIn = date.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + date.get(Calendar.DAY_OF_MONTH)
                    : String.valueOf(date.get(Calendar.DAY_OF_MONTH));
            monthIn = (date.get(Calendar.MONTH) + 1) < 10 ? "0" + (date.get(Calendar.MONTH) + 1)
                    : String.valueOf((date.get(Calendar.MONTH) + 1));
            dataDateIn = dataDateIn.append(date.get(Calendar.YEAR)).append("-").append(monthIn)
                    .append("-").append(dayIn);
            monthAndYearIn = monthAndYearIn.append(new DateFormatSymbols().getMonths()[date.get(Calendar.MONTH)])
                    .append(" ").append(date.get(Calendar.YEAR));

            date.setTime(checkOut.parse(checkOutDate));
            dayOut = date.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + date.get(Calendar.DAY_OF_MONTH)
                    : String.valueOf(date.get(Calendar.DAY_OF_MONTH));
            monthOut = (date.get(Calendar.MONTH) + 1) < 10 ? "0" + (date.get(Calendar.MONTH) + 1)
                    : String.valueOf((date.get(Calendar.MONTH) + 1));
            dataDateOut = dataDateOut.append(date.get(Calendar.YEAR)).append("-").append(monthOut)
                    .append("-").append(dayOut);
            monthAndYearOut = monthAndYearOut.append(new DateFormatSymbols().getMonths()[date.get(Calendar.MONTH)])
                    .append(" ").append(date.get(Calendar.YEAR));

            click(home.getCalendar());
            scrollToElement(home.calendarTable());
            currentMonthAndYear = getTextFromElement(home.getMonthYear());
            while (!monthAndYearIn.toString().equals(currentMonthAndYear)) {
                click(home.getNextCoupleMonths());
                fluentWait(home.getMonthYear());
                currentMonthAndYear = getTextFromElement(home.getMonthYear());
            }
            home.setCheckInDay(dataDateIn.toString());
            fluentWait(home.getCheckInDay());
            click(home.getCheckInDay());

            while (!monthAndYearOut.toString().equals(currentMonthAndYear)) {
                click(home.getNextCoupleMonths());
                fluentWait(home.getMonthYear());
                currentMonthAndYear = getTextFromElement(home.getMonthYear());
            }
            home.setCheckOutDay(dataDateOut.toString());
            fluentWait(home.getCheckOutDay());
            click(home.getCheckOutDay());
            markElement(home.calendarTable());
            takeScreenShot(scenario);
        } catch (ParseException parseException) {
            System.err.println("Parse Exception: " + parseException);
        }
    }

    @Given("I look for {int} rooms and {int} adults")
    public void lookForRoomsAdultsChildren(int rooms, int adults) {
        click(home.getOccupancyConfig());
        fluentWait(home.getAdultsCount());
        int currentAdults = Integer.parseInt(getCurrentCount(home.getAdultsCount(), ReservationType.ADULTS));
        int currentRooms = Integer.parseInt(getCurrentCount(home.getRoomsCount(), ReservationType.ROOMS));
        if (currentAdults < adults) {
            while (currentAdults < adults) {
                increase(home.getIncreaseAdults(), ReservationType.ADULTS);
                currentAdults = Integer.parseInt(getCurrentCount(home.getAdultsCount(), ReservationType.ADULTS));
            }
        } else {
            while (currentAdults > adults) {
                reduce(home.getReduceAdults(), ReservationType.ADULTS);
                currentAdults = Integer.parseInt(getCurrentCount(home.getAdultsCount(), ReservationType.ADULTS));
            }
        }

        if (currentRooms < rooms) {
            while (currentRooms < rooms) {
                increase(home.getIncreaseRooms(), ReservationType.ROOMS);
                currentRooms = Integer.parseInt(getCurrentCount(home.getRoomsCount(), ReservationType.ROOMS));
            }
        } else {
            while (currentRooms > rooms) {
                reduce(home.getReduceRooms(), ReservationType.ROOMS);
                currentRooms = Integer.parseInt(getCurrentCount(home.getRoomsCount(), ReservationType.ROOMS));
            }
        }
        click(home.getDone());
        markElement(home.getOccupancyConfig());
        takeScreenShot(scenario);
        implicitWait();
        click(home.getSearch());
    }

    @When("I scroll to a stay with {string} evaluation")
    public void selectStayWithEvaluation(String evaluation) {
        searchResults.setUpWebElements();
        fluentWait(searchResults.getDestinationLabel());
        searchResults.setEvaluationType(evaluation);
        scrollToElement(searchResults.getEvaluationType());
        markElement(searchResults.getEvaluationType());
        takeScreenShot(scenario);
    }

    @Then("I select the option I want to book with {string} evaluation")
    public void selectOptionToBook(String evaluation) {
        searchResults.setAvailability(evaluation);
        click(searchResults.getAvailability());
        switchToWindow(1);
        stayDetails.setUpWebElements();
        fluentWait(stayDetails.getGallery());
        scrollToElement(stayDetails.getSelectRooms());
        selectOptionDropdown(stayDetails.getSelectRooms(), 1);
        markElement(stayDetails.getSelectRooms());
        markElement(stayDetails.getReserveButton());
        takeScreenShot(scenario);
        click(stayDetails.getReserveButton());
    }

    @Given("I complete the details firts name:{string}, last name:{string} and email:{string}")
    public void completeDetails(String firstName, String lastName, String email) {
        yourDetails.setUpWebElements();
        fluentWait(yourDetails.getDetailsLabel());
        scrollToElement(yourDetails.getDetailsForm());
        type(yourDetails.getFirstName(), firstName);
        type(yourDetails.getLastName(), lastName);
        type(yourDetails.getEmail(), email);
        markElement(yourDetails.getDetailsForm());
        takeScreenShot(scenario);
        click(yourDetails.getFinalDetails());
    }

    @When("I check the details of the selected accommodation")
    public void checkAccommodationDetails() {
        accomodation.setUpWebElements();
        fluentWait(accomodation.getDetailsLabel());
        click(accomodation.getCheckBooking());
        fluentWait(accomodation.getDetailsForm());
        takeScreenShot(scenario);
    }
}
