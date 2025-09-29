package com.example.numberkeyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupUI();
    }

    private void setupUI() {
        TextView titleText = findViewById(R.id.title_text);
        TextView instructionText = findViewById(R.id.instruction_text);
        Button enableButton = findViewById(R.id.enable_button);
        Button selectButton = findViewById(R.id.select_button);

        titleText.setText(R.string.settings_title);
        instructionText.setText("使用步骤：\n1. 点击「启用输入法」按钮\n2. 在系统设置中启用数字键盘\n3. 点击「选择输入法」按钮\n4. 选择数字键盘作为当前输入法");

        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInputMethodSettings();
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputMethodPicker();
            }
        });
    }

    private void openInputMethodSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "无法打开输入法设置", Toast.LENGTH_SHORT).show();
        }
    }

    private void showInputMethodPicker() {
        try {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "请在设置中选择数字键盘", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "无法打开输入法选择器", Toast.LENGTH_SHORT).show();
        }
    }
}
