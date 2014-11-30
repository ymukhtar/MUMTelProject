package com.mumtel.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mumtel.IService.ICallServicesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.IServiceCountryService;
import com.mumtel.model.ServiceCountry;
import com.mumtel.utils.MumTelAuthorities;

@Controller
@Secured(MumTelAuthorities.ROLE_ADMIN)
public class ServiceController {
	
	private static Logger logger=Logger.getLogger(ServiceController.class);

	@Autowired
	private IServiceCountryService serviceCountry;
	@Autowired
	private ICallServicesService servicesService;
	@Autowired
	private ICountryService countryService;
	
	@RequestMapping(value = "/createService", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("countryList",countryService.getAll());
		model.addAttribute("serviceCountry", new ServiceCountry());
		return "createServicePage";
	}
	
	@RequestMapping(value = "/saveServiceCountry", method = RequestMethod.POST)
	public String save(@Valid ServiceCountry serviceCountry, BindingResult result,Model model) {
		if(result.hasFieldErrors()){
			return redirectToError(model);
		}else{
			logger.debug(serviceCountry);
			ServiceCountry already=this.serviceCountry.getServiceCountry(serviceCountry.getCountry().getCallingCode(), serviceCountry.getService().getDescription());
			if(already!=null){
				result.rejectValue("service.description","error.service.description", "Please choose different name!");
				return redirectToError(model);
			}else{
				logger.debug(serviceCountry);
				this.serviceCountry.createNewService(serviceCountry);
				return "redirect:/serviceAndRatesDetails?currentPage=1&searchString=";
			}
		}
	}

	private String redirectToError(Model model) {
		model.addAttribute("countryList",countryService.getAll());
		//model.addAttribute("serviceCountry", new ServiceCountry());
		logger.error("There is an error...");
		return "createServicePage";
	}
}