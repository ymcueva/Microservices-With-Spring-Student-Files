package pe.aysconsultores.eurekaclient.model;

/**
 * 'Word' object is nicely represented in JSON over a regular String.
 */
public class Word {

	private String word;

	public Word() {
		super();
	}

	public Word(String word) {
		this();
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public String getString() {
		return getWord();
	}

	public void setWord(String word) {
		this.word = word;
	}


}
