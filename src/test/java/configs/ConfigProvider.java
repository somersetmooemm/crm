package configs;

public class ConfigProvider {

    public static final TestConfiguration TEST_CONFIGURATION = org.aeonbits.owner.ConfigFactory.create(TestConfiguration.class);
    public static final DbConfiguration TEST_CONFIGURATION_DB = org.aeonbits.owner.ConfigFactory.create(DbConfiguration.class);
}
