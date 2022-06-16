package guru.sfg.beer.order.service.services.beer;

import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceRestTemplateImpl implements BeerService {

    private final String BASE_PATH = "/api/v1";
    private final RestTemplate restTemplate;
    private final String beerServiceHost;

    public BeerServiceRestTemplateImpl(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${sfg.brewery.beer.service.host}") String beerServiceHost
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.beerServiceHost = beerServiceHost;
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.ofNullable(restTemplate.getForObject(this.beerServiceHost + BASE_PATH + "/beer/" + id.toString(), BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.ofNullable(restTemplate.getForObject(this.beerServiceHost + BASE_PATH + "/beerUpc/" + upc, BeerDto.class));
    }
}
