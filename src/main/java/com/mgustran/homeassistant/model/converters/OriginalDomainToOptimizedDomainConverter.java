package com.mgustran.homeassistant.model.converters;

import com.mgustran.homeassistant.model.optimized.HaDomain;
import com.mgustran.homeassistant.model.optimized.HaField;
import com.mgustran.homeassistant.model.optimized.HaService;
import com.mgustran.homeassistant.model.original.OriginalHaDomain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OriginalDomainToOptimizedDomainConverter {

    public HaDomain convert(final OriginalHaDomain originalHaDomain) {
        final HaDomain.HaDomainBuilder dtDomain = HaDomain.builder();
        List<HaService> haServices = new ArrayList<>();
        originalHaDomain.getServices().forEach((service, map) -> {
            List<HaField> haFields = new ArrayList<>();
            ((LinkedHashMap<String, LinkedHashMap<String, Object>>)map.get("fields")).forEach((fieldId, fieldMap) -> {
                final HaField haField = HaField.builder()
                        .id(fieldId)
                        .name((String) fieldMap.get("name"))
                        .description((String) fieldMap.get("description"))
                        .required((Boolean) fieldMap.get("required"))
                        .exampleInt(fieldMap.get("example") instanceof Integer ? (Integer) fieldMap.get("example") : null)
                        .exampleStr(fieldMap.get("example") instanceof String ? (String) fieldMap.get("example") : null)
                        .selector((LinkedHashMap<String, Object>) fieldMap.get("selector"))
                        .build();
                haFields.add(haField);
            });


            final HaService haService = HaService.builder()
                    .id(service)
                    .name((String) map.get("name"))
                    .description((String) map.get("description"))
                    .fields(haFields)
                    .target(map.get("target"))
                    .build();

            haServices.add(haService);
        });
        dtDomain.domain(originalHaDomain.getDomain());
        dtDomain.services(haServices);
        return dtDomain.build();
    }

    public List<HaDomain> convertList(final List<OriginalHaDomain> originalHaDomainList) {
        return originalHaDomainList.stream().map(this::convert).collect(Collectors.toList());
    }
}
