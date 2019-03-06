package com.cyz.calculator;

/**
 * Calculator数据
 *
 * @author cyz
 * @date 2019/3/5
 */
public class CalcData {
    public String num;
    public @MainActivity.SYMBOL_TYPE
    int symbol;
    public boolean hasLine = false;

    public CalcData(@MainActivity.SYMBOL_TYPE int symbol, String num) {
        this.symbol = symbol;
        this.num = num;
    }

    public CalcData(boolean hasLine) {
        this.hasLine = hasLine;
    }
}
