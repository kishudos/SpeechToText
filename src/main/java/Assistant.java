import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * TODO
 * 
 * @author Krishna kumar vishwakarma
 * @date 30-Apr-2018
 */
public class Assistant {
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		configuration.setUseGrammar(false);

		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

		recognizer.startRecognition(true);
		System.out.println("Start speaking. \n");
		SpeechResult result;
		long timer = 0;

		while (timer < 2 * 60 * 1000) {
			result = recognizer.getResult();
			if (result != null) {
				System.out.print("You said: " + result.getHypothesis());
			} else {
				System.out.println("I can't hear what you said.\n");
			}
		}
		recognizer.stopRecognition();
	}
}
