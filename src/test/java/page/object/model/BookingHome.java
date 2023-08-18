package page.object.model;

import org.openqa.selenium.By;

public class BookingHome implements ISetUpWebElements{
    
    private By geniusExit, credentialPicker, destination, monthYear, checkInDay, checkOutDay, calendar, nextCoupleMonths, calendarTable, search;
    private By occupancyConfig, done, adultsCount, roomsCount;
    private By reduceAdults, increaseAdults, reduceRooms, increaseRooms;

    @Override
    public void setUpWebElements() {
        credentialPicker = By.id("credential_picker_container");
        geniusExit = By.cssSelector("button.a83ed08757.c21c56c305.f38b6daa18.d691166b09.ab98298258.deab83296e.f4552b6561");
        destination = By.name("ss");
        monthYear = By.cssSelector("h3.e1eebb6a1e.ee7ec6b631");
        calendar = By.cssSelector("button.d47738b911.e246f833f7.fe211c0731");
        nextCoupleMonths = By.cssSelector("button.a83ed08757.c21c56c305.f38b6daa18.d691166b09.f671049264.deab83296e.f4552b6561.dc72a8413c.f073249358");
        calendarTable = By.cssSelector("div.b91c144835");
        occupancyConfig = By.cssSelector("button.d47738b911.b7d08821c3");

        adultsCount = By.cssSelector("div:nth-child(1) > div.bfb38641b0 > span");
        roomsCount = By.cssSelector("div:nth-child(3) > div.bfb38641b0 > span");

        reduceAdults = By.cssSelector("div:nth-child(1) > div.bfb38641b0 > button.a83ed08757.c21c56c305.f38b6daa18.d691166b09.ab98298258.deab83296e.bb803d8689.e91c91fa93");
        increaseAdults = By.cssSelector("div:nth-child(1) > div.bfb38641b0 > button.a83ed08757.c21c56c305.f38b6daa18.d691166b09.ab98298258.deab83296e.bb803d8689.f4d78af12a");
        reduceRooms = By.cssSelector("div:nth-child(3) > div.bfb38641b0 > button.a83ed08757.c21c56c305.f38b6daa18.d691166b09.ab98298258.deab83296e.bb803d8689.e91c91fa93");
        increaseRooms = By.cssSelector("div:nth-child(3) > div.bfb38641b0 > button.a83ed08757.c21c56c305.f38b6daa18.d691166b09.ab98298258.deab83296e.bb803d8689.f4d78af12a");
        done = By.cssSelector("button.a83ed08757.c21c56c305.bf0537ecb5.ab98298258.d2529514af.af7297d90d.d285d0ebe9");

        search = By.cssSelector("button.a83ed08757.c21c56c305.a4c1805887.f671049264.d2529514af.c082d89982.aa11d0d5cd");
    }

    public By getGeniusExit() {
        return geniusExit;
    }

    public By getCredentialPicker(){
        return credentialPicker;
    }

    public By getDestination() {
        return destination;
    }

    public By getMonthYear() {
        return monthYear;
    }

    public void setCheckInDay(String dataDateIn){
        checkInDay = By.xpath("//span[@data-date='"+ dataDateIn +"']");
    }

    public By getCheckInDay() {
        return checkInDay;
    }

    public void setCheckOutDay(String dataDateOut){
        checkOutDay = By.xpath("//span[@data-date='"+ dataDateOut +"']");
    }

    public By getCheckOutDay() {
        return checkOutDay;
    }

    public By getCalendar(){
        return calendar;
    }

    public By getNextCoupleMonths(){
        return nextCoupleMonths;
    }

    public By calendarTable(){
        return calendarTable;
    }

    public By getOccupancyConfig() {
        return occupancyConfig;
    }

    public By getAdultsCount() {
        return adultsCount;
    }

    public By getRoomsCount() {
        return roomsCount;
    }
    
    public By getReduceAdults() {
        return reduceAdults;
    }

    public By getIncreaseAdults() {
        return increaseAdults;
    }

    public By getReduceRooms() {
        return reduceRooms;
    }

    public By getIncreaseRooms() {
        return increaseRooms;
    }
    
    public By getDone() {
        return done;
    }

    public By getSearch(){
        return search;
    }
    
}
