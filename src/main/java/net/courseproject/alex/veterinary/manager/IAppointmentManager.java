package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.dto.request.AppointmentRequest;
import net.courseproject.alex.veterinary.dto.response.AppointmentResponse;

import java.util.List;

public interface IAppointmentManager {
    AppointmentResponse getAppoint(AppointmentRequest appointmentRequest);
    List<AppointmentResponse> getAllAppoints();
    List<AppointmentResponse> getAllAppointsForUser();
    List<AppointmentResponse> getAllAppointsByUserId(Long userId);
    List<AppointmentResponse> getAllAppointsByDoctorId(Long doctorId);
    List<AppointmentResponse> getAllAppointsByPetId(Long petId);
    AppointmentResponse changeAppointStatus(Long appointId, String status);
    void deleteAppoint(Long id);
}
