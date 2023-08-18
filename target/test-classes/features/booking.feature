Feature: Book a stay

    I want to automate the End to End process to book a stay on Booking.com page

    Scenario Outline: Book a stay on "<destination>" from "<checkInDate>" to "<checkOutDate>"
        Given I navigate to Booking.com home page
        When I choose "<destination>" as destination
        Then I set the period of stay from "<checkInDate>" to "<checkOutDate>"

        Given I look for <rooms> rooms and <adults> adults
        When I scroll to a stay with "<evaluation>" evaluation
        Then I select the option I want to book with "<evaluation>" evaluation

        Given I complete the details firts name:"<firstName>", last name:"<lastName>" and email:"<email>"
        When I check the details of the selected accommodation

        Examples:
            | firstName | lastName | destination | evaluation | checkInDate | checkOutDate | rooms | adults | email              |
            | Pedro     | Giron    | Las Vegas   | Good       | 28/08/2023  | 07/09/2023   | 3     | 6      | testmail@gmail.com |