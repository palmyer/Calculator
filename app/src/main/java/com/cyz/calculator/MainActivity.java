package com.cyz.calculator;

import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Calculator首页
 *
 * @author cyz
 * @date 2019/3/5
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 符号 +
     */
    public static final int SYMBOL_PLUS = 1;
    /**
     * 符号 -
     */
    public static final int SYMBOL_MINUS = 2;
    /**
     * 符号 ×
     */
    public static final int SYMBOL_MULTIPLY = 3;
    /**
     * 符号 ÷
     */
    public static final int SYMBOL_DIVIDE = 4;
    /**
     * 符号
     */
    public static final int SYMBOL_NULL = 5;
    /**
     * 符号 =
     */
    public static final int SYMBOL_EQUAL = 6;

    /**
     * 符号 "+-×÷ =" 集合
     */
    @IntDef({SYMBOL_PLUS, SYMBOL_MINUS, SYMBOL_MULTIPLY, SYMBOL_DIVIDE, SYMBOL_NULL, SYMBOL_EQUAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SYMBOL_TYPE {

    }

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    /**
     * 计算结果
     */
    private double mResult = 0d;
    /**
     * 列表适配器
     */
    private CalcAdapter mAdapter;
    /**
     * 列表数据
     */
    private List<CalcData> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList.clear();
        mList.add(new CalcData(SYMBOL_NULL, "0"));
        mAdapter = new CalcAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt_divide,
            R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt_multiply,
            R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt_minus,
            R.id.bt0, R.id.bt_dot, R.id.bt_plus, R.id.bt_equal,
            R.id.bt_clear
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                clickNum(1);
                break;
            case R.id.bt2:
                clickNum(2);
                break;
            case R.id.bt3:
                clickNum(3);
                break;
            case R.id.bt4:
                clickNum(4);
                break;
            case R.id.bt5:
                clickNum(5);
                break;
            case R.id.bt6:
                clickNum(6);
                break;
            case R.id.bt7:
                clickNum(7);
                break;
            case R.id.bt8:
                clickNum(8);
                break;
            case R.id.bt9:
                clickNum(8);
                break;
            case R.id.bt0:
                clickNum(0);
                break;
            case R.id.bt_dot:
                clickDot();
                break;
            case R.id.bt_plus:
                clickSymbol(SYMBOL_PLUS);
                break;
            case R.id.bt_minus:
                clickSymbol(SYMBOL_MINUS);
                break;
            case R.id.bt_multiply:
                clickSymbol(SYMBOL_MULTIPLY);
                break;
            case R.id.bt_divide:
                clickSymbol(SYMBOL_DIVIDE);
                break;
            case R.id.bt_equal:
                CalcData data = mList.get(mList.size() - 1);
                if (!data.hasLine && data.symbol != SYMBOL_EQUAL) {
                    calc(mList.get(mList.size() - 1).symbol, mList.get(mList.size() - 1).num);
                    mList.add(new CalcData(SYMBOL_EQUAL, String.valueOf(mResult)));
                    mList.add(new CalcData(true));
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollToPosition(mList.size() - 1);
                    setTvResult();
                }
                break;
            case R.id.bt_clear:
                mResult = 0;
                setTvResult();
                mList.clear();
                mList.add(new CalcData(SYMBOL_NULL, "0"));
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(mList.size() - 1);
                break;
            default:
        }
    }

    /**
     * 点击数字
     *
     * @param num 数字
     */
    private void clickNum(int num) {
        CalcData data;
        boolean hasLine = false;
        if (mList.size() > 0) {
            if (mList.get(mList.size() - 1).hasLine) {
                hasLine = true;
                mResult = 0;
                data = new CalcData(SYMBOL_NULL, "0");
            } else {
                data = mList.get(mList.size() - 1);
            }
        } else {
            data = new CalcData(SYMBOL_NULL, "0");
        }
        if (!hasLine) {
            mList.remove(mList.size() - 1);
        }
        mList.add(new CalcData(data.symbol, data.num + num));
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mList.size() - 1);
    }

    /**
     * 点击小数点
     */
    private void clickDot() {
        CalcData data;
        boolean hasLine = false;
        if (mList.size() > 0) {
            if (mList.get(mList.size() - 1).hasLine) {
                hasLine = true;
                mResult = 0;
                data = new CalcData(SYMBOL_NULL, "0.");
            } else {
                data = mList.get(mList.size() - 1);
                if (data.num.contains(".")) {
                    return;
                }
            }
        } else {
            data = new CalcData(SYMBOL_NULL, "0");
        }
        if (!hasLine) {
            mList.remove(mList.size() - 1);
        }
        mList.add(new CalcData(data.symbol, data.num + "."));
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mList.size() - 1);
    }

    /**
     * 点击计算符号
     *
     * @param symbol 计算符号
     */
    private void clickSymbol(@SYMBOL_TYPE int symbol) {
        CalcData data;
        if (mList.size() > 0) {
            if (mList.get(mList.size() - 1).hasLine) {
                data = new CalcData(SYMBOL_NULL, String.valueOf(mResult));
                mList.add(new CalcData(SYMBOL_NULL, String.valueOf(mResult)));
            } else {
                data = mList.get(mList.size() - 1);
            }
        } else {
            data = new CalcData(SYMBOL_NULL, "0");
        }
        if (data.symbol == SYMBOL_NULL || data.symbol == SYMBOL_EQUAL) {
            mList.add(new CalcData(symbol, " "));
            mResult = Constants.string2Double(data.num);
        } else {
            if (Constants.string2Double(data.num) < 0) {
                mList.remove(mList.size() - 1);
                mList.add(new CalcData(symbol, " "));
            } else {
                calc(data.symbol, data.num);
                mList.add(new CalcData(symbol, " "));
            }
        }
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mList.size() - 1);
        setTvResult();
    }

    /**
     * 计算结果
     *
     * @param symbol 符号
     * @param str    数字
     */
    private void calc(int symbol, String str) {
        double x = Constants.string2Double(str);
        switch (symbol) {
            case SYMBOL_PLUS:
                mResult = mResult + x;
                break;
            case SYMBOL_MINUS:
                mResult = mResult - x;
                break;
            case SYMBOL_MULTIPLY:
                mResult = mResult * x;
                break;
            case SYMBOL_DIVIDE:
                mResult = mResult / x;
                break;
            default:
        }
    }

    /**
     * 显示计算结果
     */
    private void setTvResult() {
        mTvResult.setText("=" + Constants.doubleTrans(mResult));
    }
}
