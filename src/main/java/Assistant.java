import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * Work well for US accent however the accuracy for Indian English is not good.
 * 
 * @author Krishna kumar vishwakarma
 * @date 30-Apr-2018
 */
public class Assistant {
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		Properties pro = new Properties();
		String CONFPATH = "src/main/resources/application.properties";
		File file = new File(CONFPATH);
		String fileName = file.getAbsolutePath();
		try {
			pro.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		String acoustivModelPath = pro.getProperty("hindiAcousticModelPath");
		String defaultDictionaryPath = pro.getProperty("hindiDictionaryPath");
		String defaultLanguageModelPath = pro.getProperty("hindiLanguageModelPath");
		
		configuration.setAcousticModelPath(acoustivModelPath);
		configuration.setDictionaryPath(defaultDictionaryPath);
		configuration.setLanguageModelPath(defaultLanguageModelPath);
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
