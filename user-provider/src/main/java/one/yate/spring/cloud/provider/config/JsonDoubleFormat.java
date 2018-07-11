package one.yate.spring.cloud.provider.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class JsonDoubleFormat extends JsonSerializer<Double> {

	private DecimalFormat df = new DecimalFormat("##.00");   //默认保留两位小数
	  
	@Override
	public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)throws IOException, JsonProcessingException {
		gen.writeString(df.format(value));
	}

}
