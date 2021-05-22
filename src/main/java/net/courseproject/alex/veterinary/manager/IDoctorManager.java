package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.dto.request.DoctorRegisterRequest;
import net.courseproject.alex.veterinary.dto.request.DoctorRequest;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;

import java.util.List;

public interface IDoctorManager {
    DoctorResponse register(DoctorRegisterRequest doctorRequest);
    List<DoctorResponse> getAllDoctors();
    DoctorResponse getProfile();
    List<DoctorResponse> findDoctor(String searchQuery);
    DoctorResponse updateProfile(DoctorRequest doctorRequest);
    DoctorResponse updateDoctorById(Long id, DoctorRequest doctorRequest);
    void deleteDoctorById(Long id);
}
