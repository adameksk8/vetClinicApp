package it.nowak.adam.vetClinicApp.Controller;

import it.nowak.adam.vetClinicApp.Model.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<String> getAppointmentsByDoctorAndDate(@PathVariable(value = "doctorId") long doctorId,
                                                                                       @RequestBody String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        LocalDate localDate = LocalDate.parse(jsonObj.get("date").toString());
        return new ResponseEntity(appointmentRepository
                .findAll()
                .stream()
                .filter(a -> a.getAppointmentDateAndTime().toLocalDate().equals(localDate))
                .filter(a -> a.getDoctor().getId() == doctorId)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/appointments/")
    public ResponseEntity<String> makeAppointment(@RequestBody String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        long customerId = jsonObj.getLong("customerId");
        long customerPin = jsonObj.getLong("customerPin");
        long doctorId = jsonObj.getLong("doctorId");
        LocalDateTime localDateTime = LocalDateTime.parse(jsonObj.get("appointmentDateTime").toString());
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (doctor.isPresent()) {
            if (customer.isPresent() && customer.get().getPin() == customerPin) {//PIN validation
                Appointment appointment = new Appointment();
                appointment.setDoctor(doctor.get());
                appointment.setCustomer(customer.get());
                appointment.setAppointmentDateAndTime(localDateTime);
                appointmentRepository.save(appointment);
                return new ResponseEntity<>("Appointment saved", HttpStatus.CREATED);
            } else return new ResponseEntity<>("Invalid customer ID or PIN", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("Invalid doctor", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable(value = "appointmentId") long appointmentId,
                                                    @RequestBody String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        long customerId = jsonObj.getLong("customerId");
        long customerPin = jsonObj.getLong("customerPin");

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            Customer customer = appointment.get().getCustomer();
            if (customer.getId() == customerId && customer.getPin() == customerPin) {//ID & PIN validation
                appointmentRepository.delete(appointment.get());
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Invalid customer ID or PIN", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("Appointment doesn't exist!", HttpStatus.BAD_REQUEST);
    }
}
