package bg.softuni.bikes_shop.service.impl;

import bg.softuni.bikes_shop.model.dto.MapRatesDTO;
import bg.softuni.bikes_shop.model.entity.CurrencyEntity;
import bg.softuni.bikes_shop.repository.CurrencyRepository;
import bg.softuni.bikes_shop.service.CurrencyService;
import bg.softuni.bikes_shop.service.ItemService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;

    }

    @Override
    public void add(MapRatesDTO mapRatesDTO) {

        CurrencyEntity ceBGN = new CurrencyEntity();
        ceBGN.setName("BGN");
        ceBGN.setRate(mapRatesDTO.rates().get("BGN"));
        currencyRepository.save(ceBGN);

        CurrencyEntity ceUSD = new CurrencyEntity();
        ceUSD.setName("USD");
        ceUSD.setRate(mapRatesDTO.rates().get("USD"));
        currencyRepository.save(ceUSD);

        CurrencyEntity cePLN = new CurrencyEntity();
        cePLN.setName("PLN");
        cePLN.setRate(mapRatesDTO.rates().get("PLN"));
        currencyRepository.save(cePLN);
    }

}
