import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.EvaluatorUtil;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.TargetField;

public class Predictor {

  private Evaluator evaluator;

  public Predictor(Evaluator evaluator) {
    this.evaluator = evaluator;
  }

  public void predict(Evaluator evaluator, int a, int b, int c, int d) {

    Map inputRecord = new HashMap();
    inputRecord.put("x1", a);
    inputRecord.put("x2", b);
    inputRecord.put("x3", c);
    inputRecord.put("x4", d);

    List<InputField> inputFields = evaluator.getInputFields();

    Map<FieldName, FieldValue> arguments = new LinkedHashMap();

    for (InputField inputField : inputFields) {
      FieldName inputFieldName = inputField.getName();
      Object rawValue = inputRecord.get(inputFieldName.getValue());
      FieldValue inputFieldValue = inputField.prepare(rawValue);
      arguments.put(inputFieldName, inputFieldValue);
    }

    //evaluating model
    Map<FieldName, ?> results = evaluator.evaluate(arguments);

    //Decoupling results from runtime
    Map<String, ?> resultRecord = EvaluatorUtil.decodeAll(results);

    List<? extends TargetField> targetFields = evaluator.getTargetFields();
    for (TargetField targetField : targetFields) {
      FieldName targetName = targetField.getName();
      Object targetValue = results.get(targetName);
      System.out.println(targetValue);
    }
  }
}
