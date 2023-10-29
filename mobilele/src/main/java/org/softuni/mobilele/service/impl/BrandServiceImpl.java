package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.BrandDTO;
import org.softuni.mobilele.model.dto.ModelDTO;
import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {

//        List<BrandDTO> bandDTOList = new ArrayList<>();
//        for (BrandEntity b : brandRepository.getAllBrands()) {
//            BrandDTO brandDTO = new BrandDTO();
//            brandDTO.setName(b.getName());
//            List<ModelDTO> models = new ArrayList<>();
//            for (ModelEntity model : b.getModels()) {
//                ModelDTO modelDTO = new ModelDTO();
//                modelDTO.setId(model.getId());
//                modelDTO.setName(model.getName());
//                models.add(modelDTO);
//
//            }
//            brandDTO.setModels(models);
//            bandDTOList.add(brandDTO);
//        }

        return brandRepository.getAllBrands().stream()
                .map(b -> new BrandDTO(b.getName(),
                 b.getModels().stream()
                  .map(m -> new ModelDTO(m.getId(), m.getName(),m.getStartYear(),m.getEndYear(),m.getImageUrl()))
                         .sorted(Comparator.comparing(ModelDTO::getId))
                                .collect(Collectors.toList()))).sorted(Comparator.comparing(BrandDTO::getName)).collect(Collectors.toList());
    }
}