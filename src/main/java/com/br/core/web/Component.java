package com.br.core.web;

import org.openqa.selenium.WebDriver;

public abstract class Component<T extends Component<T>> {
	
	protected WebDriver driver;
	
	public Component(WebDriver driver) {
		this.driver = driver;
	}
	
	public abstract boolean isAvailable();
	
	public T waitUntilAvailable() {
		return new Waiter<T>().forComponent((T) this, driver).toBeAvailable();
	}

}
