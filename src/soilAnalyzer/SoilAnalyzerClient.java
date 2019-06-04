package soilAnalyzer;

public class SoilAnalyzerClient {

	public static void main(String[] args) {
		SoilCompressionAnalyser soil = new SoilCompressionAnalyser();
		System.out.println(soil.analyzeSoilSample("soilsample.txt"));
	}
}
