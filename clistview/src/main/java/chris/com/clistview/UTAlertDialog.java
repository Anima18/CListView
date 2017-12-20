package chris.com.clistview;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by jianjianhong on 2017/3/17.
 * UTAlertDialog继承AlertDialog，在Builder中新增了一些接口，主要是为了在lua中方便使用
 */

public class UTAlertDialog extends AlertDialog {
    public UTAlertDialog(@NonNull Context context) {
        super(context);
    }

    public UTAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public UTAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder extends AlertDialog.Builder{
        public Builder(@NonNull Context context) {
            super(context);
        }

        public UTAlertDialog.Builder setSingleChoiceItems(List<String> items, int checkedItem, final OnClickListener listener) {
            setSingleChoiceItems(items.toArray(new String[]{}), checkedItem, listener);
            return this;
        }

        public UTAlertDialog.Builder setMultiChoiceItems(List<String> items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            setMultiChoiceItems(items.toArray(new String[]{}), checkedItems, listener);
            return this;
        }

        @NonNull
        @Override
        public Context getContext() {
            return super.getContext();
        }

        @Override
        public UTAlertDialog.Builder setTitle(@StringRes int titleId) {
            super.setTitle(titleId);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setTitle(@Nullable CharSequence title) {
            super.setTitle(title);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setCustomTitle(@Nullable View customTitleView) {
            super.setCustomTitle(customTitleView);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setMessage(@StringRes int messageId) {
            super.setMessage(messageId);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setMessage(@Nullable CharSequence message) {
            super.setMessage(message);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setIcon(@DrawableRes int iconId) {
            super.setIcon(iconId);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setIcon(@Nullable Drawable icon) {
            super.setIcon(icon);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setIconAttribute(@AttrRes int attrId) {
            super.setIconAttribute(attrId);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setPositiveButton(@StringRes int textId, OnClickListener listener) {
            super.setPositiveButton(textId, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            super.setPositiveButton(text, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            super.setNegativeButton(textId, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            super.setNegativeButton(text, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            super.setNeutralButton(textId, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            super.setNeutralButton(text, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setCancelable(boolean cancelable) {
            super.setCancelable(cancelable);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setOnCancelListener(OnCancelListener onCancelListener) {
            super.setOnCancelListener(onCancelListener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setOnDismissListener(OnDismissListener onDismissListener) {
            super.setOnDismissListener(onDismissListener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setOnKeyListener(OnKeyListener onKeyListener) {
            super.setOnKeyListener(onKeyListener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setItems(@ArrayRes int itemsId, OnClickListener listener) {
            super.setItems(itemsId, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setItems(CharSequence[] items, OnClickListener listener) {
            super.setItems(items, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setAdapter(ListAdapter adapter, OnClickListener listener) {
            super.setAdapter(adapter, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
            super.setCursor(cursor, listener, labelColumn);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            super.setMultiChoiceItems(itemsId, checkedItems, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            super.setMultiChoiceItems(items, checkedItems, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
            super.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, OnClickListener listener) {
            super.setSingleChoiceItems(itemsId, checkedItem, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
            super.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
            super.setSingleChoiceItems(items, checkedItem, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
            super.setSingleChoiceItems(adapter, checkedItem, listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            super.setOnItemSelectedListener(listener);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setView(int layoutResId) {
            super.setView(layoutResId);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setView(View view) {
            super.setView(view);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            super.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setInverseBackgroundForced(boolean useInverseBackground) {
            super.setInverseBackgroundForced(useInverseBackground);
            return this;
        }

        @Override
        public UTAlertDialog.Builder setRecycleOnMeasureEnabled(boolean enabled) {
            super.setRecycleOnMeasureEnabled(enabled);
            return this;
        }

        @Override
        public AlertDialog create() {
            return super.create();
        }

        @Override
        public AlertDialog show() {
            return super.show();
        }
    }
}
