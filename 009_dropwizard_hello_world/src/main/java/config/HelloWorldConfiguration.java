package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Template;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotBlank;

public class HelloWorldConfiguration extends Configuration {

    @NotBlank
    private String template;

    @NotBlank
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }

}
