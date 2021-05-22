package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Service;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.dto.request.ServiceRequest;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.ServiceTransformerProvider;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.manager.IVeterinaryServiceManager;
import net.courseproject.alex.veterinary.repository.ServiceRepository;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VeterinaryServiceManagerImpl implements IVeterinaryServiceManager {

    private final ServiceRepository serviceRepository;
    private final IAuthenticationManager authenticationManager;
    private final ServiceTransformerProvider transformer;

    @Override
    public List<ServiceResponse> getPromoServices() {
        return serviceRepository.findByIsPromoTrue()
                .stream()
                .map(transformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse addService(ServiceRequest serviceRequest) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Service service = transformer.transformToDomain(serviceRequest);
        service.setCreated(LocalDateTime.now());
        service.setUpdated(LocalDateTime.now());
        service.setStatus(Status.ACTIVE);
        return transformer.transformDomainTo(serviceRepository.save(service));
    }

    @Override
    public List<ServiceResponse> getServices() {
        return serviceRepository.findAll()
                .stream()
                .map(transformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        return transformer.transformDomainTo(serviceRepository.getOne(id));
    }

    @Override
    public ServiceResponse updateService(ServiceRequest serviceRequest, Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Service service = serviceRepository.getOne(id);
        updateServiceInfo(service, serviceRequest);
        return transformer.transformDomainTo(serviceRepository.save(service));
    }

    @Override
    public void deleteService(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        serviceRepository.delete(serviceRepository.getOne(id));
    }

    private void updateServiceInfo(Service service, ServiceRequest serviceRequest) {
        service.setName(serviceRequest.getName());
        service.setDescription(serviceRequest.getDescription());
        service.setPrice(serviceRequest.getPrice());
        service.setImage(serviceRequest.getImage());
        service.setDuration(serviceRequest.getDuration());
        service.setPromo(service.isPromo());
        service.setUpdated(LocalDateTime.now());
    }
}
