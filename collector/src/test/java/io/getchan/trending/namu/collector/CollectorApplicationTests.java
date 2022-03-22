package io.getchan.trending.namu.collector;

import io.getchan.trending.namu.domain.NamuWikiChange;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CollectorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testEventSender() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration
                        .getCompleteConfiguration(CollectorApplication.class))
                .web(WebApplicationType.NONE)
                .run()) {

            OutputDestination target = context.getBean(OutputDestination.class);
            Message<byte[]> sourceMessage = target.receive(10000);

            final MessageConverter converter = context.getBean(CompositeMessageConverter.class);
            NamuWikiChange namuWikiChange = (NamuWikiChange) converter
                    .fromMessage(sourceMessage, NamuWikiChange.class);

            assertThat(namuWikiChange.getDocumentTitle()).isNotBlank();
        }
    }
}
