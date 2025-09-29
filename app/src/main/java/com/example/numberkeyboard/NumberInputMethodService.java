package com.example.numberkeyboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class NumberInputMethodService extends InputMethodService implements View.OnClickListener {

    private View mKeyboardView;
    private ClipboardManager mClipboardManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public View onCreateInputView() {
        // 加载键盘布局
        mKeyboardView = getLayoutInflater().inflate(R.layout.keyboard, null);
        
        // 设置数字键的点击监听器
        setupNumberKeys();
        
        // 设置功能键的点击监听器
        setupFunctionKeys();
        
        return mKeyboardView;
    }

    private void setupNumberKeys() {
        int[] numberKeyIds = {
            R.id.key_0, R.id.key_1, R.id.key_2, R.id.key_3, R.id.key_4,
            R.id.key_5, R.id.key_6, R.id.key_7, R.id.key_8, R.id.key_9
        };
        
        for (int keyId : numberKeyIds) {
            Button key = mKeyboardView.findViewById(keyId);
            if (key != null) {
                key.setOnClickListener(this);
            }
        }
    }

    private void setupFunctionKeys() {
        int[] functionKeyIds = {
            R.id.key_copy, R.id.key_paste, R.id.key_clear, R.id.key_delete
        };
        
        for (int keyId : functionKeyIds) {
            Button key = mKeyboardView.findViewById(keyId);
            if (key != null) {
                key.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        InputConnection inputConnection = getCurrentInputConnection();
        if (inputConnection == null) {
            return;
        }

        int viewId = view.getId();

        // 处理数字键
        if (viewId == R.id.key_0) {
            inputConnection.commitText("0", 1);
        } else if (viewId == R.id.key_1) {
            inputConnection.commitText("1", 1);
        } else if (viewId == R.id.key_2) {
            inputConnection.commitText("2", 1);
        } else if (viewId == R.id.key_3) {
            inputConnection.commitText("3", 1);
        } else if (viewId == R.id.key_4) {
            inputConnection.commitText("4", 1);
        } else if (viewId == R.id.key_5) {
            inputConnection.commitText("5", 1);
        } else if (viewId == R.id.key_6) {
            inputConnection.commitText("6", 1);
        } else if (viewId == R.id.key_7) {
            inputConnection.commitText("7", 1);
        } else if (viewId == R.id.key_8) {
            inputConnection.commitText("8", 1);
        } else if (viewId == R.id.key_9) {
            inputConnection.commitText("9", 1);
        }
        // 处理功能键
        else if (viewId == R.id.key_copy) {
            handleCopy(inputConnection);
        } else if (viewId == R.id.key_paste) {
            handlePaste(inputConnection);
        } else if (viewId == R.id.key_clear) {
            handleClear(inputConnection);
        } else if (viewId == R.id.key_delete) {
            handleDelete(inputConnection);
        }
    }

    private void handleCopy(InputConnection inputConnection) {
        // 获取选中的文本
        CharSequence selectedText = inputConnection.getSelectedText(0);
        if (selectedText != null && selectedText.length() > 0) {
            // 复制到剪贴板
            ClipData clip = ClipData.newPlainText("复制的文本", selectedText);
            mClipboardManager.setPrimaryClip(clip);
        } else {
            // 如果没有选中文本，获取当前行的所有文本
            CharSequence textBeforeCursor = inputConnection.getTextBeforeCursor(1000, 0);
            CharSequence textAfterCursor = inputConnection.getTextAfterCursor(1000, 0);
            String allText = (textBeforeCursor != null ? textBeforeCursor.toString() : "") +
                           (textAfterCursor != null ? textAfterCursor.toString() : "");
            if (!allText.isEmpty()) {
                ClipData clip = ClipData.newPlainText("复制的文本", allText);
                mClipboardManager.setPrimaryClip(clip);
            }
        }
    }

    private void handlePaste(InputConnection inputConnection) {
        if (mClipboardManager.hasPrimaryClip()) {
            ClipData clip = mClipboardManager.getPrimaryClip();
            if (clip != null && clip.getItemCount() > 0) {
                CharSequence text = clip.getItemAt(0).getText();
                if (text != null) {
                    inputConnection.commitText(text, 1);
                }
            }
        }
    }

    private void handleClear(InputConnection inputConnection) {
        // 选择所有文本并删除
        inputConnection.performContextMenuAction(android.R.id.selectAll);
        inputConnection.deleteSurroundingText(0, 0);
        
        // 另一种清空方式：获取文本长度并删除
        CharSequence textBeforeCursor = inputConnection.getTextBeforeCursor(1000, 0);
        CharSequence textAfterCursor = inputConnection.getTextAfterCursor(1000, 0);
        int beforeLength = textBeforeCursor != null ? textBeforeCursor.length() : 0;
        int afterLength = textAfterCursor != null ? textAfterCursor.length() : 0;
        inputConnection.deleteSurroundingText(beforeLength, afterLength);
    }

    private void handleDelete(InputConnection inputConnection) {
        // 删除光标前的一个字符
        inputConnection.deleteSurroundingText(1, 0);
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
        // 键盘显示时的初始化工作
    }

    @Override
    public void onFinishInputView(boolean finishingInput) {
        super.onFinishInputView(finishingInput);
        // 键盘隐藏时的清理工作
    }
}
