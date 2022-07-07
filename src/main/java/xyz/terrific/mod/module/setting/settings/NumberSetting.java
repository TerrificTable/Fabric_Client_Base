package xyz.terrific.mod.module.setting.settings;

import xyz.terrific.mod.module.setting.Setting;

public class NumberSetting extends Setting {

    public double value;
    public double min;
    public double max;
    public double increment;

    /**
     * NumberSetting Constructor
     * @param name Setting name
     * @param value default Setting value
     * @param min min value
     * @param max max value
     * @param increment the amount to increment value by (not used currently)
     */
    public NumberSetting(String name, double value, double min, double max, double increment) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.mode = "Number";
    }

    /**
     * Get current value
     * @return double value
     */
    public double getValue() {
        return value;
    }

    /**
     * set current value
     * @param value new value
     */
    public void setValue(double value) {
        double precision = 1 / increment;

        this.value = Math.round(Math.max(min, Math.min(max, value)) * precision) / precision;
    }

    /**
     * increment value
     * @param positive increment positive or negative
     */
    public void increment(boolean positive) {
        setValue(getValue() + (positive ? 1 : -1) * increment);
    }

    /**
     * get Min value
     * @return double min value
     */
    public double getMin() {
        return min;
    }

    /**
     * set min value
     * @param min new min value
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * get Max value
     * @return double max value
     */
    public double getMax() {
        return max;
    }

    /**
     * set max value
     * @param max new max value
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * get increment amount
     * @return double increment amount
     */
    public double getIncrement() {
        return increment;
    }

    /**
     * set increment amount
     * @param increment new increment amount
     */
    public void setIncrement(double increment) {
        this.increment = increment;
    }
}

