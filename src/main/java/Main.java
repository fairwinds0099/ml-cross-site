import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Computable;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.evaluator.TargetField;
import org.jpmml.model.PMMLUtil;
import org.xml.sax.SAXException;

public class Main {

  public static void main(String[] args) {
    System.out.println("test");
    Main main = new Main();
    Evaluator evaluator = main.loadPmml();
    main.predict(evaluator, 1 ,8,99,1);
    main.predict(evaluator,111,89,9,11);

  }

  private Evaluator loadPmml() {
    PMML pmml = new PMML();
    InputStream inputStream = null;
    try {
     inputStream = new FileInputStream(
          "/Users/apple4u/Desktop/data_eng/ml-cross-site/src/main/resources/decision_tree.pmml");
    }catch (IOException e) {
        e.printStackTrace();
      }

    InputStream is = inputStream;
    try {
      pmml = PMMLUtil.unmarshal(is);
    } catch (SAXException | JAXBException saxException) {
      saxException.printStackTrace();
    }
    try {
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ModelEvaluatorFactory modelEvaluatorFactory =ModelEvaluatorFactory.newInstance();
    Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
    pmml = null;
    return evaluator;
  }

  private int predict(Evaluator evaluator, int a, int b, int c, int d) {
    Map data = new HashMap();
    data.put("x1", a);
    data.put("x2", b);
    data.put("x3", c);
    data.put("x4", d);

    List<InputField> inputFields = evaluator.getInputFields();

    Map<FieldName, FieldValue> arguments = new LinkedHashMap();

    for (InputField inputField: inputFields) {
      FieldName inputFieldName = inputField.getName();
      Object rawValue = data.get(inputFieldName.getValue());
      FieldValue inputFieldValue = inputField.prepare(rawValue);
      arguments.put(inputFieldName, inputFieldValue);
    }
    Map results = evaluator.evaluate(arguments);
    List<TargetField> targetfields = evaluator.getTargetFields();
    TargetField targetField = targetfields.get(0);
    FieldName targetFieldName = targetField.getName();

    Object targetFieldValue = results.get(targetFieldName);

    int primitiveValue = -1;
    if (targetFieldValue instanceof Computable) {
      Computable computable = (Computable) targetFieldValue;
      primitiveValue = (Integer) computable.getResult();
    }
    System.out.println(a + " " + b + " " + c + " " + d + ":" + primitiveValue);
    return primitiveValue;
  }
}