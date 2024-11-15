package sk.sufliarsky.peter.graylog;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.expressions.Expression;
import org.graylog.plugins.pipelineprocessor.ast.functions.Function;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;
import org.graylog2.plugin.Message;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the plugin. Your class should implement one of the existing plugin
 * interfaces. (i.e. AlarmCallback, MessageInput, MessageOutput)
 */
public class FieldNamesRemovePrefixFunction implements Function<Void> {

    public static final String NAME = "field_names_remove_prefix";
    private static final String PREFIX_ARG = "prefix";

    private final ParameterDescriptor<String, String> prefixParam = ParameterDescriptor
            .string(PREFIX_ARG)
            .description("Prefix to remove from field names.")
            .build();

    @Override
    public Object preComputeConstantArgument(FunctionArgs functionArgs, String s, Expression expression) {
        return expression.evaluateUnsafe(EvaluationContext.emptyContext());
    }


    @Override
    public Void evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
        String prefix = prefixParam.required(functionArgs, evaluationContext);
        if (prefix == null || prefix.isEmpty()) {
            return null;
        }

        Message message = evaluationContext.currentMessage();
        Set<String> fieldNames = new HashSet<>(message.getFieldNames());
        fieldNames.forEach(fieldName -> {
            if (fieldName.startsWith(prefix)) {
                String newFieldName = fieldName.substring(prefix.length());
                message.addField(newFieldName, message.getField(fieldName));
                message.removeField(fieldName);
            }
        });

        return null;
    }

    @Override
    public FunctionDescriptor<Void> descriptor() {
        return FunctionDescriptor.<Void>builder()
                .name(NAME)
                .description("Remove given prefix from all field names starting with that prefix.")
                .returnType(Void.class)
                .params(prefixParam)
                .build();
    }
}
