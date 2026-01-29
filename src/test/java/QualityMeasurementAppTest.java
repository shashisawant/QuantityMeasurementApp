import org.example.QualityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class QualityMeasurementAppTest {

    @Test
    void givenSameFeetValue_whenCompared_shouldReturnTrue(){
        QualityMeasurementApp.Feet feet1 = new  QualityMeasurementApp.Feet(5.0);
        QualityMeasurementApp.Feet feet2 = new  QualityMeasurementApp.Feet(5.0);
        assertTrue(feet1.equals(feet2));
    }
}
