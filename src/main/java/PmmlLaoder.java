import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.xml.sax.SAXException;

public class PmmlLaoder {

  public Evaluator loadPmml() {
    Evaluator evaluator = null;
    try {
      evaluator = new LoadingModelEvaluatorBuilder()
          .load(new File(
              "/Users/apple4u/Desktop/data_eng/machinelearning/model-in-product/sklearn-jpmml/logistic_regression_model.pmml"))
          .build();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    evaluator.verify();
    return evaluator;
  }

}
