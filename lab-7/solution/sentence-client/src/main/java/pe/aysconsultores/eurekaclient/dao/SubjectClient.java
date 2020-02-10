package pe.aysconsultores.eurekaclient.dao;


import pe.aysconsultores.eurekaclient.model.Word;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "SUBJECT")
public interface SubjectClient {
	@RequestMapping(value="/", method = RequestMethod.GET)
	Word getWord();
}
