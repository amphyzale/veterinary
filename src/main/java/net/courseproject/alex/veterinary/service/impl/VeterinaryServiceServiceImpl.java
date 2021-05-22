package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.ServiceRequest;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;
import net.courseproject.alex.veterinary.manager.IVeterinaryServiceManager;
import net.courseproject.alex.veterinary.service.IVeterinaryServiceService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VeterinaryServiceServiceImpl implements IVeterinaryServiceService {

    private final IVeterinaryServiceManager veterinaryServiceManager;

    @Override
    public List<ServiceResponse> getPromoServices() {
        return veterinaryServiceManager.getPromoServices();
    }

    @Override
    public ServiceResponse addService(ServiceRequest serviceRequest) {
        return veterinaryServiceManager.addService(serviceRequest);
    }

    @Override
    public List<ServiceResponse> getServices() {
        return veterinaryServiceManager.getServices();
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        return veterinaryServiceManager.getServiceById(id);
    }

    @Override
    public ServiceResponse updateService(ServiceRequest serviceRequest, Long id) {
        return veterinaryServiceManager.updateService(serviceRequest, id);
    }

    @Override
    public void deleteService(Long id) {
        veterinaryServiceManager.deleteService(id);
    }
}
