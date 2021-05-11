package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.response.ServicesPromoResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.ServiceTransformerProvider;
import net.courseproject.alex.veterinary.manager.IVeterinaryServiceManager;
import net.courseproject.alex.veterinary.repository.ServiceRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VeterinaryServiceManagerImpl implements IVeterinaryServiceManager {

    private final ServiceRepository serviceRepository;

    @Override
    public ServicesPromoResponse getPromoServices() {
        ServicesPromoResponse response = new ServicesPromoResponse();
        response.setServiceResponseList(serviceRepository.findAll()
                .stream()
                .limit(3)
                .map(ServiceTransformerProvider::transformDomainToDto)
                .collect(Collectors.toList()));
        return response;
    }
}
