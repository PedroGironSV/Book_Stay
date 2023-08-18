package page.object.model;

import org.openqa.selenium.By;

public class BookingStayDetails implements ISetUpWebElements{

    private By gallery, selectRooms, reserveButton;

    @Override
    public void setUpWebElements() {
        gallery = By.cssSelector("div.gallery-side-reviews-wrapper.js-no-close");
        selectRooms = By.cssSelector("select.hprt-nos-select.js-hprt-nos-select");
        reserveButton = By.cssSelector("button.txp-bui-main-pp.bui-button.bui-button--primary.hp_rt_input.px--fw-cta.js-reservation-button");
    }

    public By getGallery() {
        return gallery;
    }
    
    public By getSelectRooms() {
        return selectRooms;
    }
    
    public By getReserveButton() {
        return reserveButton;
    }
    
    
}
