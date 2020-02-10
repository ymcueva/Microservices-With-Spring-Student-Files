package pe.aysconsultores.eurekaclient.dao;

import pe.aysconsultores.eurekaclient.model.Word;

import org.springframework.stereotype.Component;

@Component
public class VerbFallbackClient implements VerbClient{
	@Override
	public Word getWord() {
		return new Word("jijiji!!!!");
	}
}
