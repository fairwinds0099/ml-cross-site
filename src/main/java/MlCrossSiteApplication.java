import org.jpmml.evaluator.Evaluator;

public class MlCrossSiteApplication {

  public static void main(String[] args) {
    PmmlLaoder pmmlLaoder = new PmmlLaoder();
    Evaluator evaluator = pmmlLaoder.loadPmml();
    Predictor predictor = new Predictor(evaluator);

    predictor.predict(evaluator, 2, 5, 6, 990);
    predictor.predict(evaluator, 111, 89, 9, 11);
  }
}