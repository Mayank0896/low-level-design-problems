package DoctorAppointmentBooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Application {

    public static void main(String[] args) {
        System.out.println("This is the Doctor Appointment Application");
    }
}

// Model Layer Starts -------------

enum Gender {
    MALE, FEMALE, OTHER;
}

enum SlotStatus {
    AVAILABLE, BOOKED;
}

class Doctor {
    private long doctorId;
    private String name;
    private int age;
    private int totalExperience;
    private Gender gender;
    private String speciality;
    private List<Slot> allSlots;
    // Constructors, Getters, Setters
    public String getName() {
        return name;
    }
    public String getSpeciality() {
        return speciality;
    }
    
}

class Slot {
    private long slotId;
    private Date date;
    private SlotStatus status;
    private Doctor doctor;
    // Constructors, Getters, Setters
    public Doctor getDoctor() {
        return doctor;
    }
    public Date getDate() {
        return date;
    }
    
    
}

class Patient {
    private long patientId;
    private String name;
    private String age;
    private Gender gender;
    // Constructors, Getters, Setters
}

class AppointmentToken {
    public AppointmentToken(int randomNumber, Slot slot2) {
        this.tokenId = randomNumber;
        this.slot = slot2;
    }
    private long tokenId;
    // private Patient patient;
    // private Doctor doctor;
    private Slot slot;
    // Constructors, Getters, Setters
    
    public long getTokenId() {
        return tokenId;
    }
    public AppointmentToken(long tokenId, Slot slot) {
        this.tokenId = tokenId;
        this.slot = slot;
    }
    public AppointmentToken() {
    }
    public Slot getSlot() {
        return slot;
    }
    
}


// Model Layer Ends -----------------

// DTO Layer Starts --------------

class AppointmentTokenDTO {
    private long tokenId;
    private String doctorName;
    private String speciality;
    // private String patientName;
    // private int patientAge;
    private Date appointmentTime;
    // Constructors, Getters and Setters
    
    public AppointmentTokenDTO(long tokenId, String doctorName, String speciality, Date appointmentTime) {
        this.tokenId = tokenId;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.appointmentTime = appointmentTime;
    }

    public AppointmentTokenDTO() {
    }
    
}

// DTO Layer Ends ---------------

// Repository Layer Start ---------

interface DoctorRepository {
    List<Doctor> getDoctorsBySpeciality(String speciality);

    List<Slot> getDoctorSlotsById(long id);
}

interface SlotRepository {
    Slot getSlot(long id);

    void bookSlotById(long slotId);
}

// Repository Layer Ends ----------

// Service Layer Starts ----------

class DoctorService {
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctorsBySpeciality(String speciality) {
        // Here we will use doctorRepository to fetch All doctors by speciality and will
        // return list
        return new ArrayList<>();
    }

    public List<Slot> getSlotsForDoctor(long doctorId) {
        // Repository is returning slots which are in AVAILABLE
        List<Slot> slots = this.doctorRepository.getDoctorSlotsById(doctorId);
        return slots;
    }
}

class SlotService {
    private SlotRepository slotRepository;
    public Slot getSlot(long id) {
        return this.slotRepository.getSlot(id);
    }
    public void bookSlot(long slotId) {
        slotRepository.bookSlotById(slotId);
        return;
    }
}

class BookingService {
    private AppointmentTokenService appointmentTokenService;
    private SlotService slotService;

    public AppointmentTokenDTO booking(long slotId) {
        Slot slot = this.slotService.getSlot(slotId);
        // Change status of Slot to BOOKED
        this.slotService.bookSlot(slotId);
        AppointmentToken token = this.appointmentTokenService.createToken(slot);
        Doctor doctor = slot.getDoctor();
        return this.appointmentTokenService.createTokenDTO(token, slot, doctor);
    }

}

class AppointmentTokenService {

    public AppointmentToken createToken(Slot slot) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        return new AppointmentToken(randomNumber, slot);
    }

    public AppointmentTokenDTO createTokenDTO(AppointmentToken token, Slot slot, Doctor doctor) {
        return new AppointmentTokenDTO(token.getTokenId(), doctor.getName(), doctor.getSpeciality(), slot.getDate());
    }

}

// Service Layer Ends ------------

// Controller Layer Starts -------------

class Controller {
    private DoctorService doctorService;
    private SlotService slotService;
    private BookingService bookingService;
    // Get endpoint: return list of doctors based on matching speciality.
    public List<Doctor> getDoctors(String speciality) {
        return doctorService.getDoctorsBySpeciality(speciality);
    }

    // Get endpoint: return list of available slots for doctor id
    public List<Slot> getSlotsForDoctor(long doctorId) {
        return doctorService.getSlotsForDoctor(doctorId);
    }

    // Get endpoint: booking slot and returning token on matching slot id
    public AppointmentTokenDTO bookAppointment(long slotId) {
        // Booking appointment for the slot and return appointment token
        return this.bookingService.booking(slotId);
    }
}

// Controller Layer Ends --------------

/*
Chatgpt Review - 
 * I'd rate your code a **7 out of 10**. It's a good start with a solid foundation for a doctor appointment booking application. Here's a breakdown of the strengths and areas for improvement:

**Strengths:**

- **Clear Separation of Concerns:** You've separated the application into distinct layers (Model, DTO, Repository, Service, and Controller). This promotes maintainability and reusability.
- **Use of Interfaces:** Defining interfaces for repositories and services promotes loose coupling and easier testing.
- **Basic Functionalities:** The code covers core functionalities like searching doctors by specialty, viewing slots, and booking appointments.
- **AppointmentTokenDTO:**  Creating a DTO for appointment tokens improves data presentation for the UI.

**Areas for Improvement:**

- **Error Handling:**  Currently, the code doesn't handle potential errors like slot unavailability or booking failures. Consider adding exception handling and appropriate responses.
- **Security:** This is a basic implementation and doesn't consider user authentication or authorization.  In a real application, you'd need mechanisms to secure user data and control access.
- **Data Persistence:** The current implementation doesn't show how data is persisted (e.g., in a database). You'd need to implement actual repositories with logic to interact with a database.
- **Slot Booking Logic:**  The `booking` method in `BookingService` could be improved.  Consider using transactions to ensure data consistency when changing slot status and creating a token.
- **Additional Functionalities:** This is a basic example. You could expand on functionalities like patient registration, appointment cancellation/rescheduling, notifications, etc.

**Additional Tips:**

- **Code Comments:** Adding comments to explain complex logic or design decisions can improve code readability.
- **Testing:** Consider unit testing your services and controllers to ensure they work as expected.
- **Detailed Design:** While this provides a good overview, a more detailed LLD document might include sequence diagrams or activity diagrams to illustrate interactions between layers.

**Overall:**

Your code demonstrates a good understanding of LLD principles. By addressing the areas for improvement and incorporating best practices, you can create a more robust and secure doctor appointment booking application.

 * 
 * 
 */