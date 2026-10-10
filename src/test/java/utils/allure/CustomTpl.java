package utils.allure;

import io.qameta.allure.restassured.AllureRestAssured;
import lombok.Getter;

@Getter
public final class CustomTpl {

    private static final String DEFAULT_REQUEST_TEMPLATE = "http-request.ftl";
    private static final String DEFAULT_RESPONSE_TEMPLATE = "http-response.ftl";
    private static final String DEFAULT_REQUEST_ATTACHMENT_NAME = "Request";
    private static final String DEFAULT_RESPONSE_ATTACHMENT_NAME = "Response";
    private final AllureRestAssured filter;

    private CustomTpl() {
        filter = new AllureRestAssured();
        configureFilter();
    }

    public static CustomTpl customLogFilter() {
        return SingletonHolder.INSTANCE;
    }

    private void configureFilter() {
        filter.setRequestTemplate(CustomTpl.DEFAULT_REQUEST_TEMPLATE);
        filter.setResponseTemplate(CustomTpl.DEFAULT_RESPONSE_TEMPLATE);
        filter.setRequestAttachmentName(CustomTpl.DEFAULT_REQUEST_ATTACHMENT_NAME);
        filter.setResponseAttachmentName(CustomTpl.DEFAULT_RESPONSE_ATTACHMENT_NAME);
    }

    private static final class SingletonHolder {
        private static final CustomTpl INSTANCE = new CustomTpl();
    }
}