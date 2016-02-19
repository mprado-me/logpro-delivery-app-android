package solutions.logpro.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import solutions.logpro.MainActivity;

/**
 * Created by MarcoAurelio on 05/02/2016.
 */
// TODO: No MyEditText, não mostrar o cursor ao scollar
// TODO: Na posição horizontal, apagar o cursor ao clicar em concluir a inserção do texto
// TODO: Encontrar uma forma de o menu de seleção de texto não bugar com os outros menus
// TODO: Indicar para o usuario quando esta focado e quando esta desfocado mudando o background
public class MyEditText extends EditText {

    private final String LOG_TAG = this.getClass().getName() + Consts.GENERAL_LOG_TAG;
    private OnBackPressedListener mOnBackPressedListener;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            mOnBackPressedListener.onBackPressed();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void setOnFocusChangeListener(View.OnFocusChangeListener listener) {
        Log.d(LOG_TAG, "setOnFocusChangeListener");
        super.setOnFocusChangeListener(listener);
    }

    public void setOnBackPressedListener(MyEditText.OnBackPressedListener onBackPressedListener) {
        Log.d(LOG_TAG, "setBackPressedListener");
        mOnBackPressedListener = onBackPressedListener;
    }

    public interface OnBackPressedListener {
        void onBackPressed();
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d(LOG_TAG, "onScrollChanged");
        super.onScrollChanged(l, t, oldl, oldt);
    }
}

