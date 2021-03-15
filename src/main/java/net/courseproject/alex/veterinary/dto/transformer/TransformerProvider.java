package net.courseproject.alex.veterinary.dto.transformer;

import net.courseproject.alex.veterinary.domain.BaseEntity;
import net.courseproject.alex.veterinary.dto.AbstractDto;

public interface TransformerProvider {

    <DOMAIN extends BaseEntity, TO extends AbstractDto> TO transformDomainToDto(DOMAIN domain, TO toClass);

    <DOMAIN extends AbstractDto, TO extends BaseEntity> boolean supports(DOMAIN domain, TO toClass);

}
