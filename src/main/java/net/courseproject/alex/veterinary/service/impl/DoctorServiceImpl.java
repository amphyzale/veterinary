package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.DoctorRegisterRequest;
import net.courseproject.alex.veterinary.dto.request.DoctorRequest;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;
import net.courseproject.alex.veterinary.manager.IDoctorManager;
import net.courseproject.alex.veterinary.service.IDoctorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorManager doctorManager;

    @Override
    public DoctorResponse register(DoctorRegisterRequest doctorRequest) {
        return doctorManager.register(doctorRequest);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        return doctorManager.getAllDoctors();
    }

    @Override
    public DoctorResponse getProfile() {
        return doctorManager.getProfile();
    }

    @Override
    public List<DoctorResponse> findDoctor(String searchQuery) {
        return doctorManager.findDoctor(searchQuery);
    }

    @Override
    public DoctorResponse updateProfile(DoctorRequest doctorRequest) {
        return doctorManager.updateProfile(doctorRequest);
    }

    @Override
    public DoctorResponse updateDoctorById(Long id, DoctorRequest doctorRequest) {
        return doctorManager.updateDoctorById(id, doctorRequest);
    }

    @Override
    public void deleteDoctorById(Long id) {
        doctorManager.deleteDoctorById(id);
    }
}
