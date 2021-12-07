package com.example.producingwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.AddCountryRequest;
import io.spring.guides.gs_producing_web_service.AddCountryResponse;
import io.spring.guides.gs_producing_web_service.GetCountriesRequest;
import io.spring.guides.gs_producing_web_service.GetCountriesResponse;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountriesRequest")
	@ResponsePayload
	public GetCountriesResponse getCountries(@RequestPayload GetCountriesRequest request) {
		GetCountriesResponse response = new GetCountriesResponse();
		countryRepository.getCountries(response.getCountriesvalues());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
	@ResponsePayload
	public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
		AddCountryResponse response = new AddCountryResponse();
		countryRepository.addCountry(request.getName(),request.getPopulation(),request.getCapital(),request.getCurrency());
		return response;
	}
}
