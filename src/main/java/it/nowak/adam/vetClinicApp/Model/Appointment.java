package it.nowak.adam.vetClinicApp.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Customer customer;
    private LocalDateTime appointmentDateAndTime;

    public long getId() { return id; }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getAppointmentDateAndTime() {
        return appointmentDateAndTime;
    }

    public void setAppointmentDateAndTime(LocalDateTime appointmentDate) {
        this.appointmentDateAndTime = appointmentDate;
    }


}

