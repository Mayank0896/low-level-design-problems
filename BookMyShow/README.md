# About
In this file, we are solving a LLD problem for an application similar to BOOKMYSHOW in which any user can book one or more tickets for a movie of any show.

## Functional Requirement
1. The system should be able to list down cities where its cinemas are located.
2. Upon selecting the city, the system should display the movies released in that particular city to that user.
3. Once the user makes his choice of the movie, the system should display the cinemas running that movie and its available shows.
4. The user should be able to select the show from a cinema and book their tickets.
5. The system should be able to show the user the seating arrangement of the cinema hall.
6. The user should be able to select multiple seats according to their choice.
7. The user should be able to distinguish between available seats from the booked ones.
8. Users should be able to put a hold on the seats for 5/10 minutes before they make a payment to finalize the booking.
9. The system should serve the tickets First In First Out manner
10. Each theater company can have multiple movie halls and each movie hall can run multiple movie shows.
11. Seat types are DELUX, VIP, ECONOMY.

## Non-Functional Requirements:
1. The system would need to be highly concurrent as there will be multiple booking requests for the same seat at any particular point in time. The design should be such that system handles such ambiguity fairly.
2. Secure and ACID compliant.


## Other Non-Functional Requirements
- Performance: The application should be responsive and handle user requests efficiently.
- Scalability: The system should be able to handle increasing user load and future growth.
- Usability: The interface should be user-friendly and intuitive for patients, doctors, and admins.
- Availability: The application should be available and accessible to users with minimal downtime.