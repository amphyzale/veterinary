package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.response.ServicesPromoResponse;
import net.courseproject.alex.veterinary.manager.IVeterinaryServiceManager;
import net.courseproject.alex.veterinary.service.IVeterinaryServiceService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VeterinaryServiceServiceImpl implements IVeterinaryServiceService {

    private final IVeterinaryServiceManager veterinaryServiceManager;

    @Override
    public ServicesPromoResponse getPromoServices() {
        return veterinaryServiceManager.getPromoServices();
    }
}
