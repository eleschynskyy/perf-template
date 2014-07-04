package com.br.core.web.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebComponent;

public class Text extends WebComponent<Text> {

	public Text(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}

	public String getTextByPatternGroup(String pattern, int group) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(getText());
		if (m.matches()) {
			return m.group(group);
		}
		return null;
	}
	
}
