import org.junit.jupiter.api.Test;
import service.WeatherValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherValidatorTest {

    static WeatherValidator validation = new WeatherValidator();

    @Test
    void longitudeValidation() {
        boolean result = validation.longitudeScopeValidation("45");

        assertThat(result)
                .isNotNull()
                .isTrue();
    }

    @Test
    void longitudeValidationOverTheBounds() {
        boolean result = validation.longitudeScopeValidation("234");

        assertThat(result)
                .isNotNull()
                .isFalse();
    }

    @Test
    void latitudeValidation() {
        boolean result = validation.latitudeScopeValidation("-88");

        assertThat(result)
                .isNotNull()
                .isTrue();
    }

    @Test
    void latitudeValidationOverTheBounds() {
        boolean result = validation.latitudeScopeValidation("-1111");

        assertThat(result)
                .isNotNull()
                .isFalse();
    }

    @Test
    void cityNameValidation() {
        boolean result = validation.cityNameValidation("London");

        assertThat(result)
                .isNotNull()
                .isTrue();
    }

    @Test
    void cityNameValidationEmpty() {
        boolean result = validation.cityNameValidation("");

        assertThat(result)
                .isNotNull()
                .isFalse();
    }

    @Test
    void when_date_is_valid_then_true_should_be_returned() {
        //given
        //when
        boolean isValid = validation.dateFormatValidation("2022/03/09");
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_date_is_not_valid_then_false_should_be_returned() {
        //given
        //when
        boolean isValid = validation.dateFormatValidation("2022/03/0");
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_date_is_not_empty_then_true_should_be_returned() {
        //given
        //when
        boolean isValid = validation.dateIsNotEmptyValidation("2022/03/09");
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_date_is_empty_then_false_should_be_returned() {
        //given
        //when
        boolean isValid = validation.dateIsNotEmptyValidation("");
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_addWeatherDateScopeValidation_is_valid_then_return_true() {
        //given
        String date = (LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //when
        boolean isValid = validation.addWeatherDateScopeValidation(date);
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_addWeatherDateScopeValidation_is_used_and_date_is_after_current_date_plus_6_days_then_false_should_be_returned() {
        //given
        String date = (LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //when
        boolean isValid = validation.addWeatherDateValidation(date);
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_addWeatherDateScopeValidation_is_used_and_date_is_before_current_date_then_false_should_be_returned() {
        //given
        String date = (LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //when
        boolean isValid = validation.addWeatherDateScopeValidation(date);
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_displayWeatherDateScopeValidation_is_valid_then_return_true() {
        //given
        String date = (LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //when
        boolean isValid = validation.displayWeatherDateScopeValidation(date);
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_displayWeatherDateScopeValidation_is_used_and_date_is_after_current_date_plus_6_days_then_false_should_be_returned() {
        //given
        String date = (LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //when
        boolean isValid = validation.displayWeatherDateScopeValidation(date);
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_displayWeatherDateScopeValidation_is_used_and_date_is_before_current_date_then_false_should_be_returned() {
        //given
        String date = (LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //when
        boolean isValid = validation.displayWeatherDateScopeValidation(date);
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_isFieldNotEmpty_is_valid_then_true_should_be_returned() {
        //given
        //when
        boolean isValid = validation.isFieldNotEmpty("input");
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_isFieldNotEmpty_is_not_valid_then_false_should_be_returned() {
        //given
        //when
        boolean isValid = validation.isFieldNotEmpty("");
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_coordinateFormatValidation_is_valid_then_true_should_be_returned(){
        //given
        //when
        boolean isValid=validation.coordinateFormatValidation("20.15");
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_coordinateFormatValidation_is_not_valid_then_false_should_be_returned(){
        //given
        //when
        boolean isValid=validation.coordinateFormatValidation("20a");
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_dateIsNotEmptyValidation_is_valid_then_true_should_be_returned(){
        //given
        //when
        boolean isValid=validation.dateIsNotEmptyValidation("2022/03/22");
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_dateIsNotEmptyValidation_is_not_valid_then_true_should_be_returned(){
        //given
        //when
        boolean isValid=validation.dateIsNotEmptyValidation("");

        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_addWeatherDateValidation_is_valid_then_true_should_be_returned(){
        //given
        String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        //when
        boolean isValid=validation.addWeatherDateValidation(date);
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_addWeatherDateValidation_is_not_valid_then_true_should_be_returned(){
        //given
        //when
        boolean isValid=validation.addWeatherDateValidation("202/03/22");
        //then
        assertThat(isValid).isFalse();
    }

    @Test
    void when_displayWeatherDateValidation_is_valid_then_true_should_be_returned(){
        //given
        //when
        boolean isValid=validation.displayWeatherDateValidation("2022/03/22");
        //then
        assertThat(isValid).isTrue();
    }

    @Test
    void when_displayWeatherDateValidation_is_not_valid_then_true_should_be_returned(){
        //given
        //when
        boolean isValid=validation.displayWeatherDateValidation("202/03/22");
        //then
        assertThat(isValid).isFalse();
    }











}