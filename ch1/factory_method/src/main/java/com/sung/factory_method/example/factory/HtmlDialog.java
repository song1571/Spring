package com.sung.factory_method.example.factory;

import com.sung.factory_method.example.buttons.Button;
import com.sung.factory_method.example.buttons.HtmlButton;

/**
 * HTML Dialog will produce HTML buttons.
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}