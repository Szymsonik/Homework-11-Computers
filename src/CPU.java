public class CPU extends Overclocked {
    private final int INCREASE_TEMP = 10;


    public CPU(String producer, String name, String serialNumber, int frequency, double normalTemperature,
               double maxSafeTemperature) {
        super(producer, name, serialNumber, frequency, normalTemperature, maxSafeTemperature);
    }

    @Override
    public void overclockFrequency(double over) {
        try {
            isNotSoHot(over);
            System.out.println("Aktualna temperatura po podkręcaniu: " + super.getTemperature() +"*C");
        } catch (OverheatingRiskException ex) {
            System.err.println("Ryzyko przegrzania komponentu. Temperatura komponentu: " + super.getTemperature()+"*C");
        }
    }


    private void isNotSoHot(double frequency) throws OverheatingRiskException {
        double increaseTemp = (frequency / 100) * INCREASE_TEMP;
        if (super.getTemperature() + increaseTemp > super.getMAX_SAFE_TEMPERATURE()) {
            throw new OverheatingRiskException();
        }
        super.setTemperature(super.getTemperature() + increaseTemp);
        super.setFrequency(super.getFrequency() + frequency);
    }

    @Override
    public String toString() {
        return super.toString() + "Frequency: " + super.getFrequency() + "MHz" + ", Normal temperature: " +
                super.getNORMAL_TEMPERATURE() + "*C, " + "Actual temperature: " + super.getTemperature() + "*C, "
                + "Max safe temperature: " + super.getMAX_SAFE_TEMPERATURE() + "*C";
    }
}
