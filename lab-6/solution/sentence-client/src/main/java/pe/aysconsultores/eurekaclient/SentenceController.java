package pe.aysconsultores.eurekaclient;

import pe.aysconsultores.eurekaclient.dao.AdjectiveClient;
import pe.aysconsultores.eurekaclient.dao.ArticleClient;
import pe.aysconsultores.eurekaclient.dao.NounClient;
import pe.aysconsultores.eurekaclient.dao.SubjectClient;
import pe.aysconsultores.eurekaclient.dao.VerbClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceController {

	@Autowired
	private SubjectClient subjectClient;
	@Autowired
	private VerbClient verbClient;
	@Autowired
	private ArticleClient articleClient;
	@Autowired
	private AdjectiveClient adjectiveClient;
	@Autowired
	private NounClient nounClient;


	@GetMapping("/sentence")
	public String getSentence(){
		String sentence = "Hay problemas con los microservicios";
		try{

			sentence =  String.format("%s %s %s %s %s.",
					subjectClient.getWord().getString(),
					verbClient.getWord().getString(),
					articleClient.getWord().getString(),
					adjectiveClient.getWord().getString(),
					nounClient.getWord().getString());

		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

		return sentence;
	}


}