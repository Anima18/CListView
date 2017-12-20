package chris.com.clistview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by jianjianhong on 2017/3/30.
 */

public class DialogTextView extends AppCompatTextView implements View.OnClickListener {

    private Context mContext;
    private AlertDialog dialog;

    private DialogInterface.OnClickListener listener;

    public void setOnItemSelectListener(DialogInterface.OnClickListener listener) {
        this.listener = listener;
    }

    public DialogTextView(Context context) {
        this(context, null);
    }

    public DialogTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.setOnClickListener(this);
    }

    public void setData(String title, final List<String> data, int selectedPosition) {
        if(data == null) {
            new IllegalArgumentException("dialog items must not null");
        }
        dialog = new UTAlertDialog.Builder(mContext)
                .setTitle(title)
                .setSingleChoiceItems(data, selectedPosition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogTextView.this.setText(data.get(which));
                        if(listener != null) {
                            listener.onClick(dialog, which);
                        }
                        dialog.dismiss();
                    }
                })
                .create();
    }


    @Override
    public void onClick(View v) {
        if(dialog == null) {
            new UTAlertDialog.Builder(mContext).show();
        }else {
            dialog.show();
        }
    }
}
