package pe.aysconsultores.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentenceController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/sentence")
	public String getSentence(){
		String sentence = "Hay problemas con los microservicios";
		try{

			sentence =  String.format("%s %s %s %s %s.",
					getWord("SUBJECT"),
					getWord("VERB"),
					getWord("ARTICLE"),
					getWord("ADJECTIVE"),
					getWord("NOUN") );

		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

		return sentence;
	}

	private String getWord(String service) {
		return restTemplate.getForObject("http://" + service, String.class);
	}
}
