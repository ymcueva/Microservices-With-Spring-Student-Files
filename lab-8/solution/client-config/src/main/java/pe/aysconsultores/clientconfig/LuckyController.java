package pe.aysconsultores.clientconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class LuckyController {

	@Value("${lucky-word}")
	private String luckyWord;

	@RequestMapping("/lucky-word")
	public String luckyWord(){

		return "Mi heroe es " + luckyWord;
	}
}
