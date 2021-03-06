package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.dto.request.ServiceRequest;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;

import java.util.List;

public interface IVeterinaryServiceManager {
    List<ServiceResponse> getPromoServices();
    ServiceResponse addService(ServiceRequest serviceRequest);
    List<ServiceResponse> getServices();
    ServiceResponse getServiceById(Long id);
    ServiceResponse updateService(ServiceRequest serviceRequest, Long id);
    void deleteService(Long id);
}
