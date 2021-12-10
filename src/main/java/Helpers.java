import java.util.List;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.OutputField;
import org.jpmml.evaluator.TargetField;

public class Helpers {

  public List<? extends InputField> getXfields(Evaluator evaluator) {
    List<? extends InputField> inputFields = evaluator.getInputFields();
    System.out.println("Input Fields: " + inputFields);
    return inputFields;
  }

  public List<? extends TargetField> getPrimaryResult(Evaluator evaluator) {
    List<? extends TargetField> targetFields = evaluator.getTargetFields();
    System.out.println("Target fields: " + targetFields);
    return targetFields;
  }

  public List<OutputField> getSecondaryResult(Evaluator evaluator) {
    List<OutputField> outputFields = evaluator.getOutputFields();
    System.out.println("Output fields: " + outputFields);
    return outputFields;
  }
}
