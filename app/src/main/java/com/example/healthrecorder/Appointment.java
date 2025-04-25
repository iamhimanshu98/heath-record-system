package com.example.healthrecorder;

public class Appointment {
    private String doctorName;
    private String appointmentTime;
    private String appointmentDate;
    private String clinicAddress;
    private String status;

    public Appointment(String doctorName, String appointmentTime, String appointmentDate, String clinicAddress, String status) {
        this.doctorName = doctorName;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.clinicAddress = clinicAddress;
        this.status = status;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public String getStatus() {
        return status;
    }

    public boolean isValid() {
        return status != null && status.equals("Scheduled");
    }
}
