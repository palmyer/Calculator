package com.cyz.calculator;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cyz.calculator.MainActivity.SYMBOL_DIVIDE;
import static com.cyz.calculator.MainActivity.SYMBOL_EQUAL;
import static com.cyz.calculator.MainActivity.SYMBOL_MINUS;
import static com.cyz.calculator.MainActivity.SYMBOL_MULTIPLY;
import static com.cyz.calculator.MainActivity.SYMBOL_NULL;
import static com.cyz.calculator.MainActivity.SYMBOL_PLUS;

/**
 * Calculator适配器
 *
 * @author cyz
 * @date 2019/3/5
 */
public class CalcAdapter extends BaseQuickAdapter<CalcData, BaseViewHolder> {
    public CalcAdapter(int layoutResId, @Nullable List<CalcData> data) {
        super(layoutResId, data);
    }

    public CalcAdapter(@Nullable List<CalcData> data) {
        this(R.layout.item_calc, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CalcData item) {
        String symbol = "";
        switch (item.symbol) {
            case SYMBOL_PLUS:
                symbol = "+";
                break;
            case SYMBOL_MINUS:
                symbol = "-";
                break;
            case SYMBOL_MULTIPLY:
                symbol = "×";
                break;
            case SYMBOL_DIVIDE:
                symbol = "÷";
                break;
            case SYMBOL_NULL:
                symbol = "";
                break;
            case SYMBOL_EQUAL:
                symbol = "=";
                break;
            default:
        }
        String newNum = "";
        if(!TextUtils.isEmpty(item.num)){
            if(TextUtils.equals(" ", item.num)){
                newNum = item.num;
            }else if(item.num.contains(".")){
                newNum = item.num;
            }else{
                newNum = Constants.doubleTrans(item.num.replaceAll(" ", ""));
            }
        }
        helper.setText(R.id.tv_content, newNum)
                .setVisible(R.id.tv_symbol, !TextUtils.isEmpty(symbol))
                .setText(R.id.tv_symbol, symbol)
                .setVisible(R.id.ll_content, !item.hasLine)
                .setVisible(R.id.iv_dotline, item.hasLine);
    }
}
