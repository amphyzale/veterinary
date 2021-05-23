package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.AppointmentRequest;
import net.courseproject.alex.veterinary.dto.response.AppointmentResponse;
import net.courseproject.alex.veterinary.manager.IAppointmentManager;
import net.courseproject.alex.veterinary.service.IAppointmentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentServiceImpl implements IAppointmentService {

    private final IAppointmentManager appointmentManager;

    @Override
    public AppointmentResponse getAppoint(AppointmentRequest appointmentRequest) {
        return appointmentManager.getAppoint(appointmentRequest);
    }

    @Override
    public List<AppointmentResponse> getAllAppoints() {
        return appointmentManager.getAllAppoints();
    }

    @Override
    public List<AppointmentResponse> getAllAppointsByUserId(Long userId) {
        return appointmentManager.getAllAppointsByUserId(userId);
    }

    @Override
    public List<AppointmentResponse> getAllAppointsByDoctorId(Long doctorId) {
        return appointmentManager.getAllAppointsByDoctorId(doctorId);
    }

    @Override
    public List<AppointmentResponse> getAllAppointsByPetId(Long petId) {
        return appointmentManager.getAllAppointsByPetId(petId);
    }

    @Override
    public AppointmentResponse changeAppointStatus(Long appointId, String status) {
        return appointmentManager.changeAppointStatus(appointId, status);
    }

    @Override
    public void deleteAppoint(Long id) {
        appointmentManager.deleteAppoint(id);
    }
}
